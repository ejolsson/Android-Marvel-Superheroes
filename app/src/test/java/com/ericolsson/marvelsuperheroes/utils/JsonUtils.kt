package com.ericolsson.marvelsuperheroes.utils

import java.io.File

internal fun getJson(path: String): String {
    val uri = com.google.common.io.Resources.getResource(path)
    val file = File(uri.path)
    return String(file.readBytes())
}
