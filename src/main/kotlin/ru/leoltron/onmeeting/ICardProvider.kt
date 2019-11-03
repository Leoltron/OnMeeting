package ru.leoltron.onmeeting

import ru.leoltron.onmeeting.model.CardViewModel

interface ICardProvider {
    fun getByTag(tagId: Int): List<CardViewModel>
    fun getByParticipant(userId: Int): List<CardViewModel>
    fun getByTagAndParticipant(userId: Int, tagId: Int): List<CardViewModel>
}