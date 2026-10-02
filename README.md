

package com.roadtech.academy

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.PasswordVisualTransformation
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

private val Navy = Color(0xFF102A56)
private val Gold = Color(0xFFFFC857)
private val Background = Color(0xFFF4F7FC)

@Composable
fun RoadTechApp() {
    var screen by remember { mutableStateOf("home") }

    var fullName by remember { mutableStateOf("") }
    var email by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }
    var message by remember { mutableStateOf("") }

    MaterialTheme {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .background(Background)
                .verticalScroll(rememberScrollState())
                .padding(24.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Spacer(modifier = Modifier.height(35.dp))

            Text(
                text = "ROAD TECH",
                fontSize = 32.sp,
                fontWeight = FontWeight.ExtraBold,
                color = Navy
            )

            Text(
                text = "COMPUTER ACADEMY",
                color = Gold,
                fontWeight = FontWeight.Bold,
                fontSize = 17.sp
            )

            Spacer(modifier = Modifier.height(30.dp))

            when (screen) {

                "home" -> {
                    Text(
                        "Welcome to Road Tech!",
                        fontSize = 24.sp,
                        fontWeight = FontWeight.Bold,
                        color = Navy
                    )

                    Spacer(modifier = Modifier.height(12.dp))

                    Text("Learn Today, Lead Tomorrow.")

                    Spacer(modifier = Modifier.height(30.dp))

                    TechButton("Student Login") {
                        message = ""
                        screen = "login"
                    }

                    TechButton("Create Account") {
                        message = ""
                        screen = "register"
                    }

                    TechButton("Explore Courses") {
                        message = ""
                        screen = "courses"
                    }
                }

                "register" -> {
                    Text(
                        "Student Registration",
                        fontSize = 24.sp,
                        fontWeight = FontWeight.Bold,
                        color = Navy
                    )

                    Spacer(modifier = Modifier.height(20.dp))

                    OutlinedTextField(
                        value = fullName,
                        onValueChange = { fullName = it },
                        label = { Text("Full Name") },
                        modifier = Modifier.fillMaxWidth()
                    )

                    OutlinedTextField(
                        value = email,
                        onValueChange = { email = it },
                        label = { Text("Email Address") },
                        modifier = Modifier.fillMaxWidth()
                    )

                    OutlinedTextField(
                        value = password,
                        onValueChange = { password = it },
                        label = { Text("Create Password") },
                        visualTransformation =
                            PasswordVisualTransformation(),
                        modifier = Modifier.fillMaxWidth()
                    )

                    Spacer(modifier = Modifier.height(20.dp))

                    TechButton("Register") {
                        message = if (
                            fullName.isBlank() ||
                            !email.contains("@") ||
                            password.length < 6
                        ) {
                            "Enter your name, a valid email and a password of at least 6 characters."
                        } else {
                            "Registration screen is ready. Secure account creation will be connected in a later step."
                        }
                    }

                    TextButton(onClick = {
                        message = ""
                        screen = "login"
                    }) {
                        Text("Already have an account? Login")
                    }

                    if (message.isNotEmpty()) {
                        Text(message, color = Navy)
                    }
                }

                "login" -> {
                    Text(
                        "Student Login",
                        fontSize = 24.sp,
                        fontWeight = FontWeight.Bold,
                        color = Navy
                    )

                    Spacer(modifier = Modifier.height(20.dp))

                    OutlinedTextField(
                        value = email,
                        onValueChange = { email = it },
                        label = { Text("Email Address") },
                        modifier = Modifier.fillMaxWidth()
                    )

                    OutlinedTextField(
                        value = password,
                        onValueChange = { password = it },
                        label = { Text("Password") },
                        visualTransformation =
                            PasswordVisualTransformation(),
                        modifier = Modifier.fillMaxWidth()
                    )

                    Spacer(modifier = Modifier.height(20.dp))

                    TechButton("Login") {
                        message = if (
                            !email.contains("@") ||
                            password.isBlank()
                        ) {
                            "Please enter a valid email and password."
                        } else {
                            "Login screen is ready. Secure authentication will be connected in a later step."
                        }
                    }

                    TextButton(onClick = {
                        message = ""
                        screen = "register"
                    }) {
                        Text("Create a new account")
                    }

                    if (message.isNotEmpty()) {
                        Text(message, color = Navy)
                    }
                }

                "courses" -> {
                    Text(
                        "Our Computer Courses",
                        fontSize = 23.sp,
                        fontWeight = FontWeight.Bold,
                        color = Navy
                    )

                    Spacer(modifier = Modifier.height(20.dp))

                    val courses = listOf(
                        "Computer Fundamentals",
                        "Microsoft Office Applications",
                        "Internet and Digital Skills",
                        "Graphic Design",
                        "Programming and Web Development"
                    )

                    courses.forEachIndexed { index, course ->
                        Card(
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(vertical = 6.dp),
                            colors = CardDefaults.cardColors(
                                containerColor = Color.White
                            )
                        ) {
                            Text(
                                text = "${index + 1}. $course",
                                modifier = Modifier.padding(18.dp),
                                color = Navy,
                                fontWeight = FontWeight.SemiBold
                            )
                        }
                    }
                }
            }

            Spacer(modifier = Modifier.height(25.dp))

            if (screen != "home") {
                OutlinedButton(
                    onClick = {
                        message = ""
                        screen = "home"
                    }
                ) {
                    Text("Back to Home")
                }
            }

            Spacer(modifier = Modifier.height(30.dp))

            Text(
                "Road Tech Computer Academy",
                color = Navy,
                fontSize = 13.sp
            )
        }
    }
}

@Composable
fun TechButton(
    title: String,
    onClick: () -> Unit
) {
    Button(
        onClick = onClick,
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 7.dp)
            .height(52.dp),
        colors = ButtonDefaults.buttonColors(
            containerColor = Navy
        ),
        shape = RoundedCornerShape(12.dp)
    ) {
        Text(
            text = title,
            color = Color.White,
            fontWeight = FontWeight.Bold
        )
    }
}
