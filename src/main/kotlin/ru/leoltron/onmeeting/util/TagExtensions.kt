package ru.leoltron.onmeeting.util

import ru.leoltron.onmeeting.model.TagModel
import ru.leoltron.onmeeting.model.TagViewModel
import ru.leoltron.onmeeting.model.database.Tag

fun Tag.toModel(): TagModel = TagModel(name, color, description)
fun Tag.toViewModel(): TagViewModel = TagViewModel(tagId, name, color, description)
fun Tag.updateFrom(tagModel: TagModel) {
    name = tagModel.name
    color = tagModel.color
    description = tagModel.description
}