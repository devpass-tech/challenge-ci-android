package dev.tigrao.github.sdu.card.ui.processor

import androidx.recyclerview.widget.RecyclerView
import com.xwray.groupie.GroupieAdapter
import de.tigrao.github.sdu.card.model.CardModel
import dev.tigrao.github.sdu.card.processor.CardProcessor
import javax.inject.Inject

internal class CardProcessorImpl @Inject constructor(
    private val cardToItemMapper: CardToItemMapper
) : CardProcessor {

    override fun process(cards: List<CardModel>, recyclerView: RecyclerView) {
        val adapter = GroupieAdapter()

        val listOfItems = cardToItemMapper.mapFrom(cards, this)

        listOfItems.asSequence().forEach {
            adapter.add(it)
        }

        recyclerView.adapter = adapter
    }
}
