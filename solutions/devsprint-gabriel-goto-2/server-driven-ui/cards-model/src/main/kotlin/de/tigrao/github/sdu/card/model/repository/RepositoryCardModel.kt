package de.tigrao.github.sdu.card.model.repository

import de.tigrao.github.sdu.card.model.CardModel

data class RepositoryCardModel(
    val image: String,
    val name: String,
    val title: String,
    val description: String,
    val stars: String,
    val language: LanguageModel?,
    val cardSize: CardSize,
) : CardModel

data class LanguageModel(
    val language: String,
    val color: String,
) : CardModel

enum class CardSize {
    FULL, MINI
}
