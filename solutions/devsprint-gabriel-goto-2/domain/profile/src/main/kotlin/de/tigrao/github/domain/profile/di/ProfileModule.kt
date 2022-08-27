package de.tigrao.github.domain.profile.di

import dagger.Module

@Module(
    includes = [
        ProfileDomainModule::class,
        ProfileDataModule::class,
    ]
)
interface ProfileModule
