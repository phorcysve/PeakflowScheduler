package com.peakflow.you_must_not_cheat_yourself_your_parents_raised_a_soldier_prove_it

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject
import androidx.lifecycle.ViewModel

@AndroidEntryPoint
class GoalSetting6 : AppCompatActivity() {
    private lateinit var userData: UserData
    private val viewModel: GoalSetting6ViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_goal_setting6)
        userData = intent.getSerializableExtra("userData") as UserData
        val proceed: Button = findViewById(R.id.gs_6_proceed_btn)
        proceed.setOnClickListener {
            val i6 = Intent(this, GoalSetting7::class.java)
            i6.putExtra("userData", userData)
            startActivity(i6)
        }
    }
}

class GoalSetting6ViewModel @Inject constructor() : ViewModel() {
    // Add LiveData and business logic here if needed
} 