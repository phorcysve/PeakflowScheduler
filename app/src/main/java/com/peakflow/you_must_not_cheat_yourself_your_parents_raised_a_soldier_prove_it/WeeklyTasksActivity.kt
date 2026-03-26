package com.peakflow.you_must_not_cheat_yourself_your_parents_raised_a_soldier_prove_it

import android.os.Bundle
import android.util.Log
import android.widget.TextView
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import dagger.hilt.android.AndroidEntryPoint
import okhttp3.*
import org.json.JSONArray
import org.json.JSONObject
import java.io.IOException
import javax.inject.Inject
import androidx.lifecycle.ViewModel

@AndroidEntryPoint
class WeeklyTasksActivity : AppCompatActivity() {
    private val viewModel: WeeklyTasksViewModel by viewModels()
    private lateinit var responseTextView: TextView
    private lateinit var monday: TextView
    private lateinit var tuesday: TextView
    private lateinit var wednesday: TextView
    private lateinit var thursday: TextView
    private lateinit var friday: TextView
    private lateinit var saturday: TextView
    private lateinit var sunday: TextView
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_schedule)
        monday = findViewById(R.id.textViewMondaytask)
        tuesday = findViewById(R.id.textViewTuesdayTask)
        wednesday = findViewById(R.id.textViewWednesdayTask)
        thursday = findViewById(R.id.textViewThursdayTask)
        friday = findViewById(R.id.textViewFridayTask)
        saturday = findViewById(R.id.textViewSundayTask)
        sunday = findViewById(R.id.textViewSundayTask)
        val arrTViews = arrayOf(monday, tuesday, wednesday, thursday, friday, saturday, sunday)
    }
    private fun sendPrompt(prompt: String, callback: ResponseCallback) {
        val client = OkHttpClient()
        val json = "{ \"prompt\": { \"text\": \"$prompt\" } }"
        val body = RequestBody.create(MediaType.get("application/json; charset=utf-8"), json)
        val request = Request.Builder()
            .url("${Constants.GEMINI_API_BASE_URL}?key=${Constants.GEMINI_API_KEY}")
            .post(body)
            .build()
        client.newCall(request).enqueue(object : Callback {
            override fun onFailure(call: Call, e: IOException) {
                Log.e("API_ERROR", "Failed to fetch response", e)
                callback.onFailure(e)
            }
            override fun onResponse(call: Call, response: Response) {
                if (!response.isSuccessful) {
                    Log.e("API_ERROR", "Unexpected response: $response")
                    callback.onFailure(IOException("Unexpected response: $response"))
                    return
                }
                try {
                    val responseData = response.body()?.string()
                    val jsonResponse = JSONObject(responseData)
                    val candidates = jsonResponse.getJSONArray("candidates")
                    val outputText = candidates.getJSONObject(0).getString("output")
                    callback.onSuccess(outputText)
                } catch (e: Exception) {
                    Log.e("JSON_ERROR", "Failed to parse response", e)
                    callback.onFailure(e)
                }
            }
        })
    }
    interface ResponseCallback {
        fun onSuccess(response: String)
        fun onFailure(e: Exception)
    }
}

class WeeklyTasksViewModel @Inject constructor() : ViewModel() {
    // Add LiveData and business logic here if needed
} 