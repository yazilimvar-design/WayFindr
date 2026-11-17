// BRouter routing engine (https://github.com/abrensch/brouter, MIT, see LICENSE), vendored from master
// commit fed8f185 (2026-11-06, after v1.7.9): the pure-Java modules brouter-util, -codec, -expressions, -mapaccess and -core, unchanged.
// The app routes in-process with it from downloaded .rd5 segments (dash/nav/BRouterEngine.kt).
plugins {
    `java-library`
}

java {
    sourceCompatibility = JavaVersion.VERSION_11
    targetCompatibility = JavaVersion.VERSION_11
}

tasks.withType<JavaCompile>().configureEach {
    options.encoding = "UTF-8"
    options.compilerArgs.add("-nowarn")
}
