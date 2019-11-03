package ru.leoltron.onmeeting.util

import ru.leoltron.onmeeting.model.UserModel
import ru.leoltron.onmeeting.model.database.User

fun User.toModel(): UserModel = UserModel(userId, username)
