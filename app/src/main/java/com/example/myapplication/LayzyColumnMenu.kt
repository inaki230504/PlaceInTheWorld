package com.example.myapplication

import android.net.Uri
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.staggeredgrid.LazyVerticalStaggeredGrid
import androidx.compose.foundation.lazy.staggeredgrid.StaggeredGridCells
import androidx.compose.foundation.lazy.staggeredgrid.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.Card
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
data class Image(val image : Int,val name: String)
@Composable
fun layzymenu(modifier: Modifier,NavController : NavController, opcion : Boolean){

    Column {
        if (!opcion) {
            LazyColumn(
                modifier = modifier,
                verticalArrangement = Arrangement.spacedBy(8.dp)
            ) {
                items(getLista()) { image ->
                    val encodedName = Uri.encode(image.name)
                    Box(modifier = Modifier.fillMaxWidth().clickable{
                        NavController.navigate("flipImage/${image.name}/${image.image}")
                    }
                        ) {
                        Text(text = image.name, fontSize = 50.sp)
                        Image(
                            painter = painterResource(image.image),
                            contentDescription = "",
                            contentScale = ContentScale.Crop,
                            modifier = Modifier.fillMaxWidth()
                        )
                        Box(
                            modifier = Modifier.fillMaxWidth().align(Alignment.TopEnd)
                                .background(Color(0x55000000))
                        ) {
                            Text(text = image.name, fontSize = 30.sp)
                        }
                    }
                }
            }
        }else{
            LazyVerticalStaggeredGrid(columns = StaggeredGridCells.Fixed(2),
                modifier = modifier,
                verticalItemSpacing = 8.dp,
                horizontalArrangement = Arrangement.spacedBy(8.dp)
                ){
                items(getLista()) { image ->
                    val encodedName = Uri.encode(image.name)
                    Box(modifier = Modifier.fillMaxWidth().clickable{
                        NavController.navigate("flipImage/${image.name}/${image.image}")
                    }

                    ) {
                        Text(text = image.name, fontSize = 50.sp)
                        Image(
                            painter = painterResource(image.image),
                            contentDescription = "",
                            contentScale = ContentScale.Crop,
                            modifier = Modifier.fillMaxWidth()
                        )
                        Box(
                            modifier = Modifier.fillMaxWidth().align(Alignment.TopEnd)
                                .background(Color(0x55000000))
                        ) {
                            Text(text = image.name, fontSize = 30.sp)
                        }
                    }
                }
            }
        }
    }
}


fun getLista() : List<Image>{
    var lista = listOf<Image>(
        Image(R.drawable.image, "LaPlaza"),
        Image(R.drawable.image1, "LaPlaza"),
        Image(R.drawable.image2, "LaPlaza"),
        Image(R.drawable.image3, "LaPlaza"),
        Image(R.drawable.image4, "LaPlaza"),
        Image(R.drawable.image5, "LaPlaza"),
        Image(R.drawable.image6, "LaPlaza"),
        Image(R.drawable.image7, "LaPlaza"),
        Image(R.drawable.image8, "LaPlaza"),
    )
    return lista
}