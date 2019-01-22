package org.krjura.devtools.features.bcrypt.validations

import javax.validation.Constraint
import javax.validation.ReportAsSingleViolation
import javax.validation.constraints.NotEmpty
import javax.validation.constraints.NotNull
import javax.validation.constraints.Size
import kotlin.reflect.KClass

@MustBeDocumented
@Constraint(validatedBy = [])
@Target(AnnotationTarget.FIELD)
@Retention(AnnotationRetention.RUNTIME)
@ReportAsSingleViolation
@NotNull
@NotEmpty
@Size(min = 1, max = 100)
annotation class BCryptDataConstraint(
    val message: String = "class.BCryptDataConstraint",
    val groups: Array<KClass<out Any>> = [],
    val payload: Array<KClass<out Any>> = []
)