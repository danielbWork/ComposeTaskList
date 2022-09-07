package com.example.composelist

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import com.example.composelist.ui.theme.ComposeListTheme

class MainActivity : ComponentActivity() {
	override fun onCreate(savedInstanceState: Bundle?) {
		super.onCreate(savedInstanceState)
		setContent {

			ComposeListTheme {
				HomeScreen()
			}
		}
	}
}

//
//
//@Composable
//fun TaskList(tasks: List<Task>) {
//	LazyColumn {
//		items(tasks) { task ->
//			TaskCard(task = task)
//		}
//	}
//}
//
//@Preview(name = "Light Mode")
//@Preview(
//	uiMode = Configuration.UI_MODE_NIGHT_YES,
//	showBackground = true,
//	name = "Dark Mode"
//)
//@Composable
//fun PreviewTaskList() {
//	ComposeListTheme() {
//		TaskList(tasks = MockTasks.tasks)
//	}
//}
//
//@Composable
//fun TaskCard(task: Task) {
//
//	Surface(
//		shape = MaterialTheme.shapes.medium,
//		elevation = 1.dp, // surfaceColor color will be changing gradually from primary to surface
//		// animateContentSize will change the Surface size gradually
//		modifier = Modifier
//			.animateContentSize()
//			.padding(all = 8.dp)
//			.fillMaxWidth(),
//	) {
//		Row(
//			modifier = Modifier
//				.padding(horizontal = 2.dp)
//				.fillMaxWidth(),
//			verticalAlignment = Alignment.CenterVertically
//		) {
//
//			// We keep track if the task is expanded or not in this
//			// variable
////        var isExpanded by remember { mutableStateOf(false) }
//
//			Text(
//				text = task.name,
//				modifier = Modifier
//					.padding(all = 4.dp)
//					.weight(1.5f, fill = false),
//				style = MaterialTheme.typography.body2,
//				textDecoration = if (task.isComplete) TextDecoration.LineThrough
//				else TextDecoration.None
//
//			)
//
//			Icon(
//				imageVector = Icons.Filled.MoreVert,
//				contentDescription = "Edit Task",
//				modifier = Modifier.weight(0.1f)
//			)
//
//
//		}
//	}
//
//}
//
//@Composable
//fun TaskInput() {
//
//}
//
//
//data class Message(val author: String, val body: String)
//
//@Composable
//fun MessageCard(msg: Message) {
//	Row(modifier = Modifier.padding(all = 8.dp)) {
//		Image(
//			painter = painterResource(id = R.drawable.ic_launcher_background),
//			contentDescription = " Test image",
//			modifier = Modifier// Set image size to 40 dp
//				.size(40.dp)
//				// Clip image to be shaped as a circle
//				.clip(CircleShape)
//				.border(1.5.dp, MaterialTheme.colors.secondary, CircleShape)
//
//		)
//
//		// Add a horizontal space between the image and the column
//		Spacer(modifier = Modifier.width(8.dp))
//
//		// We keep track if the message is expanded or not in this
//		// variable
//		var isExpanded by remember { mutableStateOf(false) }
//		// surfaceColor will be updated gradually from one color to the other
//		val surfaceColor by animateColorAsState(
//			if (isExpanded) MaterialTheme.colors.primary else MaterialTheme.colors.surface,
//		)
//
//
//		Column(modifier = Modifier.clickable { isExpanded = !isExpanded }) {
//			Text(
//				text = msg.author,
//				color = MaterialTheme.colors.secondaryVariant,
//				style = MaterialTheme.typography.subtitle2
//			)
//			// Add a vertical space between the author and message texts
//			Spacer(modifier = Modifier.height(4.dp))
//			Surface(
//				shape = MaterialTheme.shapes.medium,
//				elevation = 1.dp, // surfaceColor color will be changing gradually from primary to surface
//				color = surfaceColor,
//				// animateContentSize will change the Surface size gradually
//				modifier = Modifier
//					.animateContentSize()
//					.padding(1.dp)
//			) {
//				Text(
//					text = msg.body,
//					modifier = Modifier.padding(all = 4.dp),
//					// If the message is expanded, we display all its content
//					// otherwise we only display the first line
//					maxLines = if (isExpanded) Int.MAX_VALUE else 1,
//					style = MaterialTheme.typography.body2
//
//				)
//			}
//		}
//	}
//}
//
//
////@Preview(name = "Light Mode")
//@Preview(
//	uiMode = Configuration.UI_MODE_NIGHT_YES,
//	showBackground = true,
//	name = "Dark Mode"
//)
//@Composable
//fun PreviewMessageCard() {
//	ComposeListTheme() {
//		Surface() {
//			MessageCard(
//				msg = Message("Colleague", "Hey, take a look at Jetpack Compose, it's great!")
//			)
//
//		}
//	}
//}
//
//
//@Composable
//fun Conversation(messages: List<Message>) {
//	LazyColumn {
//		items(messages) { message ->
//			MessageCard(msg = message)
//		}
//	}
//}

//@Preview
//@Composable
//fun PreviewConversation() {
//    ComposeListTheme() {
//        Conversation(SampleData.conversationSample)
//    }
//}


//@Composable
//fun Greeting(name: String) {
//    Text(text = "Hello $name!")
//}
//
//@Preview(showBackground = true)
//@Composable
//fun DefaultPreview() {
//    ComposeListTheme {
//        Greeting("Android")
//    }
//}