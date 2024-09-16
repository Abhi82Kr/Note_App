package com.example.mvvmnote.Screens

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.mvvmnote.Data.Note
import com.example.mvvmnote.ViewModel.NoteViewModel


@Composable
fun ViewDetail(noteId: Long, navController: NavController, viewModel: NoteViewModel) {
    val noteFlow = viewModel.getNoteById(noteId)
    val note by noteFlow.collectAsState(initial = null)

    Scaffold(
        topBar = {
            TopAppBarView(title = note?.title ?: "Loading...", navController)
        }
    ) { paddingValues ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues)
                .padding(30.dp)
        ) {
            note?.let {
                Text(
                    text = it.content,
                    textAlign = TextAlign.Center,
                    fontSize = 20.sp
                )
            } ?: run {
                // Show loading indicator or placeholder
                Text(
                    text = "Loading...",
                    textAlign = TextAlign.Center,
                    fontSize = 20.sp
                )
            }
        }
    }
}
//@Preview(showBackground = true)
//@Composable
//fun DefaultPreview() {
//    ViewDetail()
//}

