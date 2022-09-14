package com.example.composelist.taskInfo.view

import androidx.compose.foundation.layout.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import com.example.composelist.taskInfo.view.screens.dialogs.EditTaskNameDialog
import com.example.composelist.tasklist.modal.Task
import com.example.composelist.tasklist.view.TaskRow
import com.example.composelist.tasklist.viewModal.TaskListViewModel

@Composable
fun TaskInfoRow(task: Task, taskListViewModel: TaskListViewModel) {

	val openEditDialog =
			remember { mutableStateOf(false) }// for remembering the value of dialog is open or not

	if (openEditDialog.value) {

		// Edit the task item from list
		EditTaskNameDialog(
				task = task, isDialogOpen = openEditDialog, taskListViewModel = taskListViewModel
		)
	}

	Row(
			modifier = Modifier
					.fillMaxWidth()
					.height(IntrinsicSize.Min),
			verticalAlignment = Alignment.CenterVertically
	) {

		TaskRow(task,{
			openEditDialog.value = true
		}, { task->

			taskListViewModel.toggleTaskCompletion(
					task = task
			)

		})

	}


}