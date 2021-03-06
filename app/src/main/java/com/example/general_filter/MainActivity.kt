package com.example.general_filter

import android.app.Activity
import android.content.Intent
import android.content.SharedPreferences
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.android.gms.ads.AdRequest
import com.google.android.gms.ads.MobileAds
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {
    private lateinit var userInfo: SharedPreferences
    private val departments = ArrayList<Department>()
    private var result = ArrayList<Department>()
    private lateinit var adapter: DepartmentsAdapter
    private var status = ""

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        MobileAds.initialize(this) {}
        val adRequest = AdRequest.Builder().build()
        ad_main.loadAd(adRequest)

        for (i in 0 until 2181)
            status += 'f'
        userInfo = getSharedPreferences("userInfo", Activity.MODE_PRIVATE)
        status = userInfo.getString("status", status)!!
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
            department.pinned = status[i] == 't'
            department.index = i
            departments.add(department)
        }
        result.addAll(departments)
        val linearLayoutManager = LinearLayoutManager(this)
        linearLayoutManager.orientation = RecyclerView.VERTICAL
        rv_department.layoutManager = linearLayoutManager
        adapter = DepartmentsAdapter(this, 1, result)
        rv_department.adapter = adapter
        adapter.notifyDataSetChanged()

        btn_show_pinned.setOnClickListener {
            val i = Intent(this, ViewPinnedActivity::class.java)
            startActivityForResult(i, 0)
        }

        ed_school_name.addTextChangedListener(object : TextWatcher {
            override fun afterTextChanged(s: Editable?) {
            }

            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {
            }

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                updateList()
            }
        })

        ed_department_name.addTextChangedListener(object : TextWatcher {
            override fun afterTextChanged(s: Editable?) {
            }

            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {
            }

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                updateList()
            }
        })
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        status = userInfo.getString("status", status)!!
        for (i in status.indices) {
            departments[i].pinned = status[i] == 't'
        }
        adapter.notifyDataSetChanged()
    }

    private fun updateList() {
        result.clear()
        val schoolName = "${ed_school_name.editableText}"
        val departmentName = "${ed_department_name.editableText}"
        for (i in departments)
            if (schoolName in i.schoolName && departmentName in i.departmentName)
                result.add(i)
        adapter.notifyDataSetChanged()
    }

    fun setPinnedItem(position: Int) {
        departments[position].pinned = !departments[position].pinned
        adapter.notifyDataSetChanged()
        val s = status
        status = ""
        for (i in s.indices) {
            status += if (i == position)
                if (departments[position].pinned) 't' else 'f'
            else
                s[i]
        }
        val editor = userInfo.edit()
        editor.putString("status", status)
        editor.apply()
    }

    fun viewGeneral(id: String) {
        val codes = arrayListOf(id)
        val i = Intent(this, WebActivity::class.java)
        i.putExtra("codes", codes)
        startActivity(i)
    }
}
