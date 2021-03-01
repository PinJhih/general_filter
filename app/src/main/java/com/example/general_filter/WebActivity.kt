package com.example.general_filter

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import kotlinx.android.synthetic.main.activity_web.*
import java.util.ArrayList

class WebActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_web)

        var index = 0
        var codes = ArrayList<String>()
        intent.extras?.let {
            codes = it.getStringArrayList("codes")!!
            wv_result.loadUrl(getUrl(codes[0]))
        }
        btn_last.setOnClickListener {
            index--
            if (index == 0)
                index = 0
            wv_result.loadUrl(getUrl(codes[index]))
        }
        btn_next.setOnClickListener {
            index++
            if (index == codes.size)
                index--
            wv_result.loadUrl(getUrl(codes[index]))
        }
    }

    private fun getUrl(code:String):String = "https://www.cac.edu.tw/apply110/system/110_aColQry4qy_forapply_o5wp6ju/html/110_$code.htm?v=1.0"
}
