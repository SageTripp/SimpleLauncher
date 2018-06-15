package com.sagetripp.simplelauncher.extend

import android.os.Environment
import com.sagetripp.simplelauncher.BuildConfig
import com.sagetripp.simplelauncher.base.App
import com.sagetripp.simplelauncher.bean.MyObjectBox
import io.objectbox.BoxStore
import java.io.File
import io.objectbox.android.AndroidObjectBrowser


val boxStore: BoxStore by lazy {
    MyObjectBox.builder()
            .directory(File("${Environment.getExternalStorageDirectory()}${File.separator}SL"))
            .androidContext(App.instance)
            .build().apply {
                if (BuildConfig.DEBUG) {
                    AndroidObjectBrowser(this).start(App.instance)
                }
            }
}