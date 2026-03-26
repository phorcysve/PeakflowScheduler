package com.peakflow.you_must_not_cheat_yourself_your_parents_raised_a_soldier_prove_it

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import dagger.hilt.android.AndroidEntryPoint
import java.text.SimpleDateFormat
import java.util.*
import javax.inject.Inject
import androidx.lifecycle.ViewModel

@AndroidEntryPoint
class GoalSetting1 : AppCompatActivity() {
    private lateinit var longTermGoal: EditText
    private lateinit var deadline: EditText
    private lateinit var submitBtn: Button
    private lateinit var userData: UserData
    private val viewModel: GoalSetting1ViewModel by viewModels()
    private val dateFormat = SimpleDateFormat("dd/MM/yyyy", Locale.getDefault())

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_goal_setting1)

        longTermGoal = findViewById(R.id.goal_input_main)
        deadline = findViewById(R.id.gs_deadline_date_ip)
        submitBtn = findViewById(R.id.gs_1_submit_button)
        userData = intent.getSerializableExtra("userData") as UserData

        submitBtn.setOnClickListener {
            val dateStr = deadline.text.toString().trim()
            val longTermGoalStr = longTermGoal.text.toString().trim()
            try {
                dateFormat.parse(dateStr)
                userData.final_goal = longTermGoalStr
                userData.deadline = dateStr
                val i = Intent(this, GoalSetting2::class.java)
                i.putExtra("userData", userData)
                startActivity(i)
            } catch (e: Exception) {
                deadline.error = "Invalid date format!"
            }
        }
    }
}

class GoalSetting1ViewModel @Inject constructor() : ViewModel() {
    // Add LiveData and business logic here if needed
} 