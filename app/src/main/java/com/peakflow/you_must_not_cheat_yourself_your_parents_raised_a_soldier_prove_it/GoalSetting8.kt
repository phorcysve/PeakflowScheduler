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
class GoalSetting8 : AppCompatActivity() {
    private lateinit var userData: UserData
    private val viewModel: GoalSetting8ViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_goal_setting8)
        userData = intent.getSerializableExtra("userData") as UserData
        val submit: Button = findViewById(R.id.gs_8_submit_button)
        submit.setOnClickListener {
            val i8 = Intent(this, SignUpActivity::class.java)
            i8.putExtra("userData", userData)
            startActivity(i8)
        }
    }
}

class GoalSetting8ViewModel @Inject constructor() : ViewModel() {
    // Add LiveData and business logic here if needed
} 