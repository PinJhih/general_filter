package com.example.general_filter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.item_department.view.*

class DepartmentsAdapter(
    private val context: Context,
    private val departments: ArrayList<Department>
) : RecyclerView.Adapter<DepartmentsAdapter.ViewHolder>() {
    class ViewHolder(v: View) : RecyclerView.ViewHolder(v)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val v = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_department, parent, false)
        return ViewHolder(v)
    }

    override fun getItemCount() = departments.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val itemView = holder.itemView
        val name = departments[position].schoolName + " " + departments[position].departmentName
        itemView.tv_name.text = name
        itemView.tv_exam_date.text = departments[position].examDate
    }
}
