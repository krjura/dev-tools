package org.krjura.devtools.features.password.validations

import javax.validation.Constraint
import javax.validation.ReportAsSingleViolation
import javax.validation.constraints.Max
import javax.validation.constraints.Min
import javax.validation.constraints.NotNull
import kotlin.reflect.KClass

@MustBeDocumented
@Constraint(validatedBy = [])
@Target(AnnotationTarget.FIELD)
@Retention(AnnotationRetention.RUNTIME)
@ReportAsSingleViolation
@NotNull
@Min(1)
@Max(40)
annotation class PasswordCharacterCount(
    val message: String = "alerts.backend.constraints.class.PasswordCharacterCount",
    val groups: Array<KClass<out Any>> = [],
    val payload: Array<KClass<out Any>> = []
)