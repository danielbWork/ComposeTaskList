package com.example.composelist.components

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.window.DialogProperties
import com.example.composelist.modal.Task
import com.example.composelist.view.screens.taskList.TaskListViewModel
import java.util.*

@Composable
fun AddTaskDialog(
	openDialog: MutableState<Boolean>,
	taskListViewModel: TaskListViewModel
) {
	var task by remember { mutableStateOf(TextFieldValue("")) }

	AlertDialog(
		properties = DialogProperties(dismissOnBackPress = true, dismissOnClickOutside = false),
		shape = RoundedCornerShape(16.dp),
		onDismissRequest = {
			// Dismiss the dialog when the user clicks outside the dialog or on the back
			openDialog.value = false
		},
		title = {
			Text(
				text = "Add Task", modifier = Modifier.padding(20.dp),
				fontWeight = FontWeight.Bold,
				fontSize = 16.sp
			)
		},
		text = {
			Column(
				modifier = Modifier.padding(10.dp),
			) {

				// Input field for text
				TextField(
					value = task, onValueChange = { newText ->
						task = newText
					}, modifier = Modifier
						.fillMaxWidth()
						.padding(top = 20.dp),
					shape = RoundedCornerShape(10.dp),
					colors = TextFieldDefaults.textFieldColors(

						focusedIndicatorColor = Color.Transparent,
						unfocusedIndicatorColor = Color.Transparent,
						disabledIndicatorColor = Color.Transparent
					)
				)
			}

		},
		confirmButton = {

			Button(
				onClick = {
					if (task.text.isNotEmpty()) {

						//add the the task on the taskList
						taskListViewModel.addTask(
							Task(
								id = UUID.randomUUID().toString(), //generate unique id

								name = task.text,// text of the text field
								isComplete = false  // by default to do item is incomplete
							)
						)
						openDialog.value = false // for closing the dialog
					}
				},
			) {
				Text(text = "Add")
			}

		},
		dismissButton = {
			Button(

				onClick = {
					openDialog.value =
						false //close the dialog to assign the value false when Cancel button is clicked
				},
			) {
				Text(
					"Cancel")
			}
		}



	)

}