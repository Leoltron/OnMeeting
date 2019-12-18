package ru.leoltron.onmeeting.validation

import javax.validation.ConstraintValidator
import javax.validation.ConstraintValidatorContext

class StringLengthValidator : ConstraintValidator<StringLength, String> {
    private var minLength: Int = 0
    private var maxLength: Int = 0

    override fun initialize(constraintAnnotation: StringLength) {
        minLength = constraintAnnotation.min
        maxLength = constraintAnnotation.max
    }

    override fun isValid(s: String?, constraintValidatorContext: ConstraintValidatorContext): Boolean =
            s==null || s.length in minLength..maxLength
}
