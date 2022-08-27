package me.tigrao.github.main.domain

import android.content.res.Resources
import br.com.hippopotamus.tabarato.designsystem.viewstate.StateViewArg
import br.com.hippopotamus.tabarato.designsystem.viewstate.StateViewType
import de.tigrao.github.domain.profile.GetProfileUseCase
import de.tigrao.github.domain.profile.model.RepositoryModel
import de.tigrao.github.sdu.card.model.CardModel
import de.tigrao.github.sdu.card.model.profile.ProfileCardModel
import de.tigrao.github.sdu.card.model.repository.CardSize
import de.tigrao.github.sdu.card.model.repository.LanguageModel
import de.tigrao.github.sdu.card.model.repository.RepositoryCardModel
import de.tigrao.github.sdu.card.model.support.HorizontalCardModel
import de.tigrao.github.sdu.card.model.title.ClickActionTitleModel
import de.tigrao.github.sdu.card.model.title.TitleCardModel
import deb.tigrao.github.infra.api.ResultDomain
import me.tigrao.github.main.R
import me.tigrao.github.main.domain.model.UserProfileUiErrorModel
import me.tigrao.github.main.domain.model.UserProfileUiModel
import javax.inject.Inject

internal interface FetchProfileDataUseCase {
    suspend operator fun invoke(
        userName: String,
        forced: Boolean
    ): ResultDomain<UserProfileUiModel, UserProfileUiErrorModel.GenericError>
}

internal class FetchProfileDataUseCaseDefault @Inject constructor(
    private val getProfileDataUseCase: GetProfileUseCase,
    private val resources: Resources,
) : FetchProfileDataUseCase {
    override suspend fun invoke(
        userName: String,
        forced: Boolean
    ): ResultDomain<UserProfileUiModel, UserProfileUiErrorModel.GenericError> {
        return getProfileDataUseCase(userName, forced).transformMap(success = {

            val responseList = mutableListOf<CardModel>()

            val profileCard: CardModel = ProfileCardModel(
                image = it.image,
                title = it.name,
                subtitle = it.nickName,
                contact = it.description,
                bottomText = resources.getString(
                    R.string.cards_followers_following,
                    it.followers,
                    it.following
                )
            )
            responseList.add(profileCard)

            val action = ClickActionTitleModel(
                text = resources.getString(R.string.cards_view_all_action),
            )

            val pinnedTitle: CardModel = TitleCardModel(
                title = resources.getString(R.string.cards_title_pinned), action = action
            )
            responseList.add(pinnedTitle)

            it.pinnedRepos.map { repo ->
                responseList.add(mapRepo(repo, CardSize.FULL))
            }

            val topTitle: CardModel = TitleCardModel(
                title = resources.getString(R.string.cards_title_top), action = action
            )
            responseList.add(topTitle)

            val topRepositories: List<CardModel> = it.topRepos.map { repo ->
                mapRepo(repo, CardSize.MINI)
            }
            responseList.add(HorizontalCardModel(topRepositories))

            val starredTitle: CardModel = TitleCardModel(
                title = resources.getString(R.string.cards_title_star), action = action
            )
            responseList.add(starredTitle)

            val starRepositories: List<CardModel> = it.starsRepo.map { repo ->
                mapRepo(repo, CardSize.MINI)
            }
            responseList.add(HorizontalCardModel(starRepositories))

            UserProfileUiModel(responseList)
        }, error = {
            val state = StateViewArg(
                type = StateViewType.Api(),
                title = resources.getString(R.string.profile_generic_error_title),
                description = resources.getString(R.string.profile_generic_error_description),
            )

            UserProfileUiErrorModel.GenericError(state = state)
        })
    }

    private fun mapRepo(repo: RepositoryModel, cardSize: CardSize): CardModel = RepositoryCardModel(
        image = repo.image,
        cardSize = cardSize,
        name = repo.owner,
        title = repo.title,
        description = repo.description,
        stars = repo.stars.toString(),
        language = repo.language?.let { language ->
            LanguageModel(
                language = language.name,
                color = language.color,
            )
        })
}
