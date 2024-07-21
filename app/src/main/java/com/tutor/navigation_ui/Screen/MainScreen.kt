package com.tutor.navigation_ui.Screen

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.DrawerState
import androidx.compose.material3.DrawerValue
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ModalNavigationDrawer
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SheetState
import androidx.compose.material3.rememberDrawerState
import androidx.compose.material3.rememberModalBottomSheetState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.tutor.navigation_ui.Screen.component.DrawerBottomScreen
import com.tutor.navigation_ui.Screen.component.MyBottomAppBar
import com.tutor.navigation_ui.Screen.component.MyTopAppBar
import com.tutor.navigation_ui.Screen.component.SlideBar
import com.tutor.navigation_ui.model.SRoute
import kotlinx.coroutines.CoroutineScope

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MainScreen(modifier: Modifier = Modifier) {
	val navController = rememberNavController()

//	slide
	val coroutineScope = rememberCoroutineScope()
	val drawerState = rememberDrawerState(initialValue = DrawerValue.Closed)
//
	val sheetState = rememberModalBottomSheetState()
	//button
	val showButtonSheet = remember { mutableStateOf(false) }

	ModalNavigationDrawer(
		drawerContent = {
			SlideBar(
				modifier,
				scope = coroutineScope,
				drawerState = drawerState,
				navController = navController,
			)
		},
		drawerState = drawerState
	) {
		MyScaffold(
			modifier = modifier,
			coroutineScope = coroutineScope,
			drawerState = drawerState,
			showButtonSheet = showButtonSheet,
			sheetState = sheetState,
			navController = navController,
		)
	}
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MyScaffold(
	modifier: Modifier = Modifier,
	coroutineScope: CoroutineScope,
	drawerState: DrawerState,
	navController: NavHostController,
	showButtonSheet: MutableState<Boolean>,
	sheetState: SheetState
) {
	Scaffold(
		modifier = Modifier.fillMaxSize(),
		topBar = { MyTopAppBar(coroutineScope, drawerState) },
		bottomBar = { MyBottomAppBar( navController, showButtonSheet) },
		content = { innerPadding ->
			NavHost(navController = navController, startDestination = SRoute.Home.route) {
				composable(SRoute.Home.route) { HomeScreen() }
				composable(SRoute.Profile.route) { ProfileScreen() }
				composable(SRoute.Setting.route) { SettingScreen() }
				composable(SRoute.Notification.route) { NotificationScreen() }
				composable(SRoute.Post.route) { PostScreen() }
				composable(SRoute.Search.route) { SearchScreen() }
			}
		},
	)
	if (showButtonSheet.value) {
		DrawerBottomScreen(showButtonSheet, sheetState, navController, modifier)
	}
}

@Preview
@Composable
private fun MainScreenPrev() {
	MainScreen()
}

