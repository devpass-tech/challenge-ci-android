package de.tigrao.github.domain.profile.di

import dagger.Module
import dagger.Provides
import de.tigrao.github.domain.profile.mapper.LanguageDataToModelMapper
import de.tigrao.github.domain.profile.mapper.ProfileSuccessMapper
import de.tigrao.github.domain.profile.mapper.RepositoryDataToModelMapper

@Module
internal object ProfileMapperModule {

    @Provides
    fun languageMapper() = LanguageDataToModelMapper()

    @Provides
    fun repositoryMapper(languageDataToModelMapper: LanguageDataToModelMapper) =
        RepositoryDataToModelMapper(
            languageMapper()
        )

    @Provides
    fun profileSuccessMapper(item: RepositoryDataToModelMapper) = ProfileSuccessMapper(item)
}
