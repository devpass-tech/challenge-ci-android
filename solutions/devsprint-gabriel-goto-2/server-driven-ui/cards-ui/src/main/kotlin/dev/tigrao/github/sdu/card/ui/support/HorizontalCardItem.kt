package dev.tigrao.github.sdu.card.ui.support

import android.view.View
import androidx.recyclerview.widget.LinearLayoutManager
import com.xwray.groupie.viewbinding.BindableItem
import de.tigrao.github.sdu.card.model.support.HorizontalCardModel
import dev.tigrao.github.sdu.card.processor.CardProcessor
import dev.tigrao.github.sdu.card.ui.R
import dev.tigrao.github.sdu.card.ui.databinding.CardsHorizontalBinding

internal class HorizontalCardItem(
    private val card: HorizontalCardModel,
    private val cardProcessor: CardProcessor,
) : BindableItem<CardsHorizontalBinding>() {

    override fun bind(viewBinding: CardsHorizontalBinding, position: Int) {
        with(viewBinding.recycler) {
            layoutManager =
                LinearLayoutManager(viewBinding.recycler.context, LinearLayoutManager.HORIZONTAL, false)

            cardProcessor.process(card.cards, this)

            addItemDecoration(CarouselItemDecoration())
        }
    }

    override fun getLayout() = R.layout.cards_horizontal

    override fun initializeViewBinding(view: View) = CardsHorizontalBinding.bind(view)
}
