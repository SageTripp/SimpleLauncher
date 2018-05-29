package com.sagetripp.blurlib.algorithms

import android.graphics.Bitmap
import android.support.v8.renderscript.Allocation
import android.support.v8.renderscript.Element
import android.support.v8.renderscript.RenderScript
import android.support.v8.renderscript.ScriptIntrinsicBlur

import at.favre.app.blurbenchmark.blur.IBlur

class RenderScriptGaussianBlur(private val rs: RenderScript) : IBlur {

    override fun blur(radius: Int, original: Bitmap): Bitmap {
        val input = Allocation.createFromBitmap(rs, original)
        val output = Allocation.createTyped(rs, input.type)
        val script = ScriptIntrinsicBlur.create(rs, Element.U8_4(rs))
        script.setRadius(radius.toFloat())
        script.setInput(input)
        script.forEach(output)
        output.copyTo(original)
        return original
    }
}