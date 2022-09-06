package com.example.composelist.view.screens.taskList

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.Checkbox
import androidx.compose.material.Icon
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Delete
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.unit.dp

/**
 * Screen displaying the actual tasks list
 */
@Composable
fun TaskListScreen(taskListViewModel: TaskListViewModel) {

	// Default screen
	if (taskListViewModel.getTaskList().isEmpty()) {
		Text(
			text = "No tasks entered yet", textAlign = TextAlign.Center,
			modifier = Modifier.fillMaxHeight()
		)
		return
	}

	// Actual list
	LazyColumn(modifier = Modifier.fillMaxHeight()) {

		// TODO Separate this to another file for task
		// TODO Add Subtask code
		items(taskListViewModel.getTaskList()) {


			Card(
				shape = RoundedCornerShape(20.dp),
				elevation = 1.dp,
				modifier = Modifier.padding(5.dp)
			) {

				Row(modifier = Modifier.padding(20.dp)) {
					Text(
						text = it.name,
						// check if task item is complete then task will be crossed out
						textDecoration = if (it.isComplete) {
							TextDecoration.LineThrough
						} else {
							TextDecoration.None
						}
					)
					
					
					Spacer(modifier = Modifier.fillMaxSize(0.7f))
					
					
					Checkbox(
						checked = it.isComplete, //value of the checkbox return from task list


						onCheckedChange = { value ->

							taskListViewModel.toggleTaskCompletion(
								task = it
							)

						},
					)

					
					Spacer(modifier = Modifier.width(10.dp))

					// TODO Add alert
					Icon(

						imageVector = Icons.Filled.Delete, contentDescription = "Delete",

						modifier = Modifier.clickable {
							taskListViewModel.removeTask(it) // remove the task item from list
						},
					)				}

			}

		}


	}


}