package de.tigrao.github.domain.profile.data

import com.apollographql.apollo3.ApolloClient
import com.apollographql.apollo3.cache.normalized.api.MemoryCacheFactory
import com.apollographql.apollo3.cache.normalized.normalizedCache
import de.tigrao.github.domain.profile.domain.ShouldExpireCache
import io.mockk.every
import io.mockk.mockk
import io.mockk.verify
import org.junit.Before
import org.junit.Test

class ExpireCacheIfNeededTest {

    lateinit var apolloClient: ApolloClient
    private val shouldExpireCache = mockk<ShouldExpireCache>()
    private val expireCacheDatasource = mockk<ExpireCacheDatasource>(relaxed = true)

    private lateinit var subject: ExpireCacheIfNeeded

    @Before
    fun setup() {
        apolloClient = ApolloClient.Builder()
            .serverUrl("https://google.com")
            .normalizedCache(MemoryCacheFactory())
            .build()

        subject = ExpireCacheIfNeeded(
            apolloClient,
            shouldExpireCache,
            expireCacheDatasource,
        )
    }

    @Test
    fun expireIfNeeded_forced_verifyIsSaved() {
        subject.expireIfNeeded(true)

        verify {
            expireCacheDatasource.deleteAndSaveTime()
        }
    }

    @Test
    fun expireIfNeeded_time_verifyApolloIsCleared() {
        prepare(shouldExpire = true)

        subject.expireIfNeeded(false)

        verify {
            expireCacheDatasource.deleteAndSaveTime()
        }
    }

    fun prepare(shouldExpire: Boolean) {
        every { shouldExpireCache() } returns shouldExpire
    }
}
