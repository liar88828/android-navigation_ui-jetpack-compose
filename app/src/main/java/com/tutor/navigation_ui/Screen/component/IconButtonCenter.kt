package com.tutor.navigation_ui.Screen.component

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.tutor.navigation_ui.model.ItemNavData
import com.tutor.navigation_ui.ui.theme.Orange

@Composable
fun IconButtonCenter(
	selected: MutableState<ImageVector>,
	navController: NavHostController,
	it: ItemNavData,
	modifier: Modifier = Modifier
) {
	Box(
		modifier = modifier.padding(16.dp),
		contentAlignment = Alignment.Center
	) {
		FloatingActionButton(onClick = { /*TODO*/ }) {
			Icon(
				imageVector = Icons.Default.Add, contentDescription = "Add icon",
				tint = Orange
			)
		}
	}
}