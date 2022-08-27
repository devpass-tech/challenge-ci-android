package de.tigrao.github.domain.profile.data

import com.apollographql.apollo3.ApolloClient
import com.apollographql.apollo3.cache.normalized.apolloStore
import de.tigrao.github.domain.profile.domain.ShouldExpireCache
import javax.inject.Inject

internal class ExpireCacheIfNeeded @Inject constructor(
    private val apolloClient: ApolloClient,
    private val shouldExpireCache: ShouldExpireCache,
    private val expireCacheDatasource: ExpireCacheDatasource,
) {

    fun expireIfNeeded(force: Boolean) {
        if (force || shouldExpireCache()) {
            apolloClient.apolloStore.clearAll()
            expireCacheDatasource.deleteAndSaveTime()
        }
    }
}
