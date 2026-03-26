package com.peakflow.you_must_not_cheat_yourself_your_parents_raised_a_soldier_prove_it

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import android.widget.EditText
import android.widget.TextView

class ListViewAdapterBreakdownMonthly(
    private val context: Context,
    private val n: Int
) : BaseAdapter() {
    private val inflater: LayoutInflater = LayoutInflater.from(context)
    private val userInputArray: Array<String> = Array(n) { "" }

    override fun getCount(): Int = n

    override fun getItem(position: Int): Any? = null

    override fun getItemId(position: Int): Long = position.toLong()

    override fun getView(position: Int, convertView: View?, parent: ViewGroup): View {
        val view: View
        val holder: ViewHolder
        
        if (convertView == null) {
            view = inflater.inflate(R.layout.listview_adapter_breakdown, parent, false)
            holder = ViewHolder(
                tView = view.findViewById(R.id.month_keyword),
                edt = view.findViewById(R.id.gin)
            )
            view.tag = holder
        } else {
            view = convertView
            holder = view.tag as ViewHolder
        }

        val monthNumber = position + 1
        holder.tView?.text = "Month $monthNumber"
        holder.edt?.hint = "Enter Your Goal"

        // Restore input if available
        holder.edt?.setText(userInputArray[position])

        // Save input when user types
        holder.edt?.setOnFocusChangeListener { _, hasFocus ->
            if (!hasFocus) {
                userInputArray[position] = holder.edt?.text.toString()
            }
        }

        holder.edt?.setBackgroundResource(R.drawable.edittextbackground)

        return view
    }

    fun getUserInputData(): Array<String> = userInputArray

    private data class ViewHolder(
        val tView: TextView?,
        val edt: EditText?
    )
}
