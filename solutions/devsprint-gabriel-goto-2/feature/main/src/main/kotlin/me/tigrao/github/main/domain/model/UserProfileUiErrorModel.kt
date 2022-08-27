package me.tigrao.github.main.domain.model

import br.com.hippopotamus.tabarato.designsystem.viewstate.StateViewArg

internal sealed interface UserProfileUiErrorModel {

    val state: StateViewArg

    data class GenericError(override val state: StateViewArg) : UserProfileUiErrorModel
}
