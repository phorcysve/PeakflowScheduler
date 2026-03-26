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
class GoalSetting4 : AppCompatActivity() {
    private lateinit var userData: UserData
    private val viewModel: GoalSetting4ViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_goal_setting4)
        userData = intent.getSerializableExtra("userData") as UserData
        val submit: Button = findViewById(R.id.gs_4_submit_button)
        submit.setOnClickListener {
            val i4 = Intent(this, GoalSetting5::class.java)
            i4.putExtra("userData", userData)
            startActivity(i4)
        }
    }
}

class GoalSetting4ViewModel @Inject constructor() : ViewModel() {
    // Add LiveData and business logic here if needed
} 