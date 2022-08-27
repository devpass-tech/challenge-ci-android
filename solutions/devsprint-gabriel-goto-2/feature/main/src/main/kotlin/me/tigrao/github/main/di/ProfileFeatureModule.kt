package me.tigrao.github.main.di

import dagger.Module

@Module(
    includes = [ProfileFeatureInternalModule::class]
)
interface ProfileFeatureModule
