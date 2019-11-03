package ru.leoltron.onmeeting.util

import ru.leoltron.onmeeting.model.CardViewModel
import ru.leoltron.onmeeting.model.database.Card
import ru.leoltron.onmeeting.model.database.Tag
import ru.leoltron.onmeeting.model.database.User

fun Card.toModel(): CardViewModel = CardViewModel(
        title,
        user!!.username,
        cardId,
        locationString,
        startDate,
        endDate,
        participants.map(User::toModel),
        tags.map(Tag::toViewModel))