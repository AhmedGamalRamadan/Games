package com.example.bostatask

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.core.view.WindowCompat
import androidx.navigation.compose.rememberNavController
import com.example.bostatask.ui.navigation.AppNavigation
import com.example.bostatask.ui.theme.BostaTaskTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        WindowCompat.getInsetsController(window, window.decorView)
            .isAppearanceLightStatusBars = true
        setContent {
            BostaTaskTheme {
                val navController = rememberNavController()
                AppNavigation(navController)
            }
        }
    }
}