import io.izzel.taboolib.gradle.*
import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

plugins {
    java
    id("io.izzel.taboolib") version "2.0.22"
    id("org.jetbrains.kotlin.jvm") version "1.9.22"
}

taboolib {
    env {
        // 安装模块
        install(Basic, Bukkit, BukkitNMS, BukkitNMSUtil, MinecraftEffect, CommandHelper)
    }
    version { taboolib = "6.2.3" }
}

repositories {
    mavenCentral()
}

dependencies {
    compileOnly("ink.ptms.core:v11200:11200")
    compileOnly(kotlin("stdlib"))
    compileOnly(fileTree("libs"))
}

tasks.withType<JavaCompile> {
    options.encoding = "UTF-8"
}

tasks.withType<KotlinCompile> {
    kotlinOptions {
        jvmTarget = "1.8"
        freeCompilerArgs = listOf("-Xjvm-default=all")
    }
}

configure<JavaPluginConvention> {
    sourceCompatibility = JavaVersion.VERSION_1_8
    targetCompatibility = JavaVersion.VERSION_1_8
}
