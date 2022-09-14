package com.example.composelist.tasklist.view.screens

import android.content.res.Configuration
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.example.composelist.tasklist.modal.Task
import com.example.composelist.tasklist.view.screens.dialogs.DeleteTaskDialog
import com.example.composelist.tasklist.viewModal.TaskListViewModel
import androidx.compose.runtime.*
import androidx.compose.ui.tooling.preview.Devices
import androidx.compose.ui.tooling.preview.Preview
import com.example.composelist.tasklist.view.TaskRow
import com.example.composelist.ui.theme.ComposeListTheme

/**
 * Screen displaying the actual tasks list
 */
@Composable
fun TaskListScreen(
		tasks: List<Task>,
		onEditPress: (Task) -> Unit,
		onToggleTask: (Task) -> Unit,
		onDeletePress: ((Task) -> Unit)? = null
) {

	// Default screen
	if (tasks.isEmpty()) {
		Text(
				text = "No tasks entered yet", textAlign = TextAlign.Center,
				modifier = Modifier.fillMaxHeight()
		)
		return
	}

	// Actual list
	LazyColumn(modifier = Modifier.fillMaxHeight()) {

		items(items = tasks) {

			Card(
					shape = RoundedCornerShape(20.dp),
					elevation = 1.dp,
					modifier = Modifier
							.padding(5.dp),
					border = BorderStroke(1.5.dp, MaterialTheme.colors.primary),


					) {
				TaskRow(it, onEditPress, onToggleTask, onDeletePress)
			}

		}


	}


}


@Preview(
		name = "Light Mode",
		showBackground = true,
		device = Devices.PIXEL
)
@Preview(
		uiMode = Configuration.UI_MODE_NIGHT_YES,
		showBackground = true,
		name = "Dark Mode",
		device = Devices.PIXEL
)
@Composable
fun TaskListPreview() {

	val tasks = listOf(
			Task(name = "Test Example"),
			Task(
					name = "Test Example " +
						   "complete", isComplete = true
			),
			Task(
					name = "Test Example complete lonansjfknasjknsakgnjgskanj asndklmsadk",
					isComplete = true
			)
	)

	ComposeListTheme {
		// Actual list
		TaskListScreen(tasks = tasks, onEditPress = {}, onToggleTask = {}, onDeletePress = {})

	}
}



