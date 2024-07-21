package com.tutor.navigation_ui.model

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.MailOutline
import androidx.compose.material.icons.filled.Notifications
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.filled.Search
import androidx.compose.material.icons.filled.Settings
import androidx.compose.ui.graphics.vector.ImageVector

sealed class SRoute(
	val route: String
) {
	data object Home : SRoute("Home")
	data object Add : SRoute("Add")
	data object Search : SRoute("Search")
	data object Notification : SRoute("Notification")
	data object Profile : SRoute("Profile")
	data object Post : SRoute("Post")
	data object Setting : SRoute("Setting")

}

data class ItemNavData(
	val icon: ImageVector,
	val route: String,
	val title: String,
	val badge: Int
)

data class ItemBottomData(
	val icon: ImageVector,
	val title: String,
	val onClick: () -> Unit,
)

val itemSidebarRoute = listOf(
	ItemNavData(
		Icons.Default.Home,
		SRoute.Home.route,
		"Home", 9
	),
	ItemNavData(
		Icons.Default.Person,
		SRoute.Profile.route,
		"Profile",
		0
	),
	ItemNavData(
		Icons.Default.Settings,
		SRoute.Setting.route,
		"Setting", 12
	),
	ItemNavData(
		Icons.Default.Search,
		SRoute.Search.route,
		"Search", 12
	),
	ItemNavData(
		Icons.Default.Notifications,
		SRoute.Notification.route,
		"Notification", 123
	),
	ItemNavData(
		Icons.Default.MailOutline,
		SRoute.Post.route,
		"Post", 12
	),
)

val itemsBottom = listOf(

	ItemNavData(
		Icons.Default.Person,
		SRoute.Profile.route,
		"Profile",
		0
	),
	ItemNavData(
		Icons.Default.Notifications,
		SRoute.Notification.route,
		"Notification", 123
	),
	ItemNavData(
		Icons.Default.Add,
		SRoute.Add.route,
		"Add", 9
	),
	ItemNavData(
		Icons.Default.Search,
		SRoute.Search.route,
		"Search", 12
	),
	ItemNavData(
		Icons.Default.Settings,
		SRoute.Setting.route,
		"Setting", 12
	),



)

