package com.sylvia.h_medi.presentation.ui.theme

import androidx.compose.material.MaterialTheme
import androidx.compose.material.lightColors
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color

private val ColorPalette = lightColors(
    primary = MyBlue,
    secondary = Color.White,
    onPrimary = Color.White,
    background = Background
)



@Composable
fun HMediTheme(content: @Composable() () -> Unit) {

    MaterialTheme(
        colors = ColorPalette,
        typography = Typography,
        shapes = Shapes,
        content = content
    )
}