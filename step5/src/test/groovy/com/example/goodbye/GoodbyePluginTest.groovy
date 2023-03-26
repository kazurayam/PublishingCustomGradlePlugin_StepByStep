package com.example.goodbye

import org.gradle.api.Project
import org.gradle.testfixtures.ProjectBuilder
import spock.lang.Specification

class GoodbyePluginTest extends Specification {
    def "the plugin registers the tasks"() {
        given:
        Project project = ProjectBuilder.builder().build()

        when:
        project.plugins.apply("org.sample.Goodbye")

        then:
        project.tasks.findByName("goodbye") != null
    }
}
