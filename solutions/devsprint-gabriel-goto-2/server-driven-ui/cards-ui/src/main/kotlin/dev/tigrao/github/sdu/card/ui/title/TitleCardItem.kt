package dev.tigrao.github.sdu.card.ui.title

import android.graphics.Paint
import android.view.View
import com.xwray.groupie.viewbinding.BindableItem
import de.tigrao.github.sdu.card.model.title.TitleCardModel
import dev.tigrao.github.sdu.card.ui.R
import dev.tigrao.github.sdu.card.ui.databinding.CardsTitleBinding

internal class TitleCardItem(
    private val card: TitleCardModel
) : BindableItem<CardsTitleBinding>() {

    override fun bind(viewBinding: CardsTitleBinding, position: Int) {
        with(viewBinding) {
            txtTitle.text = card.title
            btnAction.text = card.action.text

            btnAction.paintFlags = btnAction.paintFlags + Paint.UNDERLINE_TEXT_FLAG
        }
    }

    override fun getLayout() = R.layout.cards_title

    override fun initializeViewBinding(view: View) = CardsTitleBinding.bind(view)
}
