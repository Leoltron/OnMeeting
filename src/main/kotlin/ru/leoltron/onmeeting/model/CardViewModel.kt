package ru.leoltron.onmeeting.model

import com.fasterxml.jackson.annotation.JsonInclude
import java.sql.Timestamp

@JsonInclude(JsonInclude.Include.NON_NULL)
data class CardViewModel(
        val title: String,
        val username: String,
        val cardId: Int,
        val locationString: String?,
        val startDate: Timestamp?,
        val endDate: Timestamp?,
        val participants: List<UserModel>,
        val tags: List<TagViewModel>)
