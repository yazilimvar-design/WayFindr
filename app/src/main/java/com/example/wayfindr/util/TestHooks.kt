package com.example.wayfindr.util

import android.content.Context
import java.io.File

/**
 * CI-only test entry points. The shipped APK is a debug build, so BuildConfig.DEBUG alone is not
 * enough: the hooks also need a marker file in the app's private folder, which only the app itself
 * or `adb shell run-as` (the CI smoke test) can create — other apps on the phone cannot.
 */
object TestHooks {
    @JvmStatic
    fun on(ctx: Context): Boolean =
        com.example.wayfindr.BuildConfig.DEBUG && File(ctx.filesDir, "test_hooks").exists()

    private val SAFE = Regex("^[A-Za-z0-9._-]{1,64}$")
    @JvmStatic
    fun safeName(name: String?): Boolean = name != null && SAFE.matches(name) && name != "." && name != ".."
}
