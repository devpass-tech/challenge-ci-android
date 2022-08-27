package de.tigrao.github.sdu.card.model.title

import de.tigrao.github.sdu.card.model.CardModel

data class TitleCardModel(
    val title: String,
    val action: ClickActionTitleModel,
) : CardModel

data class ClickActionTitleModel(
    val text: String,
)
