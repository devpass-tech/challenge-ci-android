package me.tigrao.github.main.domain.mapper

import android.content.res.Resources
import de.tigrao.github.domain.profile.model.UserProfileModel
import de.tigrao.github.sdu.card.model.profile.ProfileCardModel
import me.tigrao.github.main.R
import javax.inject.Inject

internal class UserProfileToCardMapper @Inject constructor(
    private val resources: Resources
) {

    fun mapFrom(it: UserProfileModel) =
        ProfileCardModel(
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
}
