package com.sagetripp.simplelauncher.bean

import android.graphics.drawable.Drawable

data class App(val name: String, val icon: Drawable, val isSystem: Boolean = false)
