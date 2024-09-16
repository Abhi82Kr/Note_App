package com.example.mvvmnote.Screens

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonColors
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.mvvmnote.Data.Note
import com.example.mvvmnote.Routes
import com.example.mvvmnote.ViewModel.NoteViewModel

@Composable
fun UpdateNote(
    noteId: Long,
    navController: NavController,
    viewModel: NoteViewModel
) {
    // Collect note state as a flow
    val noteFlow = viewModel.getNoteById(noteId)
    val note by noteFlow.collectAsState(initial = null)

    // Manage local state for title and description
    var title by remember { mutableStateOf(note?.title ?: "") }
    var description by remember { mutableStateOf(note?.content ?: "") }

    // Update local state when note changes
    LaunchedEffect(note) {
        title = note?.title ?: ""
        description = note?.content ?: ""
    }

    Scaffold(
        topBar = { TopAppBarView("Update Note", navController) }
    ) { paddingValues ->
        Column(
            modifier = Modifier
                .padding(paddingValues)
                .padding(25.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            NoteTextField(
                label = "Title",
                value = title,
                onValueChanged = { newString ->
                    title = newString
                }
            )

            Spacer(modifier = Modifier.height(10.dp))

            NoteTextField(
                label = "Description",
                value = description,
                onValueChanged = { newString ->
                    description = newString
                }
            )

            Spacer(modifier = Modifier.height(10.dp))

            Button(
                modifier = Modifier.padding(10.dp),
                shape = RoundedCornerShape(10.dp),
                colors = ButtonColors(
                    containerColor = Color.DarkGray,
                    disabledContentColor = Color.Red,
                    contentColor = Color.Red,
                    disabledContainerColor = Color.DarkGray
                ),
                onClick = {
                    // Implement the update functionality

                    viewModel.updateNote(
                        Note(
                            id=noteId,
                            title=title,
                            content = description,
                            timestamp =  System.currentTimeMillis()
                        )
                    )
                    navController.navigate(Routes.homeScreen)
                }
            ) {
                Text("Update Note", fontSize = 15.sp)
            }
        }
    }
}
