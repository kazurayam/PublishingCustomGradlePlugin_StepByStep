package com.example.greeting

import org.gradle.api.Plugin
import org.gradle.api.Project

class GreetingPlugin implements Plugin<Project> {
    void apply(Project project) {
        project.task('hello') {
            doLast {
                println "Hello from GreetingPlugin"
            }
        }
        project.task('goodbye') {
            doLast {
                println "Goodbye from GreetingPlugin"
            }
        }
    }
}

