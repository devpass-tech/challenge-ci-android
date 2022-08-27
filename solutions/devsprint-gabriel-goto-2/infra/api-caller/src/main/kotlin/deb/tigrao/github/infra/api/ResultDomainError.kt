package deb.tigrao.github.infra.api

sealed class ResultDomainError(val message: String) {
    data class NetworkError(
        val httpCode: Int,
        val httpMessage: String,
        val exceptionTitle: String,
        val localizeMessage: String,
        val isConnectionError: Boolean
    ) : ResultDomainError(httpMessage)

    data class GenericError(
        val exceptionTitle: String,
        private val genericMessage: String,
        val isConnectionError: Boolean
    ) : ResultDomainError(genericMessage)

    object UnknownError : ResultDomainError("unknow")
}
