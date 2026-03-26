package com.peakflow.you_must_not_cheat_yourself_your_parents_raised_a_soldier_prove_it

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.DatabaseReference
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject
import android.widget.EditText
import android.widget.ProgressBar
import android.widget.Button
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch

@AndroidEntryPoint
class SignUpActivity : AppCompatActivity() {
    @Inject lateinit var mAuth: FirebaseAuth
    @Inject lateinit var databaseReference: DatabaseReference
    private val viewModel: SignUpViewModel by viewModels()

    private lateinit var nameEditText: EditText
    private lateinit var ageEditText: EditText
    private lateinit var uidEditText: EditText
    private lateinit var emailEditText: EditText
    private lateinit var passwordEditText: EditText
    private lateinit var signUpButton: Button
    private lateinit var progressBar: ProgressBar
    private var userData: UserData? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_sign_up)

        nameEditText = findViewById(R.id.editTextName)
        ageEditText = findViewById(R.id.editTextAge)
        uidEditText = findViewById(R.id.editTextUID)
        emailEditText = findViewById(R.id.editTextEmail)
        passwordEditText = findViewById(R.id.editTextPassword)
        signUpButton = findViewById(R.id.buttonSignUp)
        progressBar = findViewById(R.id.progressBar)

        userData = intent.getSerializableExtra("userData") as? UserData
        if (userData == null) {
            Toast.makeText(this, "Error loading user data!", Toast.LENGTH_SHORT).show()
            finish()
        }

        signUpButton.setOnClickListener {
            checkUIDAndRegister()
        }
    }

    private fun checkUIDAndRegister() {
        val uid = uidEditText.text.toString().trim()
        if (uid.isEmpty()) {
            uidEditText.error = "UID is required!"
            uidEditText.requestFocus()
            return
        }
        databaseReference.child(uid).get().addOnCompleteListener { task ->
            if (task.isSuccessful && task.result.exists()) {
                uidEditText.error = "UID already exists!"
                Toast.makeText(this, "Choose a different UID", Toast.LENGTH_SHORT).show()
            } else {
                registerUser(uid)
            }
        }
    }

    private fun registerUser(uid: String) {
        val name = nameEditText.text.toString().trim()
        val ageStr = ageEditText.text.toString().trim()
        val email = emailEditText.text.toString().trim()
        val password = passwordEditText.text.toString().trim()

        if (name.isEmpty()) {
            nameEditText.error = "Name is required!"
            nameEditText.requestFocus()
            return
        }
        val age = ageStr.toIntOrNull()
        if (age == null || age <= 0) {
            ageEditText.error = "Enter a valid age!"
            return
        }
        if (email.isEmpty()) {
            emailEditText.error = "Email is required!"
            emailEditText.requestFocus()
            return
        }
        if (password.isEmpty()) {
            passwordEditText.error = "Password is required!"
            passwordEditText.requestFocus()
            return
        }
        if (password.length < 6) {
            passwordEditText.error = "Password must be at least 6 characters!"
            return
        }
        progressBar.visibility = android.view.View.VISIBLE
        mAuth.createUserWithEmailAndPassword(email, password)
            .addOnCompleteListener { task ->
                progressBar.visibility = android.view.View.GONE
                if (task.isSuccessful) {
                    val user = mAuth.currentUser
                    saveUserToDatabase(uid, name, age, email, user?.uid ?: "")
                    Toast.makeText(this, "Sign-Up Successful!", Toast.LENGTH_SHORT).show()
                    startActivity(Intent(this, DashboardActivity::class.java))
                    finish()
                } else {
                    Toast.makeText(this, "Sign-Up Failed: ${task.exception?.message}", Toast.LENGTH_LONG).show()
                }
            }
    }

    private fun saveUserToDatabase(uid: String, name: String, age: Int, email: String, firebaseUid: String) {
        val monthlyGoals = userData?.monthly_goals?.toList() ?: emptyList()
        val weeklyGoals = userData?.weekly_goals?.toList() ?: emptyList()
        val dailyRoutineList = userData?.daily_routine?.map { it.toList() } ?: emptyList()
        // Save UID securely
        SecureStorageUtil.getEncryptedPrefs(this).edit().putString("uid", uid).apply()
        val newUser = User(name, age, email, firebaseUid, userData?.deadline, userData?.final_goal, monthlyGoals, weeklyGoals, dailyRoutineList)
        databaseReference.child(uid).setValue(newUser)
    }
}

class SignUpViewModel @Inject constructor() : ViewModel() {
    // Add LiveData and business logic here for sign up if needed
} 