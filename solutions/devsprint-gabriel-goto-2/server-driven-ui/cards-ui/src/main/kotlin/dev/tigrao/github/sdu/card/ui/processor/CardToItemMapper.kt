package dev.tigrao.github.sdu.card.ui.processor

import de.tigrao.github.sdu.card.model.CardModel
import de.tigrao.github.sdu.card.model.profile.ProfileCardModel
import de.tigrao.github.sdu.card.model.repository.RepositoryCardModel
import de.tigrao.github.sdu.card.model.support.HorizontalCardModel
import de.tigrao.github.sdu.card.model.title.TitleCardModel
import dev.tigrao.github.sdu.card.ui.profile.ProfileCardItem
import dev.tigrao.github.sdu.card.ui.repository.RepositoryCardItem
import dev.tigrao.github.sdu.card.ui.support.HorizontalCardItem
import dev.tigrao.github.sdu.card.ui.title.TitleCardItem
import javax.inject.Inject

internal class CardToItemMapper @Inject constructor() {

    fun mapFrom(
        cards: List<CardModel>,
        cardProcessorImpl: CardProcessorImpl
    ) = cards.mapNotNull { card ->
        when (card) {
            is ProfileCardModel -> {
                ProfileCardItem(card)
            }
            is RepositoryCardModel -> {
                RepositoryCardItem(card)
            }
            is TitleCardModel -> {
                TitleCardItem(card)
            }
            is HorizontalCardModel -> {
                HorizontalCardItem(card, cardProcessorImpl)
            }
            else -> null
        }
    }
}
