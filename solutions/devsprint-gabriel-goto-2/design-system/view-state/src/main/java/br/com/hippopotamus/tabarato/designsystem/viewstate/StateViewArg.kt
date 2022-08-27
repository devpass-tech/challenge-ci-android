package br.com.hippopotamus.tabarato.designsystem.viewstate

import br.com.tabarato.infra.action.dispatcher.ViewAction

data class StateViewArg(
    val type: StateViewType,
    val title: String,
    val description: String? = null,
    val positiveButton: ButtonViewArg? = null,
    val negativeButton: ButtonViewArg? = null
)

data class ButtonViewArg(
    val text: String,
    val action: ViewAction
)
