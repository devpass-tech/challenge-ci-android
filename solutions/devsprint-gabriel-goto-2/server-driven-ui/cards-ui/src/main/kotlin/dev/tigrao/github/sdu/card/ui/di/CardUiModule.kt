package dev.tigrao.github.sdu.card.ui.di

import dagger.Module

@Module(
    includes = [
        ProcessorModule::class,
    ]
)
interface CardUiModule
