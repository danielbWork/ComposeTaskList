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
		task: MutableState<Task?>,
		subTask: MutableState<Task?>? = null,
		taskListViewModel: TaskListViewModel,
) {

	val taskToDelete = subTask?.value ?: task.value!!

	BaseDialog(title = "Remove ${if (subTask?.value !== null) "Subtask" else "Task"}", onDismiss = {
		// Dismiss the dialog when the user clicks outside the dialog or on the back
		task.value = null
		subTask?.value = null
	}, onSubmit = {

		// When Called task should always be non-null
		if (subTask?.value !== null) {
			taskListViewModel.removeSubTask(task.value!!, subTask.value!!)
		} else {
			taskListViewModel.removeTask(task.value!!)
		}

		// for closing the dialog
		task.value = null
		subTask?.value = null

	}, submitText = "Remove") {
		Column(
				modifier = Modifier.padding(10.dp),
		) {

			// Input field for text
			Text(
					text = "Are you sure you want to delete ${taskToDelete.name.trim()}?",
					style = MaterialTheme.typography.body1
			)
		}
	}

}