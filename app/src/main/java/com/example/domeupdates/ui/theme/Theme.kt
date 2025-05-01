package com.example.domeupdates.ui.theme

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.darkColorScheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import androidx.compose.material3.Typography


private val DarkColorScheme = darkColorScheme(
    primary = OrangeColor,
    onPrimary = BlackColor,
    secondary = LightOrangeColor,
    onSecondary = BlackColor,
    background = BlackColor,
    onBackground = Color.White,
    surface = BlackColor,
    onSurface = Color.White,
)

private val LightColorScheme = lightColorScheme(
    primary = OrangeColor,
    onPrimary = BlackColor,
    secondary = LightOrangeColor,
    onSecondary = BlackColor,
    background = Color.White,
    onBackground = BlackColor,
    surface = Color.White,
    onSurface = BlackColor,
)

@Composable
fun ThreadsAppTheme(
    darkTheme: Boolean = true,
    content: @Composable () -> Unit
) {
    val colors = if (darkTheme) DarkColorScheme else LightColorScheme

    MaterialTheme(
        colorScheme = colors,
        typography = Typography,
        content = content
    )
}
