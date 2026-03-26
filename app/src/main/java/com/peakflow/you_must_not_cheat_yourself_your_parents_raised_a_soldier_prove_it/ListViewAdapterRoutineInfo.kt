package com.peakflow.you_must_not_cheat_yourself_your_parents_raised_a_soldier_prove_it

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import android.widget.RadioButton
import android.widget.RadioGroup
import android.widget.TextView

class ListViewAdapterRoutineInfo(
    private val context: Context,
    private val n: Int // Number of days (usually 7)
) : BaseAdapter() {
    private val inflater: LayoutInflater = LayoutInflater.from(context)
    private val days = arrayOf("MONDAY", "TUESDAY", "WEDNESDAY", "THURSDAY", "FRIDAY", "SATURDAY", "SUNDAY")
    private val availabilityData: Array<BooleanArray> = Array(n) { BooleanArray(18) { false } }

    override fun getCount(): Int = n

    override fun getItem(position: Int): Any? = null

    override fun getItemId(position: Int): Long = position.toLong()

    override fun getView(position: Int, convertView: View?, parent: ViewGroup): View {
        val view = convertView ?: inflater.inflate(R.layout.listview_adapter_routine_info, parent, false)
        
        val dayLabel = view.findViewById<TextView>(R.id.day_of_week)
        dayLabel.text = days[position]

        for (i in 1..18) {
            val radioGroupId = context.resources.getIdentifier("radioGroup$i", "id", context.packageName)
            val radioGroup = view.findViewById<RadioGroup>(radioGroupId)
            
            val yesButtonId = context.resources.getIdentifier("radioButtonYes$i", "id", context.packageName)
            val noButtonId = context.resources.getIdentifier("radioButtonNo$i", "id", context.packageName)

            // Restore state
            if (availabilityData[position][i-1]) {
                radioGroup.check(yesButtonId)
            } else {
                radioGroup.check(noButtonId)
            }

            radioGroup.setOnCheckedChangeListener { _, checkedId ->
                availabilityData[position][i-1] = (checkedId == yesButtonId)
            }
        }

        return view
    }

    fun getUserAvailability(): Array<BooleanArray> = availabilityData
}
