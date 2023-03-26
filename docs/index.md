-   <a href="#publishing-custom-gradle-plugin-explained-step-by-step" id="toc-publishing-custom-gradle-plugin-explained-step-by-step">Publishing Custom Gradle Plugin explained step by step</a>
    -   <a href="#introduction" id="toc-introduction">Introduction</a>
    -   <a href="#1-start-up" id="toc-1-start-up">1 Start up</a>
        -   <a href="#overview" id="toc-overview">Overview</a>
        -   <a href="#settings-gradle-and-build-gradle" id="toc-settings-gradle-and-build-gradle">settings.gradle and build.gradle</a>
            -   <a href="#step1settings-gradle" id="toc-step1settings-gradle">step1/settings.gradle</a>
            -   <a href="#step1build-gradle" id="toc-step1build-gradle">step1/build.gradle</a>
        -   <a href="#code-of-custom-gradle-plugin" id="toc-code-of-custom-gradle-plugin">code of custom Gradle plugin</a>
        -   <a href="#code-of-junit5-test" id="toc-code-of-junit5-test">code of JUnit5 test</a>
        -   <a href="#how-the-build-works" id="toc-how-the-build-works">How the build works</a>
        -   <a href="#what-needs-to-be-done" id="toc-what-needs-to-be-done">What needs to be done</a>

# Publishing Custom Gradle Plugin explained step by step

kazurayam,
26 March 2023

## Introduction

In this article I will explain how to publish custom Gradle plugins. I will present working sample codes. I will explain what sort of artifacts (files) are generated in the `<projectDir>/build` directory by the `java-gradle-plugin` and the `maven-publish` plugin.

I have made a public GitHub repository of this article and sample codes

-   <https://github.com/kazurayam/PublishingCustomGradlePlugin_StepByStep>

I used the gradle version 8.0.2, Java 17.0.7, macOS 12.6

## 1 Start up

### Overview

In the step1, I will create a skeletal project where I will write a Groovy code as a custom Gradle plugin.

### settings.gradle and build.gradle

I made a directory named `step1` where I located `step1/settings.gradle` and `step1/build.gradle`.

#### step1/settings.gradle

    rootProject.name = 'step1'

#### step1/build.gradle

    plugins {
        id 'groovy'
    }

    group = 'com.example'
    version = '1.0'

    repositories {
        mavenCentral()
    }

    dependencies {
        // Use the awesome Spock testing and specification framework
        testImplementation 'org.spockframework:spock-core:2.3-groovy-3.0'
    }

    tasks.named('test') {
        // Use JUnit Jupiter for unit tests.
        useJUnitPlatform()
    }

### code of custom Gradle plugin

I wrote `step1/src/main/groovy/com/example/greeting/GreetingPlguin`.

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

### code of JUnit5 test

`step1/src/test/groovy/com/example/greeting/GreetingPluginTest.groovy`

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

### How the build works

I tried to compile the Groovy code, but it doesn’t compile.

![1 1.GradleApiNotAccessible](images/1_1.GradleApiNotAccessible.png)

The Gradle API is not accessible for the compiler. Therefore, Groovy compiler failed to find the very core class `org.gradle.api.Project`.

### What needs to be done

In order to compile the code, I need to introduce the [java-gradle-plugin](https://docs.gradle.org/current/userguide/java_gradle_plugin.html) into the `build.gradle`. I will try it in the step2.

step2: want to compile a custom Gradle plugin, want to run JUnit5 test for it

step3 : want to publish the plugin artifact into a local Maven repository

step4 : want to publish artifacts into 2 types of repositories: Maven and Ivy

step5 : want to publish multiple custom plugins out of a single project

step6 : want to specify the name of jar file.

step7 : want to publish the sources.jar and the javadoc.jar.
