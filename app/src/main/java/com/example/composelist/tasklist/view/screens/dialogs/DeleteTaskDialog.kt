package com.example.composelist.tasklist.view.screens.dialogs

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.window.DialogProperties
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

	AlertDialog(
		properties = DialogProperties(dismissOnBackPress = true, dismissOnClickOutside = false),
		shape = RoundedCornerShape(16.dp),
		onDismissRequest = {
			// Dismiss the dialog when the user clicks outside the dialog or on the back
			taskToDelete.value = null
		},
		title = {
			Text(
				text = "Remove Task", modifier = Modifier.padding(20.dp),
				fontWeight = FontWeight.Bold,
				fontSize = 16.sp
			)
		},
		text = {
			Column(
				modifier = Modifier.padding(10.dp),
			) {

				// Input field for text
				Text(
					text = "Are you sure you want to delete ${task?.name}?",
					style = MaterialTheme.typography.body1
				)
			}

		},
		confirmButton = {

			Button(
				onClick = {
					// When pressed task should always be non-null
					taskListViewModel.removeTask(task!!)
					taskToDelete.value = null // for closing the dialog
				},
			) {
				Text(text = "Remove")
			}

		},
		dismissButton = {
			Button(

				onClick = {
					taskToDelete.value =
						null // close the dialog to assign the value false when Cancel button is clicked
				},
			) {
				Text(
					"Cancel"
				)
			}
		}


	)

}