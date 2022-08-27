package dev.tigrao.github.infra.network.interceptor

import dev.tigrao.github.infra.network.NetworkBuilder
import okhttp3.Interceptor
import okhttp3.Response
import javax.inject.Inject

internal class AuthorizationInterceptor @Inject constructor(
    private val networkBuilder: NetworkBuilder,
) : Interceptor {
    override fun intercept(chain: Interceptor.Chain): Response {
        val request = chain.request().newBuilder()
            .addHeader("Authorization", "Bearer ${networkBuilder.accessToken}")
            .build()

        return chain.proceed(request)
    }
}
