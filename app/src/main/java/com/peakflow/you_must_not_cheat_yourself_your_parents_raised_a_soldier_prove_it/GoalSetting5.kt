package com.peakflow.you_must_not_cheat_yourself_your_parents_raised_a_soldier_prove_it

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.ListView
import android.widget.TextView
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject
import androidx.lifecycle.ViewModel

@AndroidEntryPoint
class GoalSetting5 : AppCompatActivity() {
    private lateinit var userData: UserData
    private val viewModel: GoalSetting5ViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_goal_setting5)
        userData = intent.getSerializableExtra("userData") as UserData
        val weeklyGoalSettingList: ListView = findViewById(R.id.weekly_goal_setting_list)
        val ad = ListViewAdapterBreakdownWeekly(this, 4)
        weeklyGoalSettingList.adapter = ad
        val submit: Button = findViewById(R.id.submit_weekly_goals)
        submit.setOnClickListener {
            userData.weekly_goals = ad.getUserInputData()
            val i5 = Intent(this, GoalSetting6::class.java)
            i5.putExtra("userData", userData)
            startActivity(i5)
        }
    }
}

class GoalSetting5ViewModel @Inject constructor() : ViewModel() {
    // Add LiveData and business logic here if needed
} 