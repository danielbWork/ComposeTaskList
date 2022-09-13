package com.example.composelist.taskInfo.view.screens

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.composelist.taskInfo.view.TaskInfoRow
import com.example.composelist.taskInfo.view.TaskTimeRow
import com.example.composelist.tasklist.modal.Task
import com.example.composelist.tasklist.viewModal.TaskListViewModel

@Composable
fun TaskInfoScreen(navController: NavController, taskListViewModel: TaskListViewModel, taskId: Int){

	val task by taskListViewModel.getTaskByID(taskId).observeAsState(initial = Task(id= -1,
			"Fake", false))

	Scaffold(
 topBar = {
		TopAppBar {
			Icon(imageVector = Icons.Filled.ArrowBack, contentDescription = "go back", modifier =
			Modifier
					.clickable {
				navController.navigateUp()
			})
			Text( modifier = Modifier.padding(start = 5.dp), text = "Task Info")
		}
	}) {

		Column(
				modifier = Modifier
						.padding(it)
						.padding(horizontal = 20.dp, vertical = 10.dp)
						.fillMaxHeight(),
				verticalArrangement = Arrangement.Top,
				horizontalAlignment = Alignment.CenterHorizontally
		) {

			TaskInfoRow(task,taskListViewModel)

			CustomDivider()

			TaskTimeRow(task.creationTime)

			CustomDivider()

		}
	}


}

@Composable
fun CustomDivider(){
	Divider(modifier = Modifier.padding(vertical = 12.dp))
}