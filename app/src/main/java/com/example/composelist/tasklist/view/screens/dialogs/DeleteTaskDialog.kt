package com.example.composelist.tasklist.view.screens.dialogs

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.composelist.BaseDialog
import com.example.composelist.tasklist.modal.Task
import com.example.composelist.tasklist.viewModal.TaskListViewModel

/**
 * Dialog used to ask user if they want to delete a task
 */
@Composable
fun DeleteTaskDialog(
	taskListViewModel: TaskListViewModel,
	taskToDelete: MutableState<Task?>,
) {

	val task = taskToDelete.value

	BaseDialog(title = "Remove Task", onDismiss = {
		// Dismiss the dialog when the user clicks outside the dialog or on the back
		taskToDelete.value = null
	}, onSubmit = {
		// When pressed task should always be non-null
		taskListViewModel.removeTask(task!!)
		taskToDelete.value = null // for closing the dialog
	}, submitText = "Remove") {
		Column(
				modifier = Modifier.padding(10.dp),
		) {

			// Input field for text
			Text(
					text = "Are you sure you want to delete ${task?.name?.trim()}?",
					style = MaterialTheme.typography.body1
			)
		}
	}

}