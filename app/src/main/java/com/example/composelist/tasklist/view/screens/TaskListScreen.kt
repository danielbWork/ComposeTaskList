package com.example.composelist.tasklist.view.screens

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
import com.example.composelist.tasklist.view.TaskRow

/**
 * Screen displaying the actual tasks list
 */
@Composable
fun TaskListScreen(taskListViewModel: TaskListViewModel, onEditPress: (Task) -> Unit) {

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
				TaskRow(it, { task->

					taskListViewModel.toggleTaskCompletion(
							task = task
					)

				}, {

					// Notifies ui to open delete dialog
					taskToDelete.value = it
				}, onEditPress)
			}

		}


	}


}



