package de.tigrao.github.domain.profile.mapper

import de.tigrao.github.domain.profile.model.LanguageModel
import dev.tigrao.github.fragment.LanguageFragment
import javax.inject.Inject

internal class LanguageDataToModelMapper @Inject constructor() {

    fun mapFrom(from: LanguageFragment?) =
        from?.let {
            LanguageModel(
                name = it.name,
                color = it.color.orEmpty(),
            )
        }
}
