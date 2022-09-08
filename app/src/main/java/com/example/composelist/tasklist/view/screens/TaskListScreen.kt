package com.example.composelist.tasklist.view.screens

import android.app.Application
import android.content.res.Configuration
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Delete
import androidx.compose.runtime.Composable
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.scale
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.unit.dp
import com.example.composelist.tasklist.modal.Task
import com.example.composelist.tasklist.view.screens.dialogs.DeleteTaskDialog
import com.example.composelist.tasklist.viewModal.TaskListViewModel
import androidx.compose.runtime.*
import androidx.compose.ui.tooling.preview.Devices
import androidx.compose.ui.tooling.preview.Preview
import com.example.composelist.ui.theme.ComposeListTheme

/**
 * Screen displaying the actual tasks list
 */
@Composable
fun TaskListScreen(taskListViewModel: TaskListViewModel) {

	val tasks by taskListViewModel.taskList.observeAsState(initial = listOf())


	// Default screen
	if (tasks.isEmpty()) {
		Text(
				text = "No tasks entered yet", textAlign = TextAlign.Center,
				modifier = Modifier.fillMaxHeight()
		)
		return
	}

	val taskToDelete =
			remember {
				mutableStateOf<Task?>(
						null
				)
			}// for remembering the value of dialog is open or not

	if (taskToDelete.value != null) {

		// remove the task item from list
		DeleteTaskDialog(
				taskListViewModel = taskListViewModel,
				taskToDelete = taskToDelete
		)
	}

	// Actual list
	LazyColumn(modifier = Modifier.fillMaxHeight()) {

		// TODO Add Subtask code
		items(items = tasks) {


			Card(
					shape = RoundedCornerShape(20.dp),
					elevation = 1.dp,
					modifier = Modifier
							.padding(5.dp),
					border = BorderStroke(1.5.dp, MaterialTheme.colors.primary),


					) {
				TaskRow(it, { task, _ ->

					taskListViewModel.toggleTaskCompletion(
							task = task
					)

				}, {

					// Notifies ui to open delete dialog
					taskToDelete.value = it
				})
			}

		}


	}


}

@Composable
fun TaskRow(task: Task, onToggleTask: (Task, Boolean) -> Unit, onDeleteTask: (Task) -> Unit) {

	Row(
			modifier = Modifier
					.padding(10.dp)
					.fillMaxWidth(),
			verticalAlignment = Alignment.CenterVertically
	) {


		Checkbox(
				checked = task.isComplete, //value of the checkbox return from task list


				onCheckedChange = { isEnabled ->
					onToggleTask(task, isEnabled)
				},
		)


		Spacer(modifier = Modifier.width(10.dp))

		Text(
				text = task.name,
				// check if task item is complete then task will be crossed out
				textDecoration = if (task.isComplete) {
					TextDecoration.LineThrough
				} else {
					TextDecoration.None
				},
				modifier = Modifier.fillMaxWidth(0.7f),
				style = MaterialTheme.typography.body2,

				)


		Spacer(modifier = Modifier.fillMaxSize(0.7f))


		Icon(

				imageVector = Icons.Filled.Delete, contentDescription = "Delete",

				modifier = Modifier
						.clickable {
							onDeleteTask(task)
						}
						.scale(1.2f),


				)

	}

}


/**
 * The preview here is more complex then an average preview to avoid adding the unnecessary preview
 *  parameter code, while if the card code changes it's not as critical for such a small app
 */
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
			Task(name = "Test Example", isComplete = false), Task(
			name = "Test Example " +
				   "complete", isComplete = true
	), Task(
			name = "Test Example complete lonansjfknasjknsakgnjgskanj asndklmsadk",
			isComplete = true
	)
	)

	ComposeListTheme {
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
					TaskRow(
							task = it,
							onToggleTask = { _: Task, _: Boolean -> },
							onDeleteTask = { }
					)
				}
			}

		}
	}
}

