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
    compile("com.google.guava:guava:23.5-jre")
    compile("io.javalin", "javalin", "1.2.0")
    compile("org.apache.logging.log4j", "log4j-api", "2.10.0")
    compile("org.apache.logging.log4j", "log4j-core", "2.10.0")
    compile("org.apache.logging.log4j", "log4j-slf4j-impl", "2.10.0")
    compile("org.eclipse.jetty.websocket", "websocket-server", "9.4.8.v20171121")
    compile("org.eclipse.jetty.websocket", "websocket-client", "9.4.8.v20171121")

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
