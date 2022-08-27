package de.tigrao.github.sdu.card.model.profile

import de.tigrao.github.sdu.card.model.CardModel

data class ProfileCardModel(
    val image: String,
    val title: String,
    val subtitle: String,
    val contact: String,
    val bottomText: String,
) : CardModel
