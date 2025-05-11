package com.fit2081.quiztracker.data.students

import androidx.room.Entity
import androidx.room.PrimaryKey

// Student entity within the Room database
@Entity(tableName = "students")
data class Student (
    @PrimaryKey(autoGenerate = true) val id: Int = 0,
    val studentName: String,
    val studentPassword: String,
    val studentId: String = ""
)