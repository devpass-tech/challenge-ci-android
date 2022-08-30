![example workflow](https://github.com/GabriellCosta/GithubProfile/actions/workflows/main.yml/badge.svg?branch=master)

## Propose

This project was based on a previous project on my github and on my personal project that was used
as a base

## Arch
    This project has a some different types of modules, like infra modules that are responsible for
    the infrastructure of the project, feature modules where I can keep mu features, domain modules
    where domain logic agnostic of features live, cards module*

    About the cards module, they are created following an idea of a simple Server-driven-ui approach, so if
    we want to create a new screen for example a list of repositories of the given user,
    the card and the configuration are already set and we can reuse them, but here in this project
    we just have a test on this subject

## Configuration

add a Github Oauth key to your gradle.properties

```
github_access_token = <YOUR KEY HERE>
```

## Changes needed

- Enable Configuration Change support

   The app does not allow configuration change (rotation, memory, etc), would be great to give support,
   so the user will not lose the location he is seeing or interacting

- Add hilt/anvil (specification said to use dagger directly)

    The app uses dagger2 only, to make the development easier we could use anvil or hilt

- Module for shared preferences

    domains::profile method should be a pure jvm module, but right now is an android module due to
    sharedPreferences, we could create an infra module where a wrapper for sharedPreferences is created,
    this way all modules that want to use sharedPreferences would be pure jvm if they can

- Remove Resource reference from FetchProfileDataUseCase

    We could create a proper abstraction for resources usage, this way no domain layer would know
    about an android class like `resource` and also would be possible to get resource data

- Move MainActivity to the proper feature module and make all features fragment-based, this also would
    improve navigation configuration and logic

- Unit tests for FetchProfileDataUseCaseDefault

    The moment this is being written `FetchProfileDataUseCaseDefault` does not have unit tests,
    my approach to testing this class would be to extract the creation of card to the proper mapper and test them,
    the contract for these tests would be something like `mapFrom(UserProfileModel)`, with that I would
    make the UseCase have a list of mappers so I would process all the mappers for the given api
    response and create the cards in order, making the class simple
