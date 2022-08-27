package de.tigrao.github.domain.profile.di

import android.app.Application
import android.content.Context
import android.content.SharedPreferences
import dagger.Module
import dagger.Provides

@Module
internal object ProfileDataModule {

    @Provides
    fun provideSharedPreference(context: Application): SharedPreferences =
        context.getSharedPreferences("profileShared", Context.MODE_PRIVATE)
}
