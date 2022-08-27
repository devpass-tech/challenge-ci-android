package me.tigrao.github.main.di

import dagger.Binds
import dagger.Module
import dagger.android.AndroidInjector
import dagger.multibindings.ClassKey
import dagger.multibindings.IntoMap
import me.tigrao.github.main.view.MainActivity

@Module(
    subcomponents = [
        MainSubComponent::class,
    ]
)
abstract class MainActivityModule {

    @Binds
    @IntoMap
    @ClassKey(MainActivity::class)
    abstract fun bindRepoActivity(factory: MainSubComponent.Factory): AndroidInjector.Factory<*>
}
