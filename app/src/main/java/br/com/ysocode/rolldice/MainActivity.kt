package br.com.ysocode.rolldice

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Button
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.CornerRadius
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.drawscope.DrawScope
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import br.com.ysocode.rolldice.ui.theme.RollDiceTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            RollDiceTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { paddingValues ->
                    App(
                        modifier = Modifier.padding(paddingValues)
                    )
                }
            }
        }
    }
}

fun DrawScope.topLeftCircle(radius: Float, offset: Float) {
    drawCircle(
        Color.Black,
        radius,
        Offset(offset, offset)
    )
}

fun DrawScope.topRightCircle(radius: Float, offset: Float) {
    drawCircle(
        Color.Black,
        radius,
        Offset(this.size.width - offset, offset)
    )
}

fun DrawScope.centerCircle(radius: Float) {
    drawCircle(
        Color.Black,
        radius
    )
}

fun DrawScope.bottomLeftCircle(radius: Float, offset: Float) {
    drawCircle(
        Color.Black,
        radius,
        Offset(offset, this.size.height - offset)
    )
}

fun DrawScope.bottomRightCircle(radius: Float, offset: Float) {
    drawCircle(
        Color.Black,
        radius,
        Offset(this.size.width - offset, this.size.height - offset)
    )
}

fun DrawScope.centerLeftCircle(radius: Float, offset: Float) {
    drawCircle(
        Color.Black,
        radius,
        Offset(offset, this.size.height / 2f)
    )
}

fun DrawScope.centerRightCircle(radius: Float, offset: Float) {
    drawCircle(
        Color.Black,
        radius,
        Offset(this.size.width - offset, this.size.height / 2f)
    )
}

@Composable
fun Dice(number: Number, modifier: Modifier) {
    Canvas(modifier = modifier
        .size(Dp(96f), Dp(96f))
    ) {
        val cornerRadius: Float = 20f

        drawRoundRect(
            Color.White,
            size = this.size,
            cornerRadius = CornerRadius(cornerRadius)
        )

        val circleRadius: Float = 20f
        val offset: Float = cornerRadius * 2f

        when (number) {
            1 -> {
                centerCircle(circleRadius)
            }
            2 -> {
                topRightCircle(circleRadius, offset)
                bottomLeftCircle(circleRadius, offset)
            }
            3 -> {
                topRightCircle(circleRadius, offset)
                centerCircle(circleRadius)
                bottomLeftCircle(circleRadius, offset)
            }
            4 -> {
                topLeftCircle(circleRadius, offset)
                topRightCircle(circleRadius, offset)
                bottomLeftCircle(circleRadius, offset)
                bottomRightCircle(circleRadius, offset)
            }
            5 -> {
                topLeftCircle(circleRadius, offset)
                topRightCircle(circleRadius, offset)
                centerCircle(circleRadius)
                bottomLeftCircle(circleRadius, offset)
                bottomRightCircle(circleRadius, offset)
            }
            6 -> {
                topLeftCircle(circleRadius, offset)
                topRightCircle(circleRadius, offset)
                centerLeftCircle(circleRadius, offset)
                centerRightCircle(circleRadius, offset)
                bottomLeftCircle(circleRadius, offset)
                bottomRightCircle(circleRadius, offset)
            }
        }
    }
}

@Composable
fun App(modifier: Modifier) {
    Box(
        modifier = modifier
            .fillMaxWidth()
            .fillMaxHeight()
            .background(
                Color.Black
            )
    ) {
        var number: Number by remember { mutableStateOf(1) }
        Dice(
            number,
            Modifier.align(Alignment.Center)
        )

        Button(
            onClick = {
                number = (1..6).random()
            },
            modifier = Modifier
                .align(Alignment.Center)
                .offset(Dp(0f), Dp(100f))
        ) {
            Text("Jogar Dado")
        }
    }
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    RollDiceTheme {
        App(
            modifier = Modifier
        )
    }
}