package com.example.hello

import org.gradle.api.Plugin
import org.gradle.api.Project

class HelloPlugin  implements Plugin<Project> {
    void apply(Project project) {
        project.task('hello') {
            doLast {
                println "Hello from HelloPlugin"
            }
        }
    }
}
