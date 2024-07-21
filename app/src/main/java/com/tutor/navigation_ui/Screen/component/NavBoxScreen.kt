package com.tutor.navigation_ui.Screen.component

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Home
import androidx.compose.material3.DrawerState
import androidx.compose.material3.DrawerValue
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.ModalDrawerSheet
import androidx.compose.material3.ModalNavigationDrawer
import androidx.compose.material3.NavigationDrawerItem
import androidx.compose.material3.Text
import androidx.compose.material3.rememberDrawerState
import androidx.compose.material3.rememberModalBottomSheetState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.tutor.navigation_ui.model.ItemNavData
import com.tutor.navigation_ui.model.itemSidebarRoute
import com.tutor.navigation_ui.model.SRoute
import com.tutor.navigation_ui.ui.theme.Orange
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun NavBoxScreen(
	modifier: Modifier = Modifier,
	drawerState: DrawerState,
	coroutineScope: CoroutineScope,
	navController: NavHostController
) {
	val context = LocalContext.current.applicationContext
	val sheetState = rememberModalBottomSheetState()
	val showButtonSheet = remember {
		mutableStateOf(false)
	}


	ModalNavigationDrawer(
		drawerState = drawerState,
		gesturesEnabled = true,
		drawerContent = {
			ModalDrawerSheet {
				Box(
					modifier = modifier
						.background(Orange)
						.fillMaxWidth()
						.height(150.dp)
				)
				{
					HorizontalDivider()
					LazyColumn {
						items(itemSidebarRoute) {
							ItemSidebar(
								coroutineScope = coroutineScope,
								drawerState = drawerState,
								navigationController = navController,
								item = it
							)
						}
					}
				}
			}
		}) {
	}

}

@Composable
private fun ItemSidebar(
	coroutineScope: CoroutineScope,
	drawerState: DrawerState,
	navigationController: NavHostController,
	item: ItemNavData
) {
	NavigationDrawerItem(
		label = { Text(text = item.title, color = Orange) },
		icon = { Icon(imageVector = item.icon, contentDescription = item.title) },
		selected = false,
//						selected = selected.value == Icons.Default.Home,
		onClick = {
//							selected.value = Icons.Default.Home
			coroutineScope.launch { drawerState.close() }
			navigationController.navigate(item.route) {
				popUpTo(0)
			}
		}
	)
}


@Preview(showBackground = true)
@Composable
private fun NavBoxScreenScreen() {
	NavBoxScreen(
		drawerState = rememberDrawerState(initialValue = DrawerValue.Closed),
		coroutineScope = rememberCoroutineScope(),
		navController = rememberNavController()
	)
}



@Preview(showBackground = true)
@Composable
private fun ItemSidebarPrev() {
	val navigationController = rememberNavController()
	val coroutineScope = rememberCoroutineScope()
	val drawerState = rememberDrawerState(initialValue = DrawerValue.Closed)

	ItemSidebar(
		coroutineScope,
		drawerState,
		navigationController,
		item = ItemNavData(
			Icons.Default.Home,
			SRoute.Home.route,
			"Home",
			23
		)
	)
}
