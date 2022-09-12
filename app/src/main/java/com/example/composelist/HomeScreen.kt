package com.example.composelist

import android.app.Application
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
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import com.example.composelist.tasklist.view.screens.dialogs.AddTaskDialog
import com.example.composelist.tasklist.view.screens.TaskListScreen
import com.example.composelist.tasklist.viewModal.TaskListViewModalFactory
import com.example.composelist.tasklist.viewModal.TaskListViewModel

@Composable
fun HomeScreen(navController: NavController, taskListViewModel: TaskListViewModel) {

	val openAddDialog =
		remember { mutableStateOf(false) }// for remembering the value of dialog is open or not


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
				Text( modifier = Modifier.padding(start = 20.dp), text = "Task List")
			}
		}) {

		Column(
			modifier = Modifier.
			padding(it)
				.padding(20.dp)
				.fillMaxHeight(),
			verticalArrangement = Arrangement.Center,
			horizontalAlignment = Alignment.CenterHorizontally
		) {

			TaskListScreen(taskListViewModel) { task ->
				navController.navigate("info/${task.id}")
			}
		}

	}

}