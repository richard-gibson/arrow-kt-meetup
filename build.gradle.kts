

plugins {
    kotlin("jvm").version("1.3.61")
    kotlin("kapt").version("1.3.61")
}


group = "io.hexlabs"
version = "1.0-SNAPSHOT"


val arrowVersion = "0.10.3"

repositories {
    jcenter()
    mavenCentral()
}

dependencies {
    implementation("org.jetbrains.kotlin:kotlin-stdlib-jdk8")
    implementation(group = "io.arrow-kt", name = "arrow-core", version = arrowVersion)
    implementation(group = "io.arrow-kt", name = "arrow-optics", version = arrowVersion)
    implementation(group = "io.arrow-kt", name = "arrow-syntax", version = arrowVersion)
    implementation(group = "io.arrow-kt", name = "arrow-fx", version = arrowVersion)
    implementation(group = "io.arrow-kt", name = "arrow-fx-rx2", version = arrowVersion)

    kapt(group = "io.arrow-kt", name = "arrow-meta", version = arrowVersion)

}

