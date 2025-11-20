package com.swordfish.lemuroid.app.mobile.shared.compose.ui

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import com.swordfish.lemuroid.app.shared.systems.MetaSystemInfo

@Composable
fun LemuroidSystemImage(system: MetaSystemInfo) {
    val systemColor = Color(system.metaSystem.color())

    Box(
        modifier = Modifier
            .fillMaxWidth()
            .aspectRatio(1.0f)
            .background(systemColor),
        contentAlignment = Alignment.Center,
    ) {
        // Efeito de Brilho Diagonal Forte (Vidro)
        Box(
            modifier = Modifier
                .fillMaxSize()
                .background(
                    brush = Brush.linearGradient(
                        colors = listOf(
                            Color.White.copy(alpha = 0.4f), // Canto superior esquerdo bem claro
                            Color.Transparent,              // Meio transparente
                            Color.Black.copy(alpha = 0.5f)  // Canto inferior direito escuro
                        )
                    )
                )
        )

        Image(
            modifier = Modifier.fillMaxSize(0.65f),
            painter = painterResource(id = system.metaSystem.imageResId),
            contentDescription = stringResource(id = system.metaSystem.titleResId),
            contentScale = ContentScale.Fit,
        )
    }
}
