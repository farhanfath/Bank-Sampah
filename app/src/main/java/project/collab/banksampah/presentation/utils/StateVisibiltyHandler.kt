package project.collab.banksampah.presentation.utils

import androidx.compose.runtime.MutableState

fun MutableState<Boolean>.show() {
    value = true
}

fun MutableState<Boolean>.hide() {
    value = false
}

fun MutableState<Boolean>.toggle() {
    value = !value
}