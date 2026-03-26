package com.peakflow.you_must_not_cheat_yourself_your_parents_raised_a_soldier_prove_it

import java.io.Serializable

data class UserData(
    var name: String? = null,
    var deadline: String? = null,
    var final_goal: String? = null,
    var monthly_goals: Array<String>? = null,
    var weekly_goals: Array<String>? = null,
    var daily_routine: Array<BooleanArray>? = null,
    var age: Int = 0,
    var no_of_months: Int = 0,
    private var id: String? = null,
    private var pass: String? = null
) : Serializable 