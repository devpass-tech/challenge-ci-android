package de.tigrao.github.domain.profile.data

import android.content.SharedPreferences
import java.util.Calendar
import javax.inject.Inject

private const val TIME_KEY = "PROFILE_TIME_KEY"

internal class ExpireCacheDatasource @Inject constructor(
    private val sharedPreferences: SharedPreferences
) {

    fun deleteAndSaveTime() {
        val time = Calendar.getInstance().timeInMillis

        sharedPreferences.edit()
            .putLong(TIME_KEY, time)
            .apply()
    }

    fun getTime(): Long {
        return sharedPreferences.getLong(TIME_KEY, 0L)
    }
}
