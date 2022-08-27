package dev.tigrao.github.sdu.card.ui.repository

import android.content.res.ColorStateList
import android.graphics.Color
import android.graphics.drawable.GradientDrawable
import android.view.View
import android.widget.FrameLayout
import androidx.core.content.ContextCompat
import com.xwray.groupie.viewbinding.BindableItem
import de.tigrao.github.sdu.card.model.repository.CardSize
import de.tigrao.github.sdu.card.model.repository.RepositoryCardModel
import dev.tigrao.github.sdu.card.ui.R
import dev.tigrao.github.sdu.card.ui.databinding.CardsRepositoryBinding
import dev.tigrao.github.sdu.card.ui.support.loadImage

internal class RepositoryCardItem(
    private val card: RepositoryCardModel,
) : BindableItem<CardsRepositoryBinding>() {
    override fun bind(viewBinding: CardsRepositoryBinding, position: Int) {
        with(viewBinding) {
            cardImage.loadImage(card.image)

            this.txtTitle.text = card.title
            this.txtName.text = card.name
            this.txtDescription.text = card.description

            this.txtStars.text = card.stars

            this.txtLanguage.text = card.language?.language

            val dot = ContextCompat.getDrawable(root.context, R.drawable.cards_language_dot)
            card.language?.let {
                (dot as? GradientDrawable)?.color =
                    ColorStateList.valueOf(Color.parseColor(it.color))
            }

            txtLanguage.setCompoundDrawablesWithIntrinsicBounds(dot, null, null, null)

            when (card.cardSize) {
                CardSize.FULL -> this.root.layoutParams.width =
                    FrameLayout.LayoutParams.MATCH_PARENT
                CardSize.MINI -> this.root.layoutParams.width =
                    this.root.resources.getDimensionPixelSize(R.dimen.cards_repository_size_mini)
            }
        }
    }

    override fun getLayout() = R.layout.cards_repository

    override fun initializeViewBinding(view: View) = CardsRepositoryBinding.bind(view)
}
