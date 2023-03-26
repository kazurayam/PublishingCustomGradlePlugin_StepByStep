package com.example.hello

import org.gradle.api.Project
import org.gradle.testfixtures.ProjectBuilder
import spock.lang.Specification

class HelloPluginTest extends Specification {
    def "the plugin registers the tasks"() {
        given:
        Project project = ProjectBuilder.builder().build()

        when:
        project.plugins.apply("org.sample.Hello")

        then:
        project.tasks.findByName("hello") != null
    }
}
