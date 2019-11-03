package ru.leoltron.onmeeting.util

import ru.leoltron.onmeeting.model.CardAddOrEditModel
import ru.leoltron.onmeeting.model.database.Card

fun CardAddOrEditModel.toCard(userId: Int): Card = Card(title, userId, locationString, startDate, endDate)
