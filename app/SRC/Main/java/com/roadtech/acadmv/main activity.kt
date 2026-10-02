
package com.roadtech.academy

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            RoadTechApp()
        }
    }
}

private val NavyBlue = Color(0xFF102A56)
private val Gold = Color(0xFFFFC857)
private val LightBackground = Color(0xFFF4F7FC)

@Composable
fun RoadTechApp() {
    var currentScreen by remember { mutableStateOf("Home") }

    MaterialTheme {
        Surface(
            modifier = Modifier.fillMaxSize(),
            color = LightBackground
        ) {
            when (currentScreen) {
                "Home" -> HomeScreen(
                    onCourses = { currentScreen = "Courses" },
                    onAbout = { currentScreen = "About" }
                )
                "Courses" -> CoursesScreen(
                    onBack = { currentScreen = "Home" }
                )
                else -> AboutScreen(
                    onBack = { currentScreen = "Home" }
                )
            }
        }
    }
}

@Composable
fun HomeScreen(
    onCourses: () -> Unit,
    onAbout: () -> Unit
) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(24.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Text(
            text = "ROAD TECH",
            fontSize = 34.sp,
            fontWeight = FontWeight.ExtraBold,
            color = NavyBlue
        )

        Text(
            text = "COMPUTER ACADEMY",
            fontSize = 18.sp,
            fontWeight = FontWeight.Bold,
            color = Gold
        )

        Spacer(modifier = Modifier.height(12.dp))

        Text(
            text = "Learn Today, Lead Tomorrow.",
            color = NavyBlue,
            fontSize = 16.sp
        )

        Spacer(modifier = Modifier.height(35.dp))

        Card(
            modifier = Modifier.fillMaxWidth(),
            colors = CardDefaults.cardColors(
                containerColor = Color.White
            )
        ) {
            Column(
                modifier = Modifier.padding(24.dp),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Text(
                    text = "Welcome to Road Tech!",
                    fontSize = 23.sp,
                    fontWeight = FontWeight.Bold,
                    color = NavyBlue
                )

                Spacer(modifier = Modifier.height(15.dp))

                Text(
                    text = "Start your journey into computer education. Develop digital skills and prepare for the future.",
                    textAlign = TextAlign.Center,
                    color = Color.DarkGray
                )

                Spacer(modifier = Modifier.height(25.dp))

                Button(
                    onClick = onCourses,
                    modifier = Modifier.fillMaxWidth(),
                    colors = ButtonDefaults.buttonColors(
                        containerColor = NavyBlue
                    )
                ) {
                    Text("Explore Courses")
                }

                Spacer(modifier = Modifier.height(12.dp))

                OutlinedButton(
                    onClick = onAbout,
                    modifier = Modifier.fillMaxWidth()
                ) {
                    Text("About the Academy")
                }
            }
        }

        Spacer(modifier = Modifier.height(25.dp))

        Text(
            text = "Your Future Begins With Technology.",
            color = NavyBlue,
            textAlign = TextAlign.Center
        )
    }
}

@Composable
fun CoursesScreen(onBack: () -> Unit) {
    val courses = listOf(
        "Computer Fundamentals",
        "Microsoft Office Applications",
        "Internet and Digital Skills",
        "Graphic Design",
        "Programming and Web Development"
    )

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(20.dp)
    ) {
        Text(
            text = "Our Courses",
            fontSize = 28.sp,
            fontWeight = FontWeight.Bold,
            color = NavyBlue
        )

        Spacer(modifier = Modifier.height(15.dp))

        Text("Choose a course to begin your learning journey.")

        Spacer(modifier = Modifier.height(15.dp))

        LazyColumn(
            verticalArrangement = Arrangement.spacedBy(12.dp)
        ) {
            items(courses) { course ->
                Card(
                    modifier = Modifier.fillMaxWidth(),
                    colors = CardDefaults.cardColors(
                        containerColor = Color.White
                    )
                ) {
                    Text(
                        text = course,
                        modifier = Modifier.padding(20.dp),
                        fontWeight = FontWeight.SemiBold,
                        color = NavyBlue
                    )
                }
            }
        }

        Spacer(modifier = Modifier.height(15.dp))

        Button(
            onClick = onBack,
            colors = ButtonDefaults.buttonColors(
                containerColor = NavyBlue
            )
        ) {
            Text("Back to Home")
        }
    }
}

@Composable
fun AboutScreen(onBack: () -> Unit) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(24.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Text(
            text = "About Road Tech",
            fontSize = 27.sp,
            fontWeight = FontWeight.Bold,
            color = NavyBlue
        )

        Spacer(modifier = Modifier.height(20.dp))

        Text(
            text = "Road Tech Computer Academy is dedicated to helping students develop computer knowledge, digital skills and practical technology experience.",
            textAlign = TextAlign.Center,
            fontSize = 16.sp
        )

        Spacer(modifier = Modifier.height(25.dp))

        Button(onClick = onBack) {
            Text("Back to Home")
        }
    }
}
