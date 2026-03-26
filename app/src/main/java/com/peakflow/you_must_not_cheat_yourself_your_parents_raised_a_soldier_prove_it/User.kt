package com.peakflow.you_must_not_cheat_yourself_your_parents_raised_a_soldier_prove_it

data class User(
    val name: String? = null,
    val age: Int? = null,
    val email: String? = null,
    val firebaseUid: String? = null,
    val deadline: String? = null,
    val final_goal: String? = null,
    val monthly_goals: List<String>? = null,
    val weekly_goals: List<String>? = null,
    val daily_routine: List<List<Boolean>>? = null
)
