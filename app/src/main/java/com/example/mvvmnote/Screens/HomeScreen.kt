package com.example.mvvmnote.Screens





import android.annotation.SuppressLint
import androidx.compose.foundation.clickable

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
import androidx.compose.foundation.shape.CircleShape

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Create
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material.icons.filled.Edit
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color

import androidx.compose.ui.res.colorResource
import androidx.compose.ui.text.font.FontWeight

import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.mvvmnote.Data.Note

import com.example.mvvmnote.R
import com.example.mvvmnote.Routes
import com.example.mvvmnote.ViewModel.NoteViewModel


@SuppressLint("SuspiciousIndentation")
@Composable
fun HomeScreen(
    navController: NavController,
    viewModel: NoteViewModel

){

    val notes by viewModel.notes.collectAsState()

        Scaffold(
            modifier = Modifier.padding(20.dp),
            topBar = { TopAppBarView("All notes", navController) },
            floatingActionButton = {
                FloatingActionButton(
                    modifier = Modifier.padding(all = 10.dp),
                    shape = CircleShape,
                    onClick = { navController.navigate(Routes.addScreen) },
                    contentColor = colorResource(id = R.color.orange),
                    containerColor = Color.DarkGray

                ) {
                    Icon(imageVector = Icons.Default.Create, contentDescription = "Create")
                }
            },


            ) { paddingValues ->
            LazyColumn(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(paddingValues)
            ) {
                items(notes) { item ->
                    NoteItem(item, navController, viewModel)
                }


            }

        }
    }

@Composable
fun NoteItem(note: Note, navController: NavController,viewModel: NoteViewModel) {
    Card(
        modifier = Modifier
            .fillMaxWidth().height(120.dp)
            .padding(top = 8.dp, start = 8.dp, end = 8.dp, bottom = 7.dp)
            .clickable {
                // Update navigation to use a route or action
                navController.navigate(Routes.viewDetail+"/${note.id}")
            },
        elevation = CardDefaults.cardElevation(defaultElevation = 20.dp),
        colors = CardDefaults.cardColors(containerColor = Color.LightGray)
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Column(
                modifier = Modifier.weight(1f)
            ) {
                Text(text = note.title, fontWeight = FontWeight.ExtraBold)
                //Text(text = note.content, fontWeight = FontWeight.Normal)
            }

            Spacer(modifier = Modifier.width(20.dp))

            Column(
                horizontalAlignment = Alignment.End
            ) {
                IconButton(onClick = {
                    // Handle edit action
                    navController.navigate("updateNote/${note.id}")

                }) {
                    Icon(imageVector = Icons.Default.Edit, contentDescription = "Edit Note")
                }
                Spacer(modifier = Modifier.height(8.dp))
                IconButton(onClick = {
                    viewModel.deleteNote(note)
                }) {
                    Icon(imageVector = Icons.Default.Delete, contentDescription = "Delete Note")
                }
            }
        }
    }
}
//@Preview(showBackground = true)
//@Composable
//fun DefaultPreview() {
//    HomeScreen()
//}

