package com.sagetripp.test

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import io.reactivex.Flowable
import kotlinx.android.synthetic.main.layout_widget.*
import java.util.concurrent.TimeUnit

class WidgetActivity : AppCompatActivity() {
    var count = 0
    val countFlowable = Flowable.interval(1, TimeUnit.SECONDS)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.layout_widget)
        tv_count.text = "计数: $count"
        countFlowable.subscribe { tv_count.text = "计数: ${count++}" }
    }
}
