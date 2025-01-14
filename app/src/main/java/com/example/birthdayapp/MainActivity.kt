package com.example.birthdayapp

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import com.example.birthdayapp.ui.theme.BirthDayAppTheme

import androidx.compose.animation.animateColorAsState
import androidx.compose.animation.core.*
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.*
import androidx.compose.ui.graphics.drawscope.rotate
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import kotlinx.coroutines.delay
import kotlin.random.Random


class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            BirthDayAppTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    BirthdayCard(
                        name = "Android",
                        modifier = Modifier.padding(innerPadding)
                    )
                }
            }
        }
    }
}



@Composable
fun BirthdayCard(name: String = "Dear Friend", modifier: Modifier) {
    // Animation states
    val infiniteTransition = rememberInfiniteTransition(label = "")
    val colors = listOf(
        Color(0xFFFF69B4), // Pink
        Color(0xFF87CEEB), // Sky Blue
        Color(0xFFFFD700), // Gold
        Color(0xFF98FB98)  // Pale Green
    )

    var currentColorIndex by remember { mutableIntStateOf(0) }
    val targetColor by animateColorAsState(
        targetValue = colors[currentColorIndex],
        animationSpec = tween(1000), label = ""
    )

    LaunchedEffect(Unit) {
        while(true) {
            delay(2000)
            currentColorIndex = (currentColorIndex + 1) % colors.size
        }
    }

    // Balloon animation
    val balloonOffset = infiniteTransition.animateFloat(
        initialValue = 0f,
        targetValue = 20f,
        animationSpec = infiniteRepeatable(
            animation = tween(1000),
            repeatMode = RepeatMode.Reverse
        ), label = ""
    )

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(Color(0xFFFFF8DC))
            .padding(16.dp)
    ) {
        // Card content
        Card(
            modifier = Modifier
                .padding(16.dp)
                .fillMaxWidth()
                .aspectRatio(1f),
            shape = RoundedCornerShape(24.dp),
            elevation = CardDefaults.cardElevation(defaultElevation = 8.dp)
        ) {
            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .background(
                        Brush.linearGradient(
                            listOf(
                                targetColor.copy(alpha = 0.3f),
                                Color.White
                            )
                        )
                    )
            ) {
                // Decorative elements
                repeat(20) {
                    Confetti(infiniteTransition)
                }

                // Birthday message
                Column(
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(24.dp),
                    horizontalAlignment = Alignment.CenterHorizontally,
                    verticalArrangement = Arrangement.Center
                ) {
                    Text(
                        text = "Happy Birthday!",
                        style = TextStyle(
                            fontSize = 34.sp,
                            fontWeight = FontWeight.Bold,
                            color = targetColor
                        )
                    )

                    Spacer(modifier = Modifier.height(16.dp))

                    Text(
                        text = name,
                        style = TextStyle(
                            fontSize = 34.sp,
                            fontWeight = FontWeight.Medium,
                            color = Color.DarkGray,
                            fontFamily = FontFamily.Cursive
                        )
                    )

                    Spacer(modifier = Modifier.height(14.dp))

                    Text(
                        text = "Wishing you a day filled with joy," +
                                "laughter, and beautiful moments!",
                        style = TextStyle(
                            fontSize = 18.sp,
                            color = Color.DarkGray
                        ),
                        textAlign = TextAlign.Center
                    )

                    Spacer(modifier = Modifier.height(78.dp))
                }



                // Balloons
                Canvas(
                    modifier = Modifier.fillMaxSize()
                ) {
                    val balloonColors = listOf(
                        Color(0xFFFF69B4),
                        Color(0xFF87CEEB),
                        Color(0xFFFFD700)
                    )

                    repeat(5) { index ->
                        val x = size.width * (0.2f + index * 0.15f)
                        val y = size.height * 0.7f + balloonOffset.value

                        drawCircle(
                            color = balloonColors[index % balloonColors.size],
                            radius = 30f,
                            center = Offset(x, y)
                        )

                        // Balloon string
                        drawLine(
                            color = Color.DarkGray,
                            start = Offset(x, y + 30f),
                            end = Offset(x, y + 80f),
                            strokeWidth = 2f
                        )
                    }
                }
            }
        }
    }
}

@Composable
fun Confetti(infiniteTransition: InfiniteTransition) {
    val angle by infiniteTransition.animateFloat(
        initialValue = 0f,
        targetValue = 360f,
        animationSpec = infiniteRepeatable(
            animation = tween(2000, easing = LinearEasing)
        ), label = ""
    )

    val offset by infiniteTransition.animateFloat(
        initialValue = 0f,
        targetValue = 1f,
        animationSpec = infiniteRepeatable(
            animation = tween(2000),
            repeatMode = RepeatMode.Reverse
        ), label = ""
    )

    Canvas(
        modifier = Modifier.fillMaxSize()
    ) {
        rotate(angle) {
            val x = Random.nextFloat() * size.width
            val y = Random.nextFloat() * size.height
            drawCircle(
                color = Color(
                    Random.nextFloat(),
                    Random.nextFloat(),
                    Random.nextFloat(),
                    0.5f
                ),
                radius = 5f,
                center = Offset(x, y + (offset * 50f))
            )
        }
    }
}

// Usage example:
@Composable
@Preview
fun PreviewBirthdayCard() {
    val innerPadding =0.dp
    BirthdayCard(
        name = "Ryan Otieno",
        modifier = androidx.compose.ui.Modifier.Companion.padding(innerPadding)
    )
}

