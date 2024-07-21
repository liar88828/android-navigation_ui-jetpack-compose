package com.tutor.navigation_ui

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import com.tutor.navigation_ui.Screen.MainScreen
import com.tutor.navigation_ui.ui.theme.Navigation_uiTheme

class MainActivity : ComponentActivity() {
	override fun onCreate(savedInstanceState: Bundle?) {
		super.onCreate(savedInstanceState)
		enableEdgeToEdge()
		setContent {
			Navigation_uiTheme {
				MainScreen()
			}
		}
	}

}

