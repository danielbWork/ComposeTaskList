package com.example.composelist

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.padding
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.runtime.*
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.composelist.tasklist.modal.Task
import com.example.composelist.tasklist.view.screens.dialogs.AddTaskDialog
import com.example.composelist.tasklist.view.screens.TaskListScreen
import com.example.composelist.tasklist.view.screens.dialogs.DeleteTaskDialog
import com.example.composelist.tasklist.viewModal.TaskListViewModel

@Composable
fun HomeScreen(navController: NavController, taskListViewModel: TaskListViewModel) {

	val openAddDialog =
			remember { mutableStateOf(false) }// for remembering the value of dialog is open or not

	val taskToDelete =
			remember {
				mutableStateOf<Task?>(
						null
				)
			}// for remembering the value of dialog is open or not

	if (taskToDelete.value != null) {

		// remove the task item from list
		DeleteTaskDialog(
				task = taskToDelete,
				taskListViewModel = taskListViewModel,
		)
	}

	val tasks by taskListViewModel.taskList.observeAsState(initial = listOf())



	Scaffold(

			floatingActionButton = { // when floating action button click it change the value of openAddDialog to true,

				FloatingActionButton(onClick = { openAddDialog.value = true }) {
					Icon(imageVector = Icons.Filled.Add, contentDescription = "Add Task to item")

					//check if openDialog value is true then it display Alert Dialog
					if (openAddDialog.value) {
						AddTaskDialog(isDialogOpen = openAddDialog, taskListViewModel)
					}


				}

			}, topBar = {
		TopAppBar() {
			Text(modifier = Modifier.padding(start = 20.dp), text = "Task List")
		}
	}) { paddingValues ->

		Column(
				modifier = Modifier
						.padding(paddingValues)
						.padding(20.dp)
						.fillMaxHeight(),
				verticalArrangement = Arrangement.Center,
				horizontalAlignment = Alignment.CenterHorizontally
		) {

			TaskListScreen(
					tasks = tasks,
					onEditPress = {
						navController.navigate("info/${it.id}")
					},
					onToggleTask = {
						taskListViewModel.toggleTaskCompletion(task = it)
					},
					onDeletePress = {
						taskToDelete.value = it
					})
		}

	}

}