package com.example.mvvmnote.Data

import androidx.room.Entity
import androidx.room.PrimaryKey
import java.util.Date

@Entity(tableName = "note")
data class Note(
    val title:String,
    val content:String,
    val timestamp:Long,
    @PrimaryKey(autoGenerate = true)
    val id: Long = 0L,
)

val dummyList = listOf(
    Note(
        title = "Meeting Notes",
        content = "Discuss the project timeline and milestones.",
        timestamp = System.currentTimeMillis(),
        id = 1
    ),
    Note(
        title = "Grocery List",
        content = "Eggs, Milk, Bread, Butter, Fruits",
        timestamp = System.currentTimeMillis(),
        id = 2
    ),
    Note(
        title = "To-Do",
        content = "Complete the MVVM Note app implementation.",
        timestamp = System.currentTimeMillis(),
        id = 3
    ),
    Note(
        title = "Workout Plan",
        content = "Monday: Chest & Back, Tuesday: Legs & Abs",
        timestamp = System.currentTimeMillis(),
        id = 4
    ),
    Note(
        title = "Book List",
        content = "Atomic Habits, Deep Work, The Lean Startup",
        timestamp = System.currentTimeMillis(),
        id = 5
    )
)