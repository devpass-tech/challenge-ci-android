package deb.tigrao.github.infra.api

import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import okhttp3.internal.http2.ConnectionShutdownException
import retrofit2.HttpException
import java.net.ConnectException
import java.net.UnknownHostException

suspend fun <T> callApi(
    dispatcher: CoroutineDispatcher = Dispatchers.IO,
    apiCall: suspend () -> T
): ResultDomain<T, ResultDomainError> {
    return withContext(dispatcher) {
        try {
            ResultDomain.Success(apiCall())
        } catch (@Suppress("TooGenericExceptionCaught") ex: Exception) {
            val mappedException: ResultDomainError = when (ex) {
                is HttpException -> mapException(ex)
                else -> mapException(ex)
            }

            ResultDomain.Error(mappedException)
        }
    }
}

private fun mapException(httpException: HttpException): ResultDomainError.NetworkError {
    return ResultDomainError.NetworkError(
        httpCode = httpException.code(),
        httpMessage = httpException.message(),
        localizeMessage = httpException.localizedMessage,
        exceptionTitle = httpException.javaClass.name,
        isConnectionError = false
    )
}

private fun mapException(exception: Exception): ResultDomainError.GenericError {
    return ResultDomainError.GenericError(
        genericMessage = exception.message.orEmpty(),
        exceptionTitle = exception.javaClass.name,
        isConnectionError = exception.isConnectionError()
    )
}

private fun Throwable.isConnectionError() =
    this is ConnectException || this is UnknownHostException || this is ConnectionShutdownException
