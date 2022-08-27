package br.com.hippopotamus.tabarato.designsystem.viewstate

import br.com.tabarato.infra.action.dispatcher.ViewAction

interface StateViewActionDispatcher {
    fun dispatch(action: ViewAction)
}
