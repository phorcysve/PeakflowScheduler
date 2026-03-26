package com.peakflow.you_must_not_cheat_yourself_your_parents_raised_a_soldier_prove_it

import android.os.Bundle
import android.widget.ListView
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject
import androidx.lifecycle.ViewModel

@AndroidEntryPoint
class RoutinePlanner : AppCompatActivity() {
    private lateinit var listView: ListView
    private lateinit var adapter: listview_adapter_routine_info
    private val viewModel: RoutinePlannerViewModel by viewModels()
    private val numberOfDays = 7
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_routine_planner)
        listView = findViewById(R.id.list_view_routine_planner)
        adapter = listview_adapter_routine_info(this, numberOfDays)
        listView.adapter = adapter
    }
    override fun onPause() {
        super.onPause()
        val userAvailability = adapter.getUserAvailability()
        // Share or store this array as needed.
    }
}

class RoutinePlannerViewModel @Inject constructor() : ViewModel() {
    // Add LiveData and business logic here if needed
} 