package com.example.myapplication

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Face
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material.icons.filled.MoreVert
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBarColors
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.example.myapplication.ui.theme.MyApplicationTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            MyApplicationTheme{
                var navController = rememberNavController()
                var change by remember { mutableStateOf(false) }
                Scaffold(
                    modifier = Modifier.fillMaxSize(),
                    topBar = {mywonTopBar(change) {newValue -> change = newValue} },
                    floatingActionButton = {myownFloatingActio(navController)}
                ) { innerPadding ->
                    NavHost(navController = navController, startDestination = "mainmenu") {
                        composable("mainmenu") {
                            layzymenu(modifier = Modifier.padding(innerPadding), navController, change)
                        }
                        composable( route = "flipImage/{name}/{imageInt}",
                            arguments = listOf(navArgument("name") { type = NavType.StringType },
                                navArgument("imageInt") {type = NavType.IntType}

                        )){ backStackEntry ->
                            val name = backStackEntry.arguments?.getString("name") ?: "Unkown"
                            val image = backStackEntry.arguments?.getInt("imageInt") ?: 0
                            flipImage(modifier = Modifier.padding(innerPadding), navController,image,name)
                        }
                    }
                }
            }
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun mywonTopBar(change: Boolean, onChange: (Boolean) -> Unit) {
    var expand by remember { mutableStateOf(false) }
    CenterAlignedTopAppBar(
        navigationIcon = {
            Icon(
                imageVector = Icons.Filled.Menu,
                contentDescription = ""
            )
        },
        actions = {
            Icon(
                imageVector = Icons.Filled.MoreVert,
                contentDescription = "",
                modifier = Modifier.clickable {
                    expand = true
                }
            )

            DropdownMenu(
                expanded = expand,
                onDismissRequest = { expand = false }
            ) {
                DropdownMenuItem(
                    onClick = {
                        expand = false
                        onChange(false)
                    },
                    text = {
                        Row {
                            Icon(imageVector = Icons.Filled.Face, contentDescription = "")
                            Text("LazyColumn")
                        }
                    }
                )

                DropdownMenuItem(
                    onClick = {
                        expand = false
                        onChange(true)
                    },
                    text = {
                        Row {
                            Icon(imageVector = Icons.Filled.Home, contentDescription = "")
                            Text("StaggeredGrid")
                        }
                    }
                )
            }
        },
        title = { Text("PlacesintheWorld") },
        colors = TopAppBarColors( containerColor = Color(0xFF0059AE),
            scrolledContainerColor = Color.Black,
            navigationIconContentColor = Color.Black,
            titleContentColor = Color.Black,
            actionIconContentColor = Color.Black )
    )
}
@Composable
fun myownFloatingActio(navController: NavController){
    FloatingActionButton(
        onClick = {
            navController.navigate("mainmenu")
        },
        shape = RoundedCornerShape(20.dp)
        ,
        modifier = Modifier.size(70.dp),
        containerColor = Color(0xFF69B6FD),
        contentColor = Color(0xFFF6F6F6)
    ) {
        Icon(
            imageVector = Icons.Filled.ArrowBack,
            contentDescription = ""
        )
    }
}
