package com.tutor.navigation_ui.Screen.component

import android.content.Context
import android.widget.Toast
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.PlayArrow
import androidx.compose.material.icons.filled.Star
import androidx.compose.material.icons.filled.ThumbUp
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.ModalBottomSheet
import androidx.compose.material3.SheetState
import androidx.compose.material3.Text
import androidx.compose.material3.rememberModalBottomSheetState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.tutor.navigation_ui.model.ItemBottomData
import com.tutor.navigation_ui.model.SRoute
import com.tutor.navigation_ui.ui.theme.Orange

@Composable
@OptIn(ExperimentalMaterial3Api::class)
fun DrawerBottomScreen(
	showButtonSheet: MutableState<Boolean>,
	sheetState: SheetState,
	navController: NavHostController,
	modifier: Modifier,
) {
	val context = LocalContext.current

	ModalBottomSheet(
		onDismissRequest = { showButtonSheet.value = false },
		sheetState = sheetState
	) {
		InsideModalBottom(modifier, showButtonSheet, navController, context)
	}
}

@Composable
private fun InsideModalBottom(
	modifier: Modifier,
	showButtonSheet: MutableState<Boolean>,
	navController: NavHostController,
	context: Context
) {
	Column(
		modifier = modifier
			.fillMaxWidth()
			.padding(18.dp),
		horizontalAlignment = Alignment.CenterHorizontally,
		verticalArrangement = Arrangement.spacedBy(20.dp)
	) {
		BottomSheetItem(
			item = ItemBottomData(
				icon = Icons.Default.ThumbUp,
				title = "Like",
				onClick = {
					showButtonSheet.value = false
					navController.navigate(SRoute.Post.route)
					Toast.makeText(context, "Post ", Toast.LENGTH_SHORT).show()

				})
		)
		BottomSheetItem(
			item = ItemBottomData(
				icon = Icons.Default.Star,
				title = "Story",
				onClick = {
					Toast.makeText(context, "Story", Toast.LENGTH_SHORT).show()
					showButtonSheet.value = false
					navController.navigate(SRoute.Post.route)
				}
			)
		)

		BottomSheetItem(
			item = ItemBottomData(
				icon = Icons.Default.PlayArrow,
				title = "Reels",
				onClick = {
					Toast.makeText(context, "Reels", Toast.LENGTH_SHORT).show()
					showButtonSheet.value = false
					navController.navigate(SRoute.Post.route)
				}
			)
		)
	}
}

@Composable
private fun BottomSheetItem(
	item: ItemBottomData,
	modifier: Modifier = Modifier,
) {
	Row(
		modifier = modifier
			.fillMaxWidth()
			.clickable {
				item.onClick()
			},
		verticalAlignment = Alignment.CenterVertically,
		horizontalArrangement = Arrangement.spacedBy(8.dp),
	) {
		Text(text = item.title, color = Orange, fontSize = 22.sp)
		Icon(
			imageVector = item.icon, contentDescription = item.title,
			tint = Orange
		)
	}

}

@Preview(showBackground = true)
@Composable
private fun BottomSheetItemPrev() {
	BottomSheetItem(
		item = ItemBottomData(
			icon = Icons.Default.ThumbUp,
			title = "Like",
			onClick = {}
		)
	)
}

@Preview(showBackground = true)
@Composable
private fun InsideModalBottomPrev() {
	InsideModalBottom(
		modifier = Modifier,
		showButtonSheet = remember { mutableStateOf(false) },
		navController = rememberNavController(),
		context = LocalContext.current
	)
}

@OptIn(ExperimentalMaterial3Api::class)
@Preview(showBackground = true)
@Composable
private fun DrawerBottomScreenPrev() {
	val sheetState = remember {
		mutableStateOf(false)
	}
	DrawerBottomScreen(
		showButtonSheet = sheetState,
		sheetState = rememberModalBottomSheetState(),
		navController = NavHostController(LocalContext.current),
		modifier = Modifier
	)

}