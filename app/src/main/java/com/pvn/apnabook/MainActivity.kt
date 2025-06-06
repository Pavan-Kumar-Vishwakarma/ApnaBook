package com.pvn.apnabook

import android.os.Bundle
import android.os.Handler
import android.os.Looper
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.text.BasicText
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.compose.rememberNavController
import coil.ImageLoader
import coil.compose.AsyncImage
import coil.decode.GifDecoder
import coil.request.ImageRequest
import com.pvn.apnabook.presentation.navigation.NavGraph
import com.pvn.apnabook.ui.theme.ApnaBookTheme
import dagger.hilt.android.AndroidEntryPoint


@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            ApnaBookTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { _ ->

                    MainScreen()

                }
            }
        }
    }
}

@Composable
fun MainScreen() {
    val navHostController = rememberNavController()
    val showSplash = remember {
        mutableStateOf(true)
    }

    LaunchedEffect(Unit) {
        Handler(Looper.getMainLooper()).postDelayed({
            showSplash.value = false
        }, 3000)
    }

    if(showSplash.value) {
        SplashScreen()
    }
    else {
        NavGraph(navHostController = navHostController)
    }
}

@Composable
fun SplashScreen() {
    val context = LocalContext.current
    val imageLoader = ImageLoader.Builder(context)
        .components {
            add(GifDecoder.Factory())
        }
        .build()

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.White),
        contentAlignment = Alignment.Center
    ) {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            AsyncImage(
                model = ImageRequest.Builder(context)
                    .data(R.drawable.splash)
                    .build(),
                imageLoader = imageLoader,
                contentDescription = "Splash GIF",
                modifier = Modifier.size(300.dp)
            )

            Text(
                text = "Welcome to Apna Book",
                style = MaterialTheme.typography.labelLarge.copy(
                    color = Color.Black,
                    fontSize = 24.sp,
                    textAlign = TextAlign.Center
                )
            )
        }
    }
}