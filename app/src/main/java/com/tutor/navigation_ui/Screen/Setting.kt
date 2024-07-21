package com.tutor.navigation_ui.Screen

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.sp
import com.tutor.navigation_ui.ui.theme.Orange

@Composable
fun SettingScreen(modifier: Modifier = Modifier) {
	Box(modifier = modifier.fillMaxSize()) {
		Column(
			modifier
				.fillMaxSize()
				.align(Alignment.Center),
			horizontalAlignment = Alignment.CenterHorizontally,
			verticalArrangement = Arrangement.Center
		) {
			Text(
				text = "Setting Screen", fontSize = 30.sp,
				color = Orange
			)
		}
	}

}

@Preview(showBackground = true)
@Composable
private fun SettingScreenPrev() {
	SettingScreen()
}