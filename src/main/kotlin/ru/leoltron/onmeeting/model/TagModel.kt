package ru.leoltron.onmeeting.model

import ru.leoltron.onmeeting.validation.Color
import ru.leoltron.onmeeting.validation.StringLength

data class TagModel(@StringLength(min = 1, max = 50) val name: String,
                    @Color val color: String,
                    @StringLength(max = 255) val description: String? = "")