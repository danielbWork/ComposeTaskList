package com.example.composelist.tasklist.view

import android.content.res.Configuration
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material.icons.filled.Edit
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.scale
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.tooling.preview.Devices
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.composelist.tasklist.modal.Task
import com.example.composelist.ui.theme.ComposeListTheme

/**
 *  A row of action and info regarding a task
 */
@Composable
fun TaskRow(
		task: Task,
		onToggleTask: (Task) -> Unit,
		onDeleteTask: ((Task)-> Unit)? = null,
		onEditPress: (Task) -> Unit
) {

	Row(
			modifier = Modifier
					.padding(horizontal = 10.dp)
					.fillMaxWidth(),
			verticalAlignment = Alignment.CenterVertically
	) {


		Checkbox(
				checked = task.isComplete, //value of the checkbox return from task list


				onCheckedChange = {
					onToggleTask(task)
				},
		)


		Spacer(modifier = Modifier.width(10.dp))

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


		if(onDeleteTask === null){
			Spacer(modifier = Modifier.fillMaxSize(0.5f))
		}

		Row() {
			Icon(

					imageVector = Icons.Filled.Edit, contentDescription = "Edit",

					modifier = Modifier
							.clickable {
								onEditPress(task)
							}
							.scale(1.2f),

					)

			Spacer(modifier = Modifier.fillMaxSize(0.4f))

			if (onDeleteTask !== null) {
				Icon(

						imageVector = Icons.Filled.Delete, contentDescription = "Delete",

						modifier = Modifier
								.clickable {
									onDeleteTask(task)
								}
								.scale(1.2f),


						)
			}
		}
	}

}


/**
 * The preview here is more complex then an average preview to avoid adding the unnecessary preview
 *  parameter code, while if the card code changes it's not as critical for such a small app
 */
@Preview(
		name = "Light Mode",
		showBackground = true,
		device = Devices.PIXEL
)
@Preview(
		uiMode = Configuration.UI_MODE_NIGHT_YES,
		showBackground = true,
		name = "Dark Mode",
		device = Devices.PIXEL
)
@Composable
fun TaskListPreview() {

	val tasks = listOf(
			Task(name = "Test Example", isComplete = false),
			Task(
					name = "Test Example " +
						   "complete", isComplete = true
			),
			Task(
					name = "Test Example complete lonansjfknasjknsakgnjgskanj asndklmsadk",
					isComplete = true
			),
			Task(name = "NODELETE", isComplete = false)
	)

	ComposeListTheme {
		// Actual list
		LazyColumn(modifier = Modifier.fillMaxHeight()) {

			items(items = tasks) {
				Card(
						shape = RoundedCornerShape(20.dp),
						elevation = 1.dp,
						modifier = Modifier
								.padding(5.dp),
						border = BorderStroke(1.5.dp, MaterialTheme.colors.primary),


						) {

					if (it.name != "NODELETE"){
						TaskRow(
								task = it,
								onToggleTask = { },
								onDeleteTask = {},
								onEditPress = {}
						)
					}
					else {
						TaskRow(
								task = it,
								onToggleTask = { },
								onDeleteTask = null,
								onEditPress = {}
						)
					}

				}

			}

		}
	}
}