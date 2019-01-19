package org.krjura.devtools.support

import org.springframework.boot.test.context.SpringBootTest
import org.springframework.test.context.ActiveProfiles
import java.lang.annotation.Inherited

@Target(AnnotationTarget.CLASS)
@Retention(AnnotationRetention.RUNTIME)
@Inherited
@SpringBootTest
@ActiveProfiles( resolver = ProjectActiveProfilesResolver::class)
annotation class ProjectTest