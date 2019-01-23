package org.krjura.devtools.features.bcrypt.validations

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
@Max(15)
@Min(4)
annotation class BCryptIterationConstraint(
    val message: String = "class.BcryptIterationConstraint",
    val groups: Array<KClass<out Any>> = [],
    val payload: Array<KClass<out Any>> = []
)