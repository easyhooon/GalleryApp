@file:Suppress("UnstableApiUsage", "INLINE_FROM_HIGHER_PLATFORM")
plugins {
    daangn("jvm-kotlin")
}

dependencies {
    implementations(
        libs.androidx.paging.common,
        libs.javax.inject
    )
}
