load(
    "@bazel_tools//tools/jdk:default_java_toolchain.bzl",
    "DEFAULT_JAVACOPTS",
    "JDK8_JVM_OPTS",
    "JDK9_JVM_OPTS",
    "default_java_toolchain",
)

alias(
    name = "jdk8",
    actual = select(
        {
            "@bazel_tools//src/conditions:linux_x86_64": "@jdk8_linux//:jdk",
            "@bazel_tools//src/conditions:darwin": "@jdk8_macos//:jdk",
            "@bazel_tools//src/conditions:darwin_x86_64": "@jdk8_macos//:jdk",
        },
        no_match_error = "Could not find a JDK for host execution environment, please explicitly" +
                         " provide one using `--host_javabase.`",
    ),
    visibility = ["//visibility:public"],
)

alias(
    name = "jdk11",
    actual = select(
        {
            "@bazel_tools//src/conditions:linux_x86_64": "@jdk11_linux//:jdk",
            "@bazel_tools//src/conditions:darwin": "@jdk11_macos//:jdk",
            "@bazel_tools//src/conditions:darwin_x86_64": "@jdk11_macos//:jdk",
        },
        no_match_error = "Could not find a JDK for host execution environment, please explicitly" +
                         " provide one using `--host_javabase.`",
    ),
    visibility = ["//visibility:public"],
)

default_java_toolchain(
    name = "java8_toolchain",
    bootclasspath = ["@bazel_tools//tools/jdk:platformclasspath"],
    jvm_opts = JDK8_JVM_OPTS,
    source_version = "8",
    target_version = "8",
    visibility = ["//visibility:public"],
)

default_java_toolchain(
    name = "java11_for8_toolchain",
    bootclasspath = ["@bazel_tools//tools/jdk:platformclasspath"],
    jvm_opts = JDK9_JVM_OPTS,
    source_version = "8",
    target_version = "8",
    visibility = ["//visibility:public"],
)

default_java_toolchain(
    name = "java11_toolchain",
    bootclasspath = ["@bazel_tools//tools/jdk:platformclasspath"],
    jvm_opts = JDK9_JVM_OPTS,
    source_version = "11",
    target_version = "11",
    visibility = ["//visibility:public"],
)
