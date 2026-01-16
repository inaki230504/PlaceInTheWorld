package com.example.myapplication

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.width
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Star
import androidx.compose.material3.SegmentedButtonDefaults.Icon
import androidx.compose.material3.Slider
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.blur
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
 import kotlin.math.ceil
import kotlin.math.floor

@Composable
fun flipImage(
    modifier: Modifier,
    navController: NavController,
    imageId: Int?,
    imageName: String
) {
    val range = 0f..360f
    val rangealpha =  0f..1f
    val  scalerange = 1f.. 100f
    var selection by remember { mutableStateOf(0f) }
    var scale by remember { mutableStateOf(1f) }
    var alphas by remember { mutableStateOf(1f) }
    var blur by  remember { mutableStateOf(0f) }

    Column(modifier = modifier.fillMaxSize()
        .background(Color(0xFF005CB1)),

        horizontalAlignment = Alignment.CenterHorizontally
        ) {
        Text(text = imageName,
            fontSize = 50.sp
            )
        Image(
            painter = painterResource(id = imageId?: 0),
            contentDescription = null,
            modifier = Modifier.graphicsLayer {
                rotationY = selection
                scaleX = scale
                scaleY = scale
                alpha = alphas

            }.blur(
                radius = blur.dp
            )
        )
        Row (verticalAlignment = Alignment.CenterVertically){
         Text(
             text = "Rotación",
             fontSize = 15.sp
         )
            Spacer(modifier = Modifier.width(10.dp))
            Slider(
                value = selection,
                valueRange = range,
                onValueChange = {selection = it}
            )

        }
        Row (verticalAlignment = Alignment.CenterVertically){
            Text(
                text = "Scale",
                fontSize = 15.sp
            )
            Spacer(modifier = Modifier.width(10.dp))
            Slider(
                value = scale,
                valueRange = scalerange,
                onValueChange = {scale = it}
            )

        }
        Row (verticalAlignment = Alignment.CenterVertically){
            Text(
                text = "alpha",
                fontSize = 15.sp
            )
            Spacer(modifier = Modifier.width(10.dp))
            Slider(
                value = alphas,
                valueRange = rangealpha,
                onValueChange = {alphas = it}
            )

        }
        Row (verticalAlignment = Alignment.CenterVertically){
            Text(
                text = "blur",
                fontSize = 15.sp
            )
            Spacer(modifier = Modifier.width(10.dp))
            Slider(
                value = blur,
                valueRange = range,
                onValueChange = {blur = it}
            )

        }
    }
}
