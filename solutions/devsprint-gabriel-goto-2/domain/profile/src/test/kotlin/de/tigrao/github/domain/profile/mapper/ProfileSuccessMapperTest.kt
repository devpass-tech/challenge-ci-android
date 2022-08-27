package de.tigrao.github.domain.profile.mapper

import de.tigrao.github.domain.profile.model.UserProfileModel
import dev.tigrao.github.UserProfileQuery
import io.mockk.mockk
import org.junit.Assert.assertEquals
import org.junit.Test

class ProfileSuccessMapperTest {

    private val repositoryMapper = mockk<RepositoryDataToModelMapper>()

    private val subject = ProfileSuccessMapper(repositoryMapper)

    @Test
    fun mapFrom() {
        val result = subject.mapFrom(
            from = UserProfileQuery.Data(
                user = UserProfileQuery.User(
                    avatarUrl = "http url",
                    bio = "bio",
                    login = "GabriellCosta",
                    email = "email@email.com",
                    followers = UserProfileQuery.Followers(11),
                    following = UserProfileQuery.Following(13),
                    name = "name-mock",
                    pinnedItems = UserProfileQuery.PinnedItems(emptyList()),
                    repositories = UserProfileQuery.Repositories(emptyList()),
                    starredRepositories = UserProfileQuery.StarredRepositories(emptyList())
                )
            )
        )

        val expected = UserProfileModel(
            name = "name-mock",
            nickName = "GabriellCosta",
            image = "http url",
            description = "email@email.com",
            following = 13,
            followers = 11,
            pinnedRepos = emptyList(),
            starsRepo = emptyList(),
            topRepos = emptyList(),
        )

        assertEquals(expected, result)
    }
}
