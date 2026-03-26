package com.peakflow.you_must_not_cheat_yourself_your_parents_raised_a_soldier_prove_it

import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject
import androidx.lifecycle.ViewModel

@AndroidEntryPoint
class ReportAndSuggestionsActivity : AppCompatActivity() {
    private val viewModel: ReportAndSuggestionsViewModel by viewModels()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_report_and_suggestions)
    }
}

class ReportAndSuggestionsViewModel @Inject constructor() : ViewModel() {
    // Add LiveData and business logic here if needed
} 