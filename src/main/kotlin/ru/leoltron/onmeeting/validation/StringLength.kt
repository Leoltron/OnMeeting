package ru.leoltron.onmeeting.validation

import javax.validation.Constraint
import javax.validation.Payload
import kotlin.reflect.KClass

@Target(AnnotationTarget.FIELD)
@Retention(AnnotationRetention.RUNTIME)
@Constraint(validatedBy = [StringLengthValidator::class])
annotation class StringLength(
        val message: String = "Invalid string length",
        val min: Int = 0,
        val max: Int = Integer.MAX_VALUE,
        val groups: Array<KClass<*>> = [],
        val payload: Array<KClass<out Payload>> = [])
