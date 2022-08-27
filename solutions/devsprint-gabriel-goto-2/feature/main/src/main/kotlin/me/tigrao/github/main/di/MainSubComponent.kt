package me.tigrao.github.main.di

import dagger.Subcomponent
import dagger.android.AndroidInjector
import me.tigrao.github.main.view.MainActivity

@Subcomponent
interface MainSubComponent : AndroidInjector<MainActivity> {
    @Subcomponent.Factory
    interface Factory : AndroidInjector.Factory<MainActivity>
}
