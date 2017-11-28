group = "pl.edu.agh.to2"
version = "1.0-SNAPSHOT"

apply {
    plugin("java")
    plugin("application")
}

repositories {
    mavenCentral()
}

dependencies {
    testCompile("junit", "junit", "4.12")
}

configure<JavaPluginConvention> {
    sourceCompatibility = JavaVersion.VERSION_1_8

    // Include *.fxml files from source tree as resources
    sourceSets["main"].resources.srcDir("src/main/java")
}

configure<ApplicationPluginConvention> {
    mainClassName = "pl.edu.agh.to2.russianBank.RussianBank"
}
