package com.example.general_filter

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.google.android.gms.ads.AdRequest
import com.google.android.gms.ads.MobileAds
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.activity_web.*
import java.util.ArrayList

class WebActivity : AppCompatActivity() {

    private var codes = ArrayList<String>()
    private var index = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_web)
        MobileAds.initialize(this) {}
        val adRequest = AdRequest.Builder().build()
        ad_web.loadAd(adRequest)

        wv_result.settings.useWideViewPort = true
        intent.extras?.let {
            codes = it.getStringArrayList("codes")!!
            if (codes.size != 0)
                wv_result.loadUrl(getUrl(codes[0]))
        }
        setWebView(0)
        btn_last.setOnClickListener {
            index--
            if (index < 0)
                index = 0
            setWebView(index)
        }
        btn_next.setOnClickListener {
            index++
            if (index >= codes.size)
                index = codes.size - 1
            setWebView(index)
        }
    }

    private fun getUrl(code: String): String =
        "https://www.cac.edu.tw/apply110/system/110_aColQry4qy_forapply_o5wp6ju/html/110_$code.htm?v=1.0"

    private fun setWebView(index: Int) {
        if (codes.size != 0) {
            wv_result.loadUrl(getUrl(codes[index]))
            title = codes[index]
        }
    }
}
