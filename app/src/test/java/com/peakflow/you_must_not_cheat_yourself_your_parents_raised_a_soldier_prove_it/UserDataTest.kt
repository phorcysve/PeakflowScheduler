package com.peakflow.you_must_not_cheat_yourself_your_parents_raised_a_soldier_prove_it

import org.junit.Assert.*
import org.junit.Test

class UserDataTest {
    @Test
    fun testUserDataCreation() {
        val userData = UserData(
            name = "Test User",
            age = 25,
            deadline = "2024-12-31",
            final_goal = "Complete project"
        )
        assertEquals("Test User", userData.name)
        assertEquals(25, userData.age)
        assertEquals("2024-12-31", userData.deadline)
        assertEquals("Complete project", userData.final_goal)
    }

    @Test
    fun testUserDataDefaultValues() {
        val userData = UserData()
        assertNull(userData.name)
        assertEquals(0, userData.age)
        assertEquals(0, userData.no_of_months)
    }
} 