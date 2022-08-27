package de.tigrao.github.domain.profile.mapper

import de.tigrao.github.domain.profile.model.UserProfileModel
import dev.tigrao.github.UserProfileQuery
import javax.inject.Inject

internal class ProfileSuccessMapper @Inject constructor(
    private val repositoryMapper: RepositoryDataToModelMapper,
) {

    fun mapFrom(from: UserProfileQuery.Data): UserProfileModel {
        val user = from.user
        return UserProfileModel(
            name = user.name,
            image = user.avatarUrl.toString(),
            nickName = user.login,
            description = user.email.ifEmpty { user.bio.orEmpty() },
            followers = user.followers.totalCount,
            following = user.following.totalCount,
            pinnedRepos = user.pinnedItems.edges.mapNotNull { edge ->
                edge?.node?.repositoryFragment?.let(repositoryMapper::mapFrom)
            },
            topRepos = user.repositories.nodes?.mapNotNull { edge ->
                edge?.repositoryFragment?.let(repositoryMapper::mapFrom)
            }.orEmpty(),
            starsRepo = user.starredRepositories.edges?.mapNotNull { edge ->
                edge?.node?.repositoryFragment?.let(repositoryMapper::mapFrom)
            }.orEmpty()
        )
    }
}
