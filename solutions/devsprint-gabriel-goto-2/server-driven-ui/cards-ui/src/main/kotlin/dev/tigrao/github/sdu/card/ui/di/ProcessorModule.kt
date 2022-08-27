package dev.tigrao.github.sdu.card.ui.di

import dagger.Binds
import dagger.Module
import dev.tigrao.github.sdu.card.processor.CardProcessor
import dev.tigrao.github.sdu.card.ui.processor.CardProcessorImpl

@Module
internal interface ProcessorModule {

    @Binds
    fun bindCardProcessor(bind: CardProcessorImpl): CardProcessor
}
