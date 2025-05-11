package com.fit2081.quiztracker.data

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.fit2081.quiztracker.data.quizAttempts.QuizAttempt
import com.fit2081.quiztracker.data.quizAttempts.QuizAttemptDao
import com.fit2081.quiztracker.data.students.Student
import com.fit2081.quiztracker.data.students.StudentDao
import java.time.Instant

@Database(entities = [Student::class, QuizAttempt::class], version = 1, exportSchema = false)

// Abstract class representing the college databse
abstract class CollegeDatabase : RoomDatabase() {
    // Provides access to the StudentDao interface for performing database operations on Student entities
    abstract fun studentDao(): StudentDao

    // Provides access to the QuizAttemptDao
    abstract fun quizAttemptDao(): QuizAttemptDao

    companion object {
        // Singleton instance of the database
        @Volatile
        private var Instance: CollegeDatabase? = null

        // Retrieves the singleton instance of the database
        // If an instance already exists, return the existing instance. Otherwise, creates a new one
        fun getDatabase(context: Context): CollegeDatabase {
            return Instance ?: synchronized(this) {
                Room.databaseBuilder(context, CollegeDatabase::class.java, "item_database")
                    .build()
                    .also { Instance = it }
            }
        }
    }
}