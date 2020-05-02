workspace(name = "json_formatter")

load("//jdk:jdks.bzl", "jdk_repositories")

jdk_repositories()

load("@bazel_tools//tools/build_defs/repo:jvm.bzl", "jvm_maven_import_external")

jvm_maven_import_external(
    name = "junit_junit",
    artifact = "junit:junit:4.13",
    artifact_sha256 = "4b8532f63bdc0e0661507f947eb324a954d1dbac631ad19c8aa9a00feed1d863",
    server_urls = ["https://repo.maven.apache.org/maven2"],
)

load("@bazel_tools//tools/build_defs/repo:http.bzl", "http_archive")

http_archive(
    name = "jdk11_linux",
    build_file = "@local_jdk//:BUILD.bazel",
    sha256 = "ee60304d782c9d5654bf1a6b3f38c683921c1711045e1db94525a51b7024a2ca",
    strip_prefix = "jdk-11.0.7+10",
    urls = [
        "https://github.com/AdoptOpenJDK/openjdk11-binaries/releases/download/jdk-11.0.7%2B10/OpenJDK11U-jdk_x64_linux_hotspot_11.0.7_10.tar.gz",
    ],
)

http_archive(
    name = "jdk11_macos",
    build_file = "@local_jdk//:BUILD.bazel",
    sha256 = "0ab1e15e8bd1916423960e91b932d2b17f4c15b02dbdf9fa30e9423280d9e5cc",
    strip_prefix = "jdk-11.0.7+10",
    urls = [
        "https://github.com/AdoptOpenJDK/openjdk11-binaries/releases/download/jdk-11.0.7%2B10/OpenJDK11U-jdk_x64_mac_hotspot_11.0.7_10.tar.gz",
    ],
)
