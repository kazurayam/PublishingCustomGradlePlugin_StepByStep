package com.example.goodbye

import org.gradle.api.Plugin
import org.gradle.api.Project

class GoodbyePlugin  implements Plugin<Project> {
    void apply(Project project) {
        project.task('goodbye') {
            doLast {
                println "Goodbye from GoodbyePlugin"
            }
        }
    }
}
