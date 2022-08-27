package de.tigrao.github.domain.profile.data

import com.apollographql.apollo3.ApolloClient
import com.apollographql.apollo3.cache.normalized.FetchPolicy
import com.apollographql.apollo3.cache.normalized.fetchPolicy
import dev.tigrao.github.UserProfileQuery
import javax.inject.Inject

internal class GetProfileDatasource @Inject constructor(
    private val apolloClient: ApolloClient,
    private val expireCacheIfNeeded: ExpireCacheIfNeeded
) {

    suspend fun fetchProfileData(
        force: Boolean
    ): UserProfileQuery.Data {

        expireCacheIfNeeded.expireIfNeeded(force)

        return apolloClient
            .query(UserProfileQuery())
            .fetchPolicy(FetchPolicy.CacheFirst)
            .execute()
            .dataAssertNoErrors
    }
}
