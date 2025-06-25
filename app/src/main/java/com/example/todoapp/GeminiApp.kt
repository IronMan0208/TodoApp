package com.example.todoapp

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material3.Button
import androidx.compose.material3.Divider
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TodoApp() {
    // State jo text field ke text ko hold karega
    var text by remember { mutableStateOf("") }
    // State jo hamari todo list ko hold karega
    val todoList = remember { mutableStateListOf<String>() }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp) // Screen ke charo taraf thodi space
    ) {
        Text(
            text = "Todo List App",
            style = MaterialTheme.typography.headlineMedium,
            modifier = Modifier.padding(bottom = 16.dp)
        )

        // Input section: TextField aur Add Button
        Row(
            modifier = Modifier.fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically
        ) {
            OutlinedTextField(
                value = text,
                onValueChange = { newText -> text = newText },
                label = { Text("Naya task likhein...") },
                modifier = Modifier.weight(1f) // Jitni jagah hai le lo
            )
            Spacer(modifier = Modifier.width(8.dp)) // Dono ke beech mein space
            Button(onClick = {
                if (text.isNotBlank()) {
                    todoList.add(text) // List mein text add karo
                    text = "" // Text field ko khali kar do
                }
            }) {
                Text("Add")
            }
        }

        Spacer(modifier = Modifier.height(16.dp)) // List aur input ke beech space

        // Todo list ko display karne ke liye
        LazyColumn(
            modifier = Modifier.fillMaxWidth()
        ) {
            items(items = todoList, key = { it }) { todoItem ->
                TodoItem(task = todoItem, onDelete = {
                    todoList.remove(todoItem) // Item ko list se remove karo
                })
            }
        }
    }
}

@Composable
fun TodoItem(task: String, onDelete: () -> Unit) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 8.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Text(
            text = task,
            modifier = Modifier.weight(1f),
            style = MaterialTheme.typography.bodyLarge
        )
        IconButton(onClick = onDelete) {
            Icon(
                imageVector = Icons.Default.Delete,
                contentDescription = "Delete Task",
                tint = MaterialTheme.colorScheme.error // Delete icon ko laal (red) rang do
            )
        }
    }
}

// Preview function taki hum dekh sakein app kaisi dikhegi
@Preview(showBackground = true)
@Composable
fun TodoAppPreview() {
    TodoApp()
}