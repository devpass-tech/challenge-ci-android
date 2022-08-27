package dev.tigrao.github.infra.network

import com.apollographql.apollo3.ApolloClient
import com.apollographql.apollo3.cache.normalized.normalizedCache
import com.apollographql.apollo3.cache.normalized.sql.SqlNormalizedCacheFactory
import com.apollographql.apollo3.network.okHttpClient
import javax.inject.Inject

internal class NetworkService @Inject constructor(
    private val networkBuilder: NetworkBuilder,
    private val okhttpClientFactory: OkhttpClientFactory,
    private val sqlNormalizedCacheFactory: SqlNormalizedCacheFactory,
) {

    fun createApolloService(): ApolloClient {

        return ApolloClient.Builder()
            .serverUrl(networkBuilder.baseUrl)
            .normalizedCache(sqlNormalizedCacheFactory)
            .okHttpClient(okhttpClientFactory.createNewInstance())
            .build()
    }
}
