package ru.leoltron.onmeeting.validation

import javax.validation.ConstraintValidator
import javax.validation.ConstraintValidatorContext

class ColorValidator : ConstraintValidator<Color, String> {
    override fun isValid(s: String, constraintValidatorContext: ConstraintValidatorContext): Boolean =
            s.matches("^[0-9a-fA-F]{6}$".toRegex())
}
