package com.miftah.sehaty.ui.theme

import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.compositionLocalOf
import androidx.compose.runtime.remember

@Composable
fun ProvideAppUtils(
    appDimens: Dimens,
    content: @Composable () -> Unit,
) {
    val appDimen = remember { appDimens }
    CompositionLocalProvider(LocalAppDimens provides appDimen) {
        content()
    }
}

val LocalAppDimens = compositionLocalOf { Dimens() }

val MaterialTheme.dimens : Dimens
    @Composable
    get() = LocalAppDimens.current