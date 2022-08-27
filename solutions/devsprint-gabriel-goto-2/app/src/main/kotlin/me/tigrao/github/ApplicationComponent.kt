package me.tigrao.github

import android.app.Application
import android.content.res.Resources
import dagger.BindsInstance
import dagger.Component
import dagger.Module
import dagger.Provides
import dagger.android.AndroidInjectionModule
import de.tigrao.github.domain.profile.di.ProfileModule
import dev.tigrao.github.infra.network.NetworkBuilder
import dev.tigrao.github.infra.network.di.NetworkModule
import dev.tigrao.github.sdu.card.ui.di.CardUiModule
import me.tigrao.github.main.di.MainActivityModule
import me.tigrao.github.main.di.ProfileFeatureModule
import javax.inject.Singleton

@Module(
    includes = []
)
internal object AppModule {

    @Singleton
    @Provides
    fun provideNetworkBuilder() = NetworkBuilder(BuildConfig.API_URL, BuildConfig.ACCESS_TOKEN)

    @Provides
    fun provideResources(application: Application): Resources = application.resources
}

@Singleton
@Component(
    modules = [
        AppModule::class,
        AndroidInjectionModule::class,
        MainActivityModule::class,
        NetworkModule::class,
        ProfileModule::class,
        CardUiModule::class,
        ProfileFeatureModule::class,
    ],

    )
interface ApplicationComponent {

    @Component.Builder
    interface Builder {
        fun build(): ApplicationComponent

        @BindsInstance
        fun application(application: Application): Builder
    }

    fun inject(app: CustomApplication)
}
