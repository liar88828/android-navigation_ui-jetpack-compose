package com.tutor.navigation_ui.Screen.component.navigation

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.KeyboardArrowRight
import androidx.compose.material3.Badge
import androidx.compose.material3.BadgedBox
import androidx.compose.material3.DrawerState
import androidx.compose.material3.DrawerValue
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.ModalDrawerSheet
import androidx.compose.material3.NavigationDrawerItem
import androidx.compose.material3.NavigationDrawerItemDefaults
import androidx.compose.material3.Text
import androidx.compose.material3.rememberDrawerState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.tutor.navigation_ui.R
import com.tutor.navigation_ui.model.itemSidebarRoute
import com.tutor.navigation_ui.ui.theme.Orange
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch

@Composable
fun SlideBar(
	modifier: Modifier = Modifier,
	scope: CoroutineScope,
	drawerState: DrawerState,
	navController: NavHostController,
) {
	ModalDrawerSheet {
		Column(
			modifier = modifier.fillMaxSize(),
			verticalArrangement = Arrangement.spacedBy(12.dp)
		) {
			Column {
				SlideBarProfile(modifier)
				HorizontalDivider(modifier = modifier.height(2.dp), color = Color.DarkGray)
				Spacer(modifier = modifier.height(5.dp))
				SlideBarList(
					scope = scope,
					drawerState = drawerState,
					navController = navController,
				)
			}
		}
	}
}

@Composable
private fun SlideBarList(
	modifier: Modifier = Modifier,
	scope: CoroutineScope,
	drawerState: DrawerState,
	navController: NavHostController,
	test: Boolean = false,
) {
	var selectItem by remember {
		mutableStateOf(itemSidebarRoute[0])
	}

	itemSidebarRoute.forEach {
		NavigationDrawerItem(
			colors = NavigationDrawerItemDefaults.colors(selectedContainerColor = Orange.copy(alpha = 0.5f)),
			modifier = modifier.padding(horizontal = 20.dp),
			label = { Text(text = it.title) },
			selected = it == selectItem,
			onClick = {
				selectItem = it
				if (!test) {
					navController.navigate(it.route)
					scope.launch {
						drawerState.close()
					}
				}
			},
			icon = { Icon(imageVector = it.icon, contentDescription = it.title) },
			badge = {
				if (it.badge > 0) {
					BadgedBox(badge = {
						Badge(
							modifier = modifier
								.padding(end = 10.dp)
						) {
							Text(text = it.badge.toString(), fontSize = 17.sp)
						}
					}) {
						Box(
						) {
							Icon(
								imageVector = Icons.AutoMirrored.Filled.KeyboardArrowRight,
								contentDescription = it.title,
								tint = Color.Transparent
							)
						}
					}
				}
			}
		)
	}

}

@Composable
private fun SlideBarProfile(modifier: Modifier) {
	Box(
		modifier = modifier
			.fillMaxWidth()
			.height(200.dp)
			.background(Orange),
		contentAlignment = Alignment.Center
	) {
		Column(
			modifier = modifier
				.wrapContentSize()
				.padding(10.dp),
			verticalArrangement = Arrangement.Center,
			horizontalAlignment = Alignment.CenterHorizontally
		) {
			Image(
				modifier = modifier
					.height(100.dp)
					.border(
						width = 2.dp,
						color = Color.White,
						shape = CircleShape
					)
					.clip(CircleShape),
				painter = painterResource(id = R.drawable.ic_launcher_foreground),
				contentDescription = "Image",
				contentScale = ContentScale.Crop,
			)
			Spacer(modifier = modifier.height(10.dp))
			Text(
				text = "Hello",
				modifier = modifier
					.fillMaxWidth(),
				fontSize = MaterialTheme.typography.headlineLarge.fontSize,
				textAlign = TextAlign.Center
			)
		}
	}
}

@Preview
@Composable
private fun SlideBarListPrev() {
	Column {
		SlideBarList(
			scope = rememberCoroutineScope(),
			drawerState = rememberDrawerState(initialValue = DrawerValue.Open),
			navController = rememberNavController(),
			test = true
		)
	}

}

@Preview
@Composable
private fun SlideBarPrev() {
	SlideBar(
		scope = rememberCoroutineScope(),
		drawerState = rememberDrawerState(initialValue = DrawerValue.Open),
		navController = rememberNavController()
	)
}