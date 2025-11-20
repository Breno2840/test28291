package com.swordfish.touchinput.radial.controls

import androidx.compose.foundation.Canvas
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.runtime.State
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.CornerRadius
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.Color
import com.swordfish.touchinput.radial.LocalLemuroidPadTheme
import gg.padkit.PadKitScope
import gg.padkit.controls.ControlCross
import gg.padkit.ids.Id

context(PadKitScope)
@Composable
fun LemuroidControlCross(
    modifier: Modifier = Modifier,
    id: Id.DiscreteDirection,
    allowDiagonals: Boolean = true,
    background: @Composable () -> Unit = {
        Canvas(modifier = Modifier.fillMaxSize()) {
            val w = size.width
            val h = size.height
            val thickness = w / 3f
            val cornerRadius = 12f
            val dpadColor = Color(0xFF383838)

            drawRoundRect(
                color = dpadColor,
                topLeft = Offset((w - thickness) / 2, 0f),
                size = Size(thickness, h),
                cornerRadius = CornerRadius(cornerRadius, cornerRadius)
            )

            drawRoundRect(
                color = dpadColor,
                topLeft = Offset(0f, (h - thickness) / 2),
                size = Size(w, thickness),
                cornerRadius = CornerRadius(cornerRadius, cornerRadius)
            )
        }
    },
    foreground: @Composable (State<Offset>) -> Unit = { directionState ->
        val offset = directionState.value
        Canvas(modifier = Modifier.fillMaxSize()) {
            val w = size.width
            val h = size.height
            val thickness = w / 3f
            val highlightColor = Color(0x40FFFFFF)
            val threshold = 0.2f

            if (offset.y < -threshold) {
                drawRect(
                    color = highlightColor,
                    topLeft = Offset((w - thickness) / 2, 0f),
                    size = Size(thickness, h / 2)
                )
            }
            if (offset.y > threshold) {
                drawRect(
                    color = highlightColor,
                    topLeft = Offset((w - thickness) / 2, h / 2),
                    size = Size(thickness, h / 2)
                )
            }
            if (offset.x < -threshold) {
                drawRect(
                    color = highlightColor,
                    topLeft = Offset(0f, (h - thickness) / 2),
                    size = Size(w / 2, thickness)
                )
            }
            if (offset.x > threshold) {
                drawRect(
                    color = highlightColor,
                    topLeft = Offset(w / 2, (h - thickness) / 2),
                    size = Size(w / 2, thickness)
                )
            }
        }
    }
) {
    val theme = LocalLemuroidPadTheme.current
    ControlCross(
        modifier = modifier.padding(theme.padding),
        id = id,
        allowDiagonals = allowDiagonals,
        background = background,
        foreground = foreground
    )
}
