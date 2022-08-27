package br.com.tabarato.infra.action.dispatcher

interface ActionDispatcher<A : ViewAction> {
    fun dispatch(action: A)
}
