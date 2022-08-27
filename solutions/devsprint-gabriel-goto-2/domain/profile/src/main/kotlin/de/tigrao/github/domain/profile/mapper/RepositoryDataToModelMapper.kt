package de.tigrao.github.domain.profile.mapper

import de.tigrao.github.domain.profile.model.RepositoryModel
import dev.tigrao.github.fragment.RepositoryFragment
import javax.inject.Inject

internal class RepositoryDataToModelMapper @Inject constructor(
    private val languageMapper: LanguageDataToModelMapper,
) {
    fun mapFrom(from: RepositoryFragment): RepositoryModel =
        RepositoryModel(
            owner = from.owner.login,
            description = from.description.orEmpty(),
            language = languageMapper.mapFrom(from.primaryLanguage?.languageFragment),
            image = from.owner.avatarUrl.toString(),
            stars = from.stargazerCount,
            title = from.name
        )
}
