package dev.tigrao.github.infra.network

import okhttp3.Interceptor
import okhttp3.OkHttpClient
import java.util.concurrent.TimeUnit
import javax.inject.Inject

private const val CONNECTION_TIMEOUT = 1L
private const val READ_TIMEOUT = 30L
private const val WRITE_TIMEOUT = 15L

class OkhttpClientFactory @Inject constructor(
    private val interceptor: Interceptor
) {

    fun createNewInstance(): OkHttpClient =
        OkHttpClient.Builder().connectTimeout(CONNECTION_TIMEOUT, TimeUnit.MINUTES)
            .readTimeout(READ_TIMEOUT, TimeUnit.SECONDS)
            .writeTimeout(WRITE_TIMEOUT, TimeUnit.SECONDS)
            .addInterceptor(interceptor)
            .build()
}
