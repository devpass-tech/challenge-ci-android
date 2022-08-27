package dev.tigrao.github.sdu.card.ui.profile

import android.view.View
import com.xwray.groupie.viewbinding.BindableItem
import de.tigrao.github.sdu.card.model.profile.ProfileCardModel
import dev.tigrao.github.sdu.card.ui.R
import dev.tigrao.github.sdu.card.ui.databinding.CardsUserProfileBinding
import dev.tigrao.github.sdu.card.ui.support.loadImage

internal class ProfileCardItem(
    private val profileCardModel: ProfileCardModel,
) : BindableItem<CardsUserProfileBinding>() {

    override fun bind(viewBinding: CardsUserProfileBinding, position: Int) {
        with(viewBinding) {
            cardImage.loadImage(profileCardModel.image)

            txtName.text = profileCardModel.title
            txtNick.text = profileCardModel.subtitle

            txtBottomText.text = profileCardModel.bottomText

            txtContact.text = profileCardModel.contact
        }
    }

    override fun getLayout() = R.layout.cards_user_profile

    override fun initializeViewBinding(view: View) = CardsUserProfileBinding.bind(view)
}
