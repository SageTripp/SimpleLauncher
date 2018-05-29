package com.sagetripp.blurlib

import android.content.Context
import android.graphics.Bitmap
import android.support.v8.renderscript.RenderScript
import at.favre.app.blurbenchmark.blur.IBlur
import com.sagetripp.blurlib.algorithms.RenderScriptGaussianBlur

fun Bitmap.blur(ctx: Context,
                radius: Int = 2,
                algorithms: IBlur = RenderScriptGaussianBlur(RenderScript.create(ctx))) = algorithms.blur(radius, this)