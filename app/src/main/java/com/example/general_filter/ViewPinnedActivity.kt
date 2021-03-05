package com.example.general_filter

import android.app.Activity
import android.content.Intent
import android.content.SharedPreferences
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.activity_view_pinned.*

class ViewPinnedActivity : AppCompatActivity() {
    private val departments = ArrayList<Department>()
    private var status = ""
    private lateinit var adapter: DepartmentsAdapter
    private lateinit var userInfo: SharedPreferences

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_view_pinned)

        userInfo = getSharedPreferences("userInfo", Activity.MODE_PRIVATE)
        status = userInfo.getString("status", "")!!
        val ids = arrayOf(
            R.array.school_name, R.array.department_name,
            R.array.code, R.array.exam_date,
            R.array.exam_quota, R.array.enrollment_quota
        )
        val res = arrayListOf<Array<String>>()
        for (i in ids)
            res.add(resources.getStringArray(i))
        for (i in res[0].indices) {
            if (status[i] == 't') {
                val department = Department()
                department.schoolName = res[0][i]
                department.departmentName = res[1][i]
                department.code = res[2][i]
                department.examDate = res[3][i]
                department.examQuota = res[4][i]
                department.enrollmentQuota = res[5][i]
                department.pinned = true
                departments.add(department)
            }
        }

        val linearLayoutManager = LinearLayoutManager(this)
        linearLayoutManager.orientation = RecyclerView.VERTICAL
        rv_pinned.layoutManager = linearLayoutManager
        adapter = DepartmentsAdapter(this, 0, departments)
        rv_pinned.adapter = adapter
        adapter.notifyDataSetChanged()

        btn_show_web.setOnClickListener {
            val intent = Intent(this, WebActivity::class.java)
            val arr = ArrayList<String>()
            for (i in departments) {
                arr.add(i.code)
            }
            intent.putExtra("codes", arr)
            startActivity(intent)
        }
    }

    fun setPinnedItem(position: Int) {
        departments[position].pinned = false
        val s = status
        status = ""
        for (i in s.indices) {
            status += if (i == position)
                'f'
            else
                s[i]
        }
        val editor = userInfo.edit()
        editor.putString("status", status)
        editor.apply()
        departments.remove(departments[position])
        adapter.notifyDataSetChanged()
    }
}
