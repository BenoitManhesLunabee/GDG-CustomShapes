package com.lunabee.customshapes.demo.screens

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import com.lunabee.customshapes.demo.ui.theme.CSTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            CSTheme {
                Root()
            }
        }
    }
}
