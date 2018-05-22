package com.sagetripp.simplelauncher.extend

import android.app.Activity
import android.app.Fragment
import android.content.Context
import android.graphics.Point
import android.widget.Toast

/*
toast
 */

fun Context.toast(msg: String) {
    Toast.makeText(this, msg, Toast.LENGTH_LONG).show()
}

fun Activity.toast(msg: String) {
    (this as Context).toast(msg)
}

fun Fragment.toast(msg: String) {
    activity.toast(msg)
}

fun Activity.getScreenWidth(screenSize: (width: Int, height: Int) -> Unit) {
    val point = Point()
    windowManager.defaultDisplay.getSize(point)
    return screenSize(point.x, point.y)
}