package de.tigrao.github.domain.profile.model

data class UserProfileModel(
    val name: String,
    val nickName: String,
    val image: String,
    val description: String,
    val following: Int,
    val followers: Int,
    val pinnedRepos: List<RepositoryModel>,
    val topRepos: List<RepositoryModel>,
    val starsRepo: List<RepositoryModel>,
)

data class RepositoryModel(
    val owner: String,
    val image: String,
    val stars: Int,
    val language: LanguageModel?,
    val title: String,
    val description: String,
)

data class LanguageModel(
    val name: String,
    val color: String,
)
