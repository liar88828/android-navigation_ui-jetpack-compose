package com.tutor.navigation_ui.Screen.component

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Person
import androidx.compose.material3.BottomAppBar
import androidx.compose.material3.BottomAppBarDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.tutor.navigation_ui.model.ItemNavData
import com.tutor.navigation_ui.model.SRoute
import com.tutor.navigation_ui.model.itemsBottom
import com.tutor.navigation_ui.ui.theme.Orange

@Composable
fun MyBottomAppBar(
	navController: NavHostController,
	showButtonSheet: MutableState<Boolean>,
	modifier: Modifier = Modifier,
	test: Boolean = false
) {
	var selected = remember {
		mutableStateOf(Icons.Default.Home)
	}

	BottomAppBar(
		containerColor = Orange,
		contentPadding = BottomAppBarDefaults.ContentPadding,
	) {
		LazyRow(
			modifier = modifier
				.fillMaxWidth()
				.padding(horizontal = 4.dp),
			horizontalArrangement = Arrangement.SpaceBetween,
			verticalAlignment = Alignment.CenterVertically
		) {
			items(itemsBottom) {
				ItemBottomAppBar(selected, navController, showButtonSheet, it, test)
			}
		}
	}
}

@Composable
private fun ItemBottomAppBar(
	selected: MutableState<ImageVector>,
	navController: NavHostController,
	showButtonSheet: MutableState<Boolean>,
	it: ItemNavData,
	test: Boolean = false
) {
//	Column(
//		horizontalAlignment = Alignment.CenterHorizontally, modifier = Modifier
//			.fillMaxWidth()
////			.padding(horizontal = 4.dp)
//	) {
	IconButton(
		modifier = Modifier
			.clip(RoundedCornerShape(10.dp))
			.background(
				if (it.route == SRoute.Add.route) Color.LightGray.copy(alpha = 0.9f)
				else Color.Transparent
			),
		onClick = {
			if (it.route == SRoute.Add.route) {
				showButtonSheet.value = true
				selected.value = it.icon
			} else {
				selected.value = it.icon
				if (!test) {
					navController.navigate(it.route) { popUpTo(0) }
				}
			}
		}
	) {
		Icon(
			modifier = Modifier.size(if (it.route == SRoute.Add.route) 46.dp else 32.dp),
			imageVector = it.icon,
			contentDescription = it.title,
			tint = if (it.route == SRoute.Add.route) Color.White
			else if (selected.value == it.icon) Color.White
			else Color.Gray
		)
	}
//		Text(text = it.title, color = if (selected.value == it.icon) Color.White else Color.Gray)
//	}
}

@Preview(showBackground = true)
@Composable
private fun MyBottomAppBarPrev() {
	val selector = remember {
		mutableStateOf(Icons.Default.Person)
	}
	Column {
		Icon(imageVector = selector.value, contentDescription = "TEST")
		MyBottomAppBar(
			rememberNavController(),
			remember { mutableStateOf(false) },
			test = true
		)
	}
}
