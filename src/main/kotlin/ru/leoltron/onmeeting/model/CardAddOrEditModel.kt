package ru.leoltron.onmeeting.model

import java.sql.Timestamp

data class CardAddOrEditModel(
        val title: String,
        val locationString: String?,
        val startDate: Timestamp?,
        val endDate: Timestamp?,
        val participantsIds: List<Int>,
        val tagIds: List<Int>)