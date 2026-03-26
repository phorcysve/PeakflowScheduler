package com.peakflow.you_must_not_cheat_yourself_your_parents_raised_a_soldier_prove_it

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.DatabaseReference
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class DashboardViewModel @Inject constructor(
    private val auth: FirebaseAuth,
    private val database: DatabaseReference
) : ViewModel() {
    private val _userData = MutableLiveData<User?>()
    val userData: LiveData<User?> = _userData

    init {
        fetchUserData()
    }

    private fun fetchUserData() {
        val currentUser = auth.currentUser
        if (currentUser != null) {
            database.child(currentUser.uid).get().addOnSuccessListener { snapshot ->
                val user = snapshot.getValue(User::class.java)
                _userData.postValue(user)
            }
        }
    }
} 