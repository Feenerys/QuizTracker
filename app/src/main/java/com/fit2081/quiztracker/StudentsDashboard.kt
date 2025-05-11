package com.fit2081.quiztracker

import android.widget.Toast
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.Checkbox
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.fit2081.quiztracker.data.AuthManager
import com.fit2081.quiztracker.data.quizAttempts.QuizAttempt
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import java.text.SimpleDateFormat
import kotlin.random.Random

@Composable
fun StudentsDashboard() {
    Column() {
        // Mutable state variables to track checkbox states for each question
        var q1 by remember { mutableStateOf(false) }
        var q2 by remember { mutableStateOf(false) }
        var q3 by remember { mutableStateOf(false) }

        // Get the current context
        val _context = LocalContext.current

        // Display the quiz title with styling
        Text (
            text = "FIT2081 Mobile Quiz",
            style = TextStyle(
                fontSize = 30.sp,
                fontWeight = androidx.compose.ui.text.font.FontWeight.Bold
            )
        )

        Spacer(modifier = Modifier.padding(10.dp))

        Text("Select the correct answers to the following statements")
        Spacer(modifier = Modifier.padding(20.dp))

        Row(
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            Checkbox(
                checked = q1,
                onCheckedChange = {q1 = it}
            )
            Text("Paris is the capitale of France.")
        }


        Row(
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            Checkbox(
                checked = q2,
                onCheckedChange = {q2 = it}
            )
            Text("Vincent van Gogh painted the Mona Lisa")
        }

        Row(
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            Checkbox(
                checked = q3,
                onCheckedChange = {q3 = it}
            )
            Text("Mount Kosciuszko is the highest mountain in Australia")
        }
        Spacer(modifier = Modifier.padding(20.dp))

        Button(
            onClick = {
                var totalMark = 0.0
                if (q1) totalMark += 1
                if (!q2) totalMark += 1
                if (q3) totalMark += 1

                var random4digits = Random.nextInt(1000, 9999)

                var quizId = "Qz$random4digits"
                val dateFormat = SimpleDateFormat("dd/MM/yyyy")
                val currentDate = dateFormat.format(System.currentTimeMillis())

                var attempt: QuizAttempt = QuizAttempt(
                    studentId = AuthManager.getStudentId().toString(),
                    quizId = quizId,
                    quizDate = currentDate,
                    finalMark = totalMark
                )

                CoroutineScope(Dispatchers.IO).launch {
                    quizAttemptViewMode.insertQuizAttempt(attempt)
                }

                q1 = false
                q2 = false
                q3 = false
                Toast.makeText(_context, "Quiz Attempt Submitted", Toast.LENGTH_SHORT).show()
            }, modifier = Modifier.align(Alignment.End)
        ) {
            Text("Submit Quiz Attempt")
        }
        HorizontalDivider()
        
    }
}