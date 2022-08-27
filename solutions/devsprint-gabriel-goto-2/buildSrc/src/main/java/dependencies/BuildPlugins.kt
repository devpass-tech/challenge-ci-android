package dependencies

object PluginsVersions {
    const val kotlin = "1.6.21"
    const val androidGradlePlugin = "7.0.4"
    const val detekt = "1.1.1"
}

object BuildPlugins {

    const val kotlinGradlePlugin = "org.jetbrains.kotlin:kotlin-gradle-plugin:${PluginsVersions.kotlin}"
    const val androidGradlePlugin = "com.android.tools.build:gradle:${PluginsVersions.androidGradlePlugin}"
    const val detektPlugin = "io.gitlab.arturbosch.detekt:detekt-gradle-plugin:${PluginsVersions.detekt}"
}
