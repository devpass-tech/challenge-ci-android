package de.tigrao.github.domain.profile.domain

import de.tigrao.github.domain.profile.data.ExpireCacheDatasource
import io.mockk.every
import io.mockk.mockk
import io.mockk.mockkStatic
import org.junit.Assert.assertFalse
import org.junit.Assert.assertTrue
import org.junit.Test
import java.util.Calendar

class ShouldExpireCacheTest {

    private val expireCacheDatasource = mockk<ExpireCacheDatasource>()
    private val subject = ShouldExpireCache(expireCacheDatasource)

    @Test
    fun invoke_savedOneDayAgo_returnTrue() {
        prepare(
            savedTime = 1652076000000,
            current = 1652164200000,
        )

        val result = subject()

        assertTrue(result)
    }

    @Test
    fun invoke_savedLessThanOneHourAgo_returnTrue() {
        prepare(
            savedTime = 1652076000000,
            current = 1652077800000,
        )

        val result = subject()

        assertFalse(result)
    }

    fun prepare(savedTime: Long, current: Long) {
        every {
            expireCacheDatasource.getTime()
        } returns savedTime

        mockkStatic(Calendar::class)
        val currentCalendar = Calendar.getInstance()
        currentCalendar.timeInMillis = current

        every { Calendar.getInstance() } returns currentCalendar
    }
}
