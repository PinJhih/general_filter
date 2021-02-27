package com.example.general_filter

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    private val departments = ArrayList<Department>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val ids = arrayOf(
            R.array.school_name, R.array.department_name,
            R.array.code, R.array.exam_date,
            R.array.exam_quota, R.array.enrollment_quota
        )
        val res = arrayListOf<Array<String>>()
        for (i in ids)
            res.add(resources.getStringArray(i))
        for (i in res[0].indices) {
            val department = Department()
            department.schoolName = res[0][i]
            department.departmentName = res[1][i]
            department.code = res[2][i]
            department.examDate = res[3][i]
            department.examQuota = res[4][i]
            department.enrollmentQuota = res[5][i]
            departments.add(department)
        }
    }
}