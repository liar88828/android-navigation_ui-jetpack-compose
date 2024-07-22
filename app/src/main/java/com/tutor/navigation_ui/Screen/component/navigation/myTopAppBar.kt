package com.tutor.navigation_ui.Screen.component.navigation

import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material.icons.filled.MoreVert
import androidx.compose.material3.DrawerState
import androidx.compose.material3.DrawerValue
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.material3.rememberDrawerState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import com.tutor.navigation_ui.ui.theme.Orange
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch

@Composable
@OptIn(ExperimentalMaterial3Api::class)
fun MyTopAppBar(
	coroutineScope: CoroutineScope,
	drawerState: DrawerState
) {
	TopAppBar(
		colors = TopAppBarDefaults.topAppBarColors(
			containerColor = Orange,
			navigationIconContentColor = Color.White,
			titleContentColor = Color.White,
		),
		title = { Text(text = "Navigation Drawer") },
		navigationIcon = {
			IconButton(
				onClick = { coroutineScope.launch { drawerState.open() } }
			) {
				Icon(imageVector = Icons.Default.Menu, contentDescription = "More Icon")
			}
		},
		actions = {
			LazyRow {
				item() {
					Icon(
						imageVector = Icons.Default.MoreVert,
						contentDescription = "More Icon",
						tint = Color.White
					)
				}
			}
		},
		)
}

@Preview(showBackground = true)
@Composable
private fun MyTopAppBarPrev() {
	MyTopAppBar(
		coroutineScope = rememberCoroutineScope(),
		drawerState = rememberDrawerState(
			initialValue = DrawerValue.Closed
		)
	)
}
