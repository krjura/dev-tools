package org.krjura.devtools.support

import org.springframework.test.context.ActiveProfilesResolver

class ProjectActiveProfilesResolver: ActiveProfilesResolver {

    companion object {
        const val SPRING_PROFILES_ACTIVE = "spring.profiles.active"
        const val PROFILE_SEPARATOR = ","
    }

    override fun resolve(testClass: Class<*>): Array<String> {
        val activeProfiles = System.getProperty(SPRING_PROFILES_ACTIVE)

        if( activeProfiles == null || activeProfiles.isEmpty() ) {
            return arrayOf("test")
        }

        return activeProfiles.split(PROFILE_SEPARATOR).toTypedArray();

    }

}