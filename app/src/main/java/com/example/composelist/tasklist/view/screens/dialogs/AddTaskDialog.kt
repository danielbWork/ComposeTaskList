package com.example.composelist.tasklist.view.screens.dialogs

import android.R
import androidx.compose.foundation.*
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Edit
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.runtime.*
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.focus.focusRequester
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.text.input.KeyboardCapitalization
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import com.example.composelist.BaseDialog
import com.example.composelist.tasklist.modal.Task
import com.example.composelist.tasklist.viewModal.TaskListViewModel
import kotlinx.coroutines.delay

/**
 * Dialog asking user for name to add a task
 * @param isDialogOpen Flag marking if te dialog is open or closed
 * @param task Used to mark that user is adding a subtask
 */
@OptIn(ExperimentalComposeUiApi::class)
@Composable
fun AddTaskDialog(
		isDialogOpen: MutableState<Boolean>,
		taskListViewModel: TaskListViewModel,
		task: Task? = null
) {
	var newTask by remember { mutableStateOf(TextFieldValue("")) }

	BaseDialog(title = "Add ${if(task !== null) "Subtask" else "Task"}", onDismiss = {
		// Dismiss the dialog when the user clicks outside the dialog or on the back
		isDialogOpen.value = false
	}, onSubmit = {
		if (newTask.text.trim().isNotEmpty()) {

			if(task !== null){
				//add the the new subtask to the task
				taskListViewModel.addSubTask(task , Task(name = newTask.text.trim()))
			}
			else {
				//add the the new task on the taskList
				taskListViewModel.addTask(
						Task(
								name = newTask.text.trim(),// text of the text
								// field
						)
				)
			}


			isDialogOpen.value = false // for closing the dialog
		}
	}, submitText = "Done") {

		// Used to have the TextField be focused when dialog is opned
		val focusRequester = remember { FocusRequester() }

		LaunchedEffect(key1 = Unit) {
			delay(200)
			focusRequester.requestFocus()
		}

		TextField(
				value = newTask,
				onValueChange = {
					newTask = it
				},
				modifier = Modifier
						.fillMaxWidth()
						.border(
								BorderStroke(
										width = 2.dp,
										color = colorResource(
												id = R.color.darker_gray
										)
								),
								shape = RoundedCornerShape(50)
						)
						.verticalScroll(state = rememberScrollState(), enabled = true)
						.heightIn(
								min = 50.dp,
								max = 100.dp
						)
						.focusRequester(focusRequester)
						.wrapContentHeight(),
				colors = TextFieldDefaults.textFieldColors(
						backgroundColor = Color.Transparent,
						focusedIndicatorColor = Color.Transparent,
						unfocusedIndicatorColor = Color.Transparent
				),
				keyboardOptions = KeyboardOptions(
						keyboardType = KeyboardType.Text,
						capitalization = KeyboardCapitalization.Sentences
				),
				placeholder = { Text(text = "My New Task") },
				leadingIcon = {
					Icon(
							imageVector = Icons.Filled.Edit,
							contentDescription = "Edit icon",
							tint = MaterialTheme.colors.secondary,
							modifier = Modifier
									.width(20.dp)
									.height(20.dp)
					)
				},

				)
	}
}
