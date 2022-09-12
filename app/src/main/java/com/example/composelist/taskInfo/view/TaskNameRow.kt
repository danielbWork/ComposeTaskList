package com.example.composelist.taskInfo.view

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material.Checkbox
import androidx.compose.material.Icon
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material.icons.filled.Edit
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.scale
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.unit.dp
import com.example.composelist.taskInfo.dialogs.EditTaskDialog
import com.example.composelist.tasklist.modal.Task
import com.example.composelist.tasklist.view.screens.dialogs.DeleteTaskDialog
import com.example.composelist.tasklist.viewModal.TaskListViewModel

@Composable
fun TaskNameRow(task: Task, taskListViewModel: TaskListViewModel) {

	val openEditDialog =
			remember { mutableStateOf(false) }// for remembering the value of dialog is open or not


	if (openEditDialog.value) {

		// remove the task item from list
		EditTaskDialog(
				task = task, isDialogOpen = openEditDialog, taskListViewModel = taskListViewModel
		)
	}

	Row(
			modifier = Modifier
					.fillMaxWidth()
					.height(IntrinsicSize.Min),
			verticalAlignment = Alignment.CenterVertically
	) {

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


		Spacer(modifier = Modifier.fillMaxSize(0.5f))

		Icon(

				imageVector = Icons.Filled.Edit, contentDescription = "Edit",
				modifier = Modifier
						.clickable {
							openEditDialog.value = true
						}
						.scale(1.2f),
		)
	}


}