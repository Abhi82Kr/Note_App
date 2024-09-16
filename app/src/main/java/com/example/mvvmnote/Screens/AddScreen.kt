package com.example.mvvmnote.Screens

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonColors
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.mvvmnote.Data.Note
import com.example.mvvmnote.R
import com.example.mvvmnote.Routes
import com.example.mvvmnote.ViewModel.NoteViewModel

@Composable
fun AddNote(
    navController: NavController,
    viewModel: NoteViewModel
) {
    val Title = remember { mutableStateOf("") }
    val desc = remember { mutableStateOf("") }

    Scaffold(
        topBar = { TopAppBarView("Add Note",navController ) }
    ) { paddingValues ->
        Column(
            modifier = Modifier.fillMaxSize()
                .padding(paddingValues).padding(25.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {

            NoteTextField(
                label = "Title",
                value = Title.value,
                onValueChanged = { newString->
                    Title.value=newString
                }
            )

            Spacer(modifier = Modifier.height(10.dp))

            NoteTextField(
                label = "Description",
                value = desc.value,
                onValueChanged = { newString ->
                   desc.value=newString
                } // Fixed onValueChanged lambda
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
                    viewModel.insertNote(
                        Note(
                            title = Title.value.trim(),
                            content = desc.value.trim(),
                            timestamp = System.currentTimeMillis()
                        )
                    )
                    navController.navigate(Routes.homeScreen)

            }) {
                Text("Add Note", fontSize = 15.sp)
            }
        }
    }
}


@Composable
fun NoteTextField(
    label: String,
    value: String,
    onValueChanged: (String) -> Unit
) {
    OutlinedTextField(

        value = value,
        onValueChange = onValueChanged,
        label = { Text(text = label, color = Color.Black) },
        modifier = Modifier.fillMaxWidth(),
        keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Text),
        colors = OutlinedTextFieldDefaults.colors(
            focusedBorderColor = colorResource(id = R.color.black),
            unfocusedBorderColor = colorResource(id = R.color.black),
            cursorColor = colorResource(id = R.color.black),
            focusedTextColor = colorResource(id = R.color.black),
            unfocusedTextColor = colorResource(id = R.color.black),
        )
    )
}



//@Preview(showBackground = true)
//@Composable
//fun DefaultPreview() {
//    AddNote()
//}
