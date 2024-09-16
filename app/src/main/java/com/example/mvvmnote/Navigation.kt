package com.example.mvvmnote

import androidx.compose.runtime.Composable
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController

import com.example.mvvmnote.Screens.AddNote
import com.example.mvvmnote.Screens.HomeScreen
import com.example.mvvmnote.Screens.UpdateNote
import com.example.mvvmnote.Screens.ViewDetail

import com.example.mvvmnote.ViewModel.NoteViewModel

@Composable
fun Navigation(
    navController: NavHostController = rememberNavController(),
    viewModel: NoteViewModel = viewModel()
) {
    NavHost(navController = navController, startDestination = Routes.homeScreen) {
        composable(Routes.homeScreen){
            HomeScreen(navController,viewModel)
        }
        composable(Routes.addScreen){
            AddNote(navController,viewModel)
        }
        composable("${Routes.viewDetail}/{noteId}") { backStackEntry ->
            val noteId = backStackEntry.arguments?.getString("noteId")?.toLong() ?: 0L
            ViewDetail(noteId, navController, viewModel)
        }
        composable("${Routes.updateDetail}/{noteId}") { backStackEntry ->
            val noteId = backStackEntry.arguments?.getString("noteId")?.toLong() ?: 0L
            UpdateNote(noteId,navController, viewModel )
        }

    }
}


