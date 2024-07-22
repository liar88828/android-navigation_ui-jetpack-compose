package com.tutor.navigation_ui.Screen.component.navigation

import android.widget.Toast
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.core.animateFloat
import androidx.compose.animation.core.updateTransition
import androidx.compose.animation.expandVertically
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.animation.shrinkVertically
import androidx.compose.animation.slideInVertically
import androidx.compose.animation.slideOutVertically
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.filled.Settings
import androidx.compose.material.icons.filled.ThumbUp
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.rotate
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.tutor.navigation_ui.ui.theme.Orange

@Composable
private fun rememberFloatButton(): FloatButtonData {
	var expanded by remember { mutableStateOf(false) }
	val transition = updateTransition(targetState = expanded, label = "Transition")
	val rotation by transition.animateFloat(label = "Rotation") {
		if (it) 45f + 360f else 0f
	}

	fun handlerExpand() {
		expanded = !expanded
	}

	return FloatButtonData(
		handlerExpand = ::handlerExpand,
		rotation,
		expanded
	)

}

data class FloatButtonData(
	val handlerExpand: () -> Unit,
	val rotation: Float,
	var expanded: Boolean
)

data class MiniFloatingButton(
	val icon: ImageVector,
	val title: String
)

@Composable
fun MyFloatingButton() {
	val floatAction = rememberFloatButton()
	Column(
		horizontalAlignment = Alignment.End
	) {
		AnimatedVisibility(
			visible = floatAction.expanded,
			enter = fadeIn() + slideInVertically(initialOffsetY = { it }) + expandVertically(),
			exit = fadeOut() + slideOutVertically(targetOffsetY = { it }) + shrinkVertically(),
		) {
			MiniFloatButton()
		}


		FloatingActionButton(
			containerColor = Orange,
			modifier = Modifier.rotate(floatAction.rotation),
			onClick = {
				floatAction.handlerExpand()
			}) {
			Icon(
				tint = Color.Gray,
				imageVector = Icons.Default.Add,
				contentDescription = "Icons Add",
			)
		}
	}

}

@Composable
private fun MiniFloatButton(modifier: Modifier = Modifier) {
	val itemsFloatButton = listOf(
		MiniFloatingButton(Icons.Default.Person, "Contact"),
		MiniFloatingButton(Icons.Default.Home, "Home"),
		MiniFloatingButton(Icons.Default.ThumbUp, "Like"),
		MiniFloatingButton(Icons.Default.Settings, "Setting"),
	)

	LazyColumn(
		modifier = modifier.padding(bottom = 10.dp),
		verticalArrangement = Arrangement.spacedBy(10.dp),
		horizontalAlignment = Alignment.End
	) {
		items(itemsFloatButton) {
			MiniFloatIcon(modifier, it)
		}
	}
}

@Composable
private fun MiniFloatIcon(modifier: Modifier = Modifier, item: MiniFloatingButton) {
	val context = LocalContext.current
	Row(
		verticalAlignment = Alignment.CenterVertically,
		horizontalArrangement = Arrangement.Center
	) {
		Box(
			modifier = modifier
				.border(
					width = 2.dp,
					color = Orange,
					shape = MaterialTheme.shapes.medium
				)
				.padding(10.dp)
		)
		{
			Text(text = item.title)
		}

		Spacer(modifier = Modifier.width(10.dp))
		FloatingActionButton(
			modifier = modifier.size(45.dp),
			onClick = { Toast.makeText(context, item.title, Toast.LENGTH_SHORT).show() },
			containerColor = Orange
		) {
			Icon(
				imageVector = item.icon, contentDescription = item.title,
				tint = Color.Gray,
			)
		}
	}

}

@Preview(showBackground = true)
@Composable
private fun MiniFloatButtonPrev() {
	MiniFloatButton(modifier = Modifier)
}

@Preview(showBackground = true)
@Composable
private fun MiniFloatIconPrev() {
	MiniFloatIcon(
		modifier = Modifier,
		item = MiniFloatingButton(Icons.Default.Person, "Contect")
	)
}

@Preview(showBackground = true)
@Composable
private fun MyFloatingButtonPrev() {
	MyFloatingButton()
}
