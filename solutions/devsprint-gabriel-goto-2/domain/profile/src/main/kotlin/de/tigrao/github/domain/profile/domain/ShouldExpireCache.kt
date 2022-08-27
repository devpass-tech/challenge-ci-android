package de.tigrao.github.domain.profile.domain

import de.tigrao.github.domain.profile.data.ExpireCacheDatasource
import java.util.Calendar
import javax.inject.Inject

private const val HOUR = 60 * 1000 * 60 * 24

internal class ShouldExpireCache @Inject constructor(
    private val expireCacheDatasource: ExpireCacheDatasource,
) {

    operator fun invoke(): Boolean {
        val savedTime = expireCacheDatasource.getTime()
        val currentTime = Calendar.getInstance().timeInMillis

        val difference = (currentTime - savedTime) / HOUR

        return difference >= 1
    }
}
