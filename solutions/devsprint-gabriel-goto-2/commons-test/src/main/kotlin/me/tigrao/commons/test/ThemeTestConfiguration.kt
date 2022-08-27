package me.tigrao.commons.test

import android.app.Application
import androidx.test.core.app.ApplicationProvider

fun applyTestTheme() {
    ApplicationProvider.getApplicationContext<Application>()
        .setTheme(R.style.Theme_MyApp)
}
