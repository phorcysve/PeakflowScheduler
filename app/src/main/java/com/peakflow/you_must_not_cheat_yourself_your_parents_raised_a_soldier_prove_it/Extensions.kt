package com.peakflow.you_must_not_cheat_yourself_your_parents_raised_a_soldier_prove_it

import android.content.Context
import android.widget.EditText
import android.widget.Toast
import androidx.fragment.app.FragmentActivity
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase

// Context extensions
fun Context.showToast(message: String, duration: Int = Toast.LENGTH_SHORT) {
    Toast.makeText(this, message, duration).show()
}

fun Context.showLongToast(message: String) {
    showToast(message, Toast.LENGTH_LONG)
}

// EditText extensions
fun EditText.isEmptyOrBlank(): Boolean {
    return text.toString().trim().isEmpty()
}

fun EditText.getTextTrimmed(): String {
    return text.toString().trim()
}

fun EditText.setErrorIfEmpty(errorMessage: String): Boolean {
    return if (isEmptyOrBlank()) {
        error = errorMessage
        requestFocus()
        false
    } else {
        true
    }
}

// Firebase extensions
fun FragmentActivity.getFirebaseAuth(): FirebaseAuth {
    return FirebaseAuth.getInstance()
}

fun FragmentActivity.getFirebaseDatabase(): DatabaseReference {
    return FirebaseDatabase.getInstance().reference
}

// Validation extensions
fun String.isValidEmail(): Boolean {
    return android.util.Patterns.EMAIL_ADDRESS.matcher(this).matches()
}

fun String.isValidPassword(): Boolean {
    return length >= Constants.MIN_PASSWORD_LENGTH
}

fun String.isValidAge(): Boolean {
    return toIntOrNull()?.let { it in Constants.MIN_AGE..Constants.MAX_AGE } ?: false
}

// Array extensions
fun Array<String?>.filterNotNull(): Array<String> {
    return filterNotNull().toTypedArray()
}

fun Array<BooleanArray>.toList(): List<List<Boolean>> {
    return map { it.toList() }
} 