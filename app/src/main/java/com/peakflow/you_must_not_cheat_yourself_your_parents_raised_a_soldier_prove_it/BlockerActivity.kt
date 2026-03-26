package com.peakflow.you_must_not_cheat_yourself_your_parents_raised_a_soldier_prove_it

import android.os.Bundle
import android.view.WindowManager
import android.widget.Button
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject
import androidx.lifecycle.ViewModel

@AndroidEntryPoint
class BlockerActivity : AppCompatActivity() {
    private val viewModel: BlockerViewModel by viewModels()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        window.setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN)
        setContentView(R.layout.activity_blocker)
        val exitButton: Button = findViewById(R.id.exit_button)
        exitButton.setOnClickListener { finish() }
    }
}

class BlockerViewModel @Inject constructor() : ViewModel() {
    // Add LiveData and business logic here if needed
} 