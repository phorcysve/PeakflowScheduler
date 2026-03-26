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
class GoalSetting2 : AppCompatActivity() {
    private lateinit var userData: UserData
    private lateinit var proceedBtn: Button
    private val viewModel: GoalSetting2ViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_goal_setting2)
        userData = intent.getSerializableExtra("userData") as UserData
        proceedBtn = findViewById(R.id.gs_2_submit_button)
        proceedBtn.setOnClickListener {
            val i2 = Intent(this, GoalSetting3::class.java)
            i2.putExtra("userData", userData)
            startActivity(i2)
        }
    }
}

class GoalSetting2ViewModel @Inject constructor() : ViewModel() {
    // Add LiveData and business logic here if needed
} 