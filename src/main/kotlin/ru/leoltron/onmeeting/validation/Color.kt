package ru.leoltron.onmeeting.validation

import javax.validation.Constraint
import javax.validation.Payload
import kotlin.reflect.KClass


@Target(AnnotationTarget.FIELD)
@kotlin.annotation.Retention(AnnotationRetention.RUNTIME)
@Constraint(validatedBy = [ColorValidator::class])
annotation class Color(val message: String = "Invalid color",
                       val groups: Array<KClass<*>> = [],
                       val payload: Array<KClass<out Payload>> = [])