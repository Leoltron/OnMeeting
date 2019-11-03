package ru.leoltron.onmeeting.util

import ru.leoltron.onmeeting.repo.UserRepository
import java.security.Principal

fun Principal.getId(userRepository: UserRepository): Int? = userRepository.findByUsername(name).firstOrNull()?.userId