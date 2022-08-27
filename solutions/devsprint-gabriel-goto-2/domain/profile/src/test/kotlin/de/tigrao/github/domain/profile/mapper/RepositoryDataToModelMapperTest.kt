package de.tigrao.github.domain.profile.mapper

import de.tigrao.github.domain.profile.model.LanguageModel
import de.tigrao.github.domain.profile.model.RepositoryModel
import dev.tigrao.github.fragment.LanguageFragment
import dev.tigrao.github.fragment.RepositoryFragment
import io.mockk.every
import io.mockk.mockk
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Test

class RepositoryDataToModelMapperTest {

    private val languageMapper = mockk<LanguageDataToModelMapper>()

    private val subject = RepositoryDataToModelMapper(languageMapper)

    val languageModel = mockk<LanguageModel>()

    @Before
    fun setup() {

        every { languageMapper.mapFrom(any()) } returns languageModel
    }

    @Test
    fun mapFrom() {
        val result = subject.mapFrom(
            RepositoryFragment(
                id = "mock-id",
                name = "repo-name",
                primaryLanguage = RepositoryFragment.PrimaryLanguage(
                    __typename = "type",
                    languageFragment = LanguageFragment(
                        name = "name",
                        color = "color",
                    )
                ),
                description = "mock-description",
                owner = RepositoryFragment.Owner(
                    avatarUrl = "url",
                    login = "GabriellCosta",
                ),
                stargazerCount = 13,
            )
        )

        val expected = RepositoryModel(
            title = "repo-name",
            language = languageModel,
            description = "mock-description",
            image = "url",
            owner = "GabriellCosta",
            stars = 13,
        )

        assertEquals(expected, result)
    }
}
