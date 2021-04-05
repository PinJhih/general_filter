package com.example.general_filter

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import kotlinx.android.synthetic.main.activity_web.*
import java.util.ArrayList

class WebActivity : AppCompatActivity() {

    private var codes = ArrayList<String>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_web)
        wv_result.settings.useWideViewPort = true
        var index = 0
        intent.extras?.let {
            codes = it.getStringArrayList("codes")!!
            if (codes.count() != 0)
                wv_result.loadUrl(getUrl(codes[0]))
        }
        setWebView(0)
        btn_last.setOnClickListener {
            index--
            if (index == 0)
                index = 0
            setWebView(index)
        }
        btn_next.setOnClickListener {
            index++
            if (index == codes.size)
                index--
            setWebView(index)
        }
    }

    private fun getUrl(code: String): String =
        "https://www.cac.edu.tw/apply110/system/110_aColQry4qy_forapply_o5wp6ju/html/110_$code.htm?v=1.0"

    private fun setWebView(index: Int) {
        if (codes.count() != 0) {
            wv_result.loadUrl(getUrl(codes[index]))
            title = codes[index]
        }
    }
}
