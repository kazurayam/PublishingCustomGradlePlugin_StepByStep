# Publishing Custom Gradle Plugin explained step by step

kazurayam,
26 March 2023

## Introduction

In this article I will explain how to publish custom Gradle plugins. I will present working sample codes. I will explain what sort of artifacts (files) are generated in the `<projectDir>/build` directory by the `java-gradle-plugin` and the `maven-publish` plugin.

I have made a public GitHub repository of this article and sample codes

-   <https://github.com/kazurayam/PublishingCustomGradlePlugin_StepByStep>

I used the gradle version 8.0.2, Java 17.0.7, macOS 12.6

step1: want to write a set of Groovy code as a custom Gradle plugin

step2: want to compile a custom Gradle plugin, want to run JUnit5 test for it

step3 : want to publish the plugin artifact into a local Maven repository

step4 : want to publish artifacts into 2 types of repositories: Maven and Ivy

step5 : want to publish multiple custom plugins out of a single project

step6 : want to specify the name of jar file.

step7 : want to publish the sources.jar and the javadoc.jar.
