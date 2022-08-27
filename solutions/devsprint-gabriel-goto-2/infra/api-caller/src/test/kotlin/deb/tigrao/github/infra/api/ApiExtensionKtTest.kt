package deb.tigrao.github.infra.api

import kotlinx.coroutines.runBlocking
import okhttp3.MediaType.Companion.toMediaType
import okhttp3.ResponseBody.Companion.toResponseBody
import okhttp3.internal.http2.ConnectionShutdownException
import org.junit.Assert.assertEquals
import org.junit.Assert.assertTrue
import org.junit.Test
import retrofit2.HttpException
import retrofit2.Response
import java.net.ConnectException
import java.net.UnknownHostException

class ApiExtensionKtTest {

    @Test
    fun callApi_withSuccess_returnSuccessResult() = runBlocking {
        val expected = "String Object"

        val result = callApi { expected }

        assertTrue(result is ResultDomain.Success<String>)
    }

    @Test
    fun callApi_withSuccess_returnReturnCorrectValue() = runBlocking {
        val expected = "String Object"

        val result = callApi { expected }

        result as ResultDomain.Success

        assertEquals(expected, result.data)
    }

    @Test
    fun callApi_withError_returnReturnResultError() = runBlocking {
        val expected = "String Object"

        val result = callApi { throw IllegalArgumentException(expected) }

        assertTrue(result is ResultDomain.Error)
    }

    @Test
    fun callApi_withError_returnReturnResultErrorWithMappedMessage() = runBlocking {
        val result = callApi { throw IllegalArgumentException("String Object") }

        result as ResultDomain.Error

        val expected = ResultDomainError.GenericError(
            exceptionTitle = "java.lang.IllegalArgumentException",
            genericMessage = "String Object",
            isConnectionError = false,
        )

        assertEquals(expected, result.errorResult)
    }

    @Test
    fun callApi_withErrorHttpException_returnReturnResultErrorWithMappedMessage() = runBlocking {
        val response = Response.error<String>(
            404,
            "sample".toResponseBody("application/json".toMediaType())
        )

        val result = callApi { throw HttpException(response) }

        result as ResultDomain.Error

        val expected = ResultDomainError.NetworkError(
            httpCode = 404,
            httpMessage = "Response.error()",
            localizeMessage = "HTTP 404 Response.error()",
            exceptionTitle = "retrofit2.HttpException",
            isConnectionError = false,
        )

        assertEquals(expected, result.errorResult)
    }

    @Test
    fun callApe_withErrorConnectException_returnReturnResultErrorWithMappedMessage() = runBlocking {
        val result = callApi { throw ConnectException() }

        result as ResultDomain.Error

        val expected = ResultDomainError.GenericError(
            exceptionTitle = "java.net.ConnectException",
            genericMessage = "",
            isConnectionError = true,
        )

        assertEquals(expected, result.errorResult)
    }

    @Test
    fun callApe_withErrorUnknownHostException_returnReturnResultErrorWithMappedMessage() =
        runBlocking {
            val result = callApi { throw UnknownHostException() }

            result as ResultDomain.Error

            val expected = ResultDomainError.GenericError(
                exceptionTitle = "java.net.UnknownHostException",
                genericMessage = "",
                isConnectionError = true,
            )

            assertEquals(expected, result.errorResult)
        }

    @Test
    fun callApe_withErrorConnectionShutdownException_returnReturnResultErrorWithMappedMessage() =
        runBlocking {
            val result = callApi { throw ConnectionShutdownException() }

            result as ResultDomain.Error

            val expected = ResultDomainError.GenericError(
                exceptionTitle = "okhttp3.internal.http2.ConnectionShutdownException",
                genericMessage = "",
                isConnectionError = true,
            )

            assertEquals(expected, result.errorResult)
        }
}
