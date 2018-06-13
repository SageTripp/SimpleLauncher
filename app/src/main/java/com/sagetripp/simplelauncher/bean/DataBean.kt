package com.sagetripp.simplelauncher.bean

import android.content.ComponentName
import android.content.Intent
import android.content.pm.ResolveInfo
import android.graphics.drawable.Drawable
import android.view.View

data class App(val name: String, val icon: Drawable, val info: ResolveInfo) {
    fun onAppClick(view: View) {
        view.context.applicationContext
                .startActivity(Intent().apply {
                    component = ComponentName(info.activityInfo.packageName, info.activityInfo.name)
                })
    }
}
