package dependencies

import org.gradle.api.Project
import org.gradle.kotlin.dsl.closureOf

internal object Versions {

    const val okhttp = "4.9.3"
    const val retrofit = "2.9.0"
    const val supportLibrary = "1.4.0"
    const val material = "1.4.0"
    const val recyclerView = "1.2.1"
    const val jUnit4 = "4.13.2"
    const val assertJ = "2.9.1"
    const val androidJUnit = "1.2.0"
    const val espressoCore = "3.4.0"
    const val espressoRules = "1.4.0"
    const val roboletric = "4.7.3"
    const val timber = "5.0.1"
    const val livedata = "2.4.0"
    const val constraintLayout = "2.1.2"
    const val gson = "2.8.9"
    const val imageFetcher = "4.12.0"
    const val paging = "3.1.0"

    const val mockitoKotlin = "2.0.0-RC3"
    const val mockitoDexMaker = "2.19.0"
}

object Dependencies {

    val kotlinStdlib = "org.jetbrains.kotlin:kotlin-stdlib-jdk7:${PluginsVersions.kotlin}"

    val okhttp = "com.squareup.okhttp3:okhttp:${Versions.okhttp}"
    val okhttpLogger = "com.squareup.okhttp3:logging-interceptor:${Versions.okhttp}"
    val retrofit = "com.squareup.retrofit2:retrofit:${Versions.retrofit}"

    val appCompat = "androidx.appcompat:appcompat:${Versions.supportLibrary}"
    const val coreKTX = "androidx.core:core-ktx:1.7.0"
    val recyclerView = "androidx.recyclerview:recyclerview:${Versions.recyclerView}"
    val materialDesign = "com.google.android.material:material:${Versions.material}"

    val timber = "com.jakewharton.timber:timber:${Versions.timber}"

    val viewModel = "androidx.lifecycle:lifecycle-viewmodel:${Versions.livedata}"
    val extensions = "androidx.lifecycle:lifecycle-extensions:${Versions.livedata}"
    val lifecycle = "androidx.lifecycle:lifecycle-livedata:${Versions.livedata}"
    val lifecycleRuntime = "androidx.lifecycle:lifecycle-runtime-ktx:${Versions.livedata}"

    val constraintLayout = "androidx.constraintlayout:constraintlayout:${Versions.constraintLayout}"

    val imageFetcher = "com.github.bumptech.glide:glide:${Versions.imageFetcher}"

    const val pullToRefresh = "androidx.swiperefreshlayout:swiperefreshlayout:1.1.0"

    object Groupie {
        private const val VERSION = "2.10.1"

        private const val core = "com.github.lisawray.groupie:groupie:$VERSION"
        private const val binding = "com.github.lisawray.groupie:groupie-viewbinding:$VERSION"

        val addLibs = closureOf<Project> {
            with(project.dependencies) {
                add("implementation", core)
                add("implementation", binding)
            }
        }
    }

    object Dagger {
        private const val VERSION = "2.41"

        val android = "com.google.dagger:dagger-android-support:$VERSION"
        private val implementation = "com.google.dagger:dagger:$VERSION"
        private val processor = "com.google.dagger:dagger-compiler:$VERSION"

        val addLibs = closureOf<Project> {
            with(project.dependencies) {
                add("implementation", implementation)
                add("kapt", processor)
            }
        }
    }

    object Apollo {
        private const val VERSION = "3.3.0"

        const val runtime = "com.apollographql.apollo3:apollo-runtime:$VERSION"
        const val sqLite = "com.apollographql.apollo3:apollo-normalized-cache-sqlite:$VERSION"
        const val sqLiteJvm = "com.apollographql.apollo3:apollo-normalized-cache-jvm:$VERSION"
        const val cache = "com.apollographql.apollo:apollo-http-cache:2.5.11"
        const val api = "com.apollographql.apollo3:apollo-api:$VERSION"
    }

    object Moshi {
        object Versions {
            const val moshi = "1.12.0"
        }

        const val core = "com.squareup.moshi:moshi:${Versions.moshi}"
        const val retrofit = "com.squareup.retrofit2:converter-moshi:2.9.0"
        const val coreKotlin = "com.squareup.moshi:moshi-kotlin:${Versions.moshi}"
        const val codeGen = "com.squareup.moshi:moshi-kotlin-codegen:${Versions.moshi}"

        val addLibs = closureOf<Project> {
            with(project.dependencies) {
                add("implementation", core)
                add("kapt", codeGen)
            }
        }
    }

    object Coroutines {
        object Versions {
            const val coroutines = "1.5.2"
        }

        const val core = "org.jetbrains.kotlinx:kotlinx-coroutines-core:${Versions.coroutines}"
        const val android =
            "org.jetbrains.kotlinx:kotlinx-coroutines-android:${Versions.coroutines}"
        const val test = "org.jetbrains.kotlinx:kotlinx-coroutines-test:${Versions.coroutines}"
    }

    object Kirich {
        private const val VERSION = "1.5.3"

        const val viewBindingProperty =
            "com.github.kirich1409:viewbindingpropertydelegate-noreflection:$VERSION"
    }
}

object TestDependencies {

    const val mockkVersion = "1.12.1"

    const val mockk = "io.mockk:mockk:$mockkVersion"
    const val mockkAndroid = "io.mockk:mockk-android:$mockkVersion"
    val jUnit = "junit:junit:${Versions.jUnit4}"
    val kotlinReflect = "org.jetbrains.kotlin:kotlin-reflect:${PluginsVersions.kotlin}"
    val assertJ = "org.assertj:assertj-core:${Versions.assertJ}"
    val mockitoKotlin = "com.nhaarman.mockitokotlin2:mockito-kotlin:${Versions.mockitoKotlin}"
    val mockitoDexMaker = "com.linkedin.dexmaker:dexmaker-mockito:${Versions.mockitoDexMaker}"
    val androidTestRunner = "com.android.support.test:runner:${Versions.androidJUnit}"
    val espresso = "com.android.support.test.espresso:espresso-core:${Versions.espressoCore}"
    val roboletric = "org.robolectric:robolectric:${Versions.roboletric}"
    val mockWebServer = "com.squareup.okhttp3:mockwebserver:${Versions.okhttp}"
}
