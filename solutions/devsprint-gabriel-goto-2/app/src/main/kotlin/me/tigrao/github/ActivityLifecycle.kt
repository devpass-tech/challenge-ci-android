package me.tigrao.github

import android.app.Activity
import android.app.Application
import android.os.Bundle
import dagger.android.AndroidInjection

internal class ActivityLifecycle : Application.ActivityLifecycleCallbacks {
    override fun onActivityCreated(activity: Activity, savedInstanceState: Bundle?) {
        AndroidInjection.inject(activity)
    }

    override fun onActivityStarted(activity: Activity) {
        // DO NOTHING
    }

    override fun onActivityResumed(activity: Activity) {
        // DO NOTHING
    }

    override fun onActivityPaused(activity: Activity) {
        // DO NOTHING
    }

    override fun onActivityStopped(activity: Activity) {
        // DO NOTHING
    }

    override fun onActivitySaveInstanceState(activity: Activity, outState: Bundle) {
        // DO NOTHING
    }

    override fun onActivityDestroyed(activity: Activity) {
        // DO NOTHING
    }
}
