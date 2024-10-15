package com.rickaban.android.getrandomusers.app.ui.theme

enum class Theme(val value: Int){
    SYSTEM(0),
    LIGHT(1),
    DARK(2);
}

fun Int.theme() = when(this){
    Theme.SYSTEM.value -> Theme.SYSTEM
    Theme.LIGHT.value -> Theme.LIGHT
    Theme.DARK.value -> Theme.DARK
    else -> throw IllegalArgumentException("Invalid theme $this")
}        