package com.sagetripp.simplelauncher.bean

import android.content.ComponentName
import android.content.Intent
import android.content.pm.ResolveInfo
import android.graphics.drawable.Drawable
import android.view.View
import io.objectbox.annotation.Entity
import io.objectbox.annotation.Id

@Entity
data class Soft(val name: String,
                @Transient val icon: Drawable,
                @Transient val info: ResolveInfo,
                @Id var id: Long = 0) {
    fun onAppClick(view: View) {
        view.context.applicationContext
                .startActivity(Intent().apply {
                    component = ComponentName(info.activityInfo.packageName, info.activityInfo.name)
                })
    }

}
