package com.example.greeting

import org.gradle.api.Project
import org.gradle.testfixtures.ProjectBuilder
import spock.lang.Specification

class GreetingPluginTest extends Specification {
    def "the plugin registers the tasks"() {
        given:
        Project project = ProjectBuilder.builder().build()

        when:
        project.plugins.apply("org.sample.Greetings")

        then:
        project.tasks.findByName("hello") != null
        project.tasks.findByName("goodbye") != null

    }
}
