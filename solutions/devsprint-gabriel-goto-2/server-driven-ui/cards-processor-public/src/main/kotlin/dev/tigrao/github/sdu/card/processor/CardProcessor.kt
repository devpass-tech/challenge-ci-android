package dev.tigrao.github.sdu.card.processor

import androidx.recyclerview.widget.RecyclerView
import de.tigrao.github.sdu.card.model.CardModel

interface CardProcessor {

    fun process(cards: List<CardModel>, recyclerView: RecyclerView)
}
