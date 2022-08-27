package me.tigrao.github

internal object AppInjector {

    fun init(customApplication: CustomApplication) {
        DaggerApplicationComponent
            .builder()
            .application(customApplication)
            .build()
            .inject(customApplication)

        customApplication
            .registerActivityLifecycleCallbacks(ActivityLifecycle())
    }
}
