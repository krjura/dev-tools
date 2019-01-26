package org.krjura.devtools.validations

import javax.validation.Constraint
import javax.validation.ReportAsSingleViolation
import javax.validation.constraints.NotNull
import kotlin.reflect.KClass

@MustBeDocumented
@Constraint(validatedBy = [])
@Target(AnnotationTarget.FIELD)
@Retention(AnnotationRetention.RUNTIME)
@ReportAsSingleViolation
@NotNull
annotation class Required(
    val message: String = "alerts.backend.constraints.class.Required",
    val groups: Array<KClass<out Any>> = [],
    val payload: Array<KClass<out Any>> = []
)