package de.tigrao.github.domain.profile.model

sealed interface UserProfileErrorModel {

    object GenericError : UserProfileErrorModel
}
