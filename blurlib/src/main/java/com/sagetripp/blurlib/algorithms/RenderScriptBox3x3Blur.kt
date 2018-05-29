package com.sagetripp.blurlib.algorithms

import android.graphics.Bitmap
import android.support.v8.renderscript.Allocation
import android.support.v8.renderscript.Element
import android.support.v8.renderscript.RenderScript
import android.support.v8.renderscript.ScriptIntrinsicConvolve3x3
import at.favre.app.blurbenchmark.blur.IBlur
import com.sagetripp.blurlib.BlurKernels


class RenderScriptBox3x3Blur(private val rs: RenderScript) : IBlur {

    override fun blur(radius: Int, original: Bitmap): Bitmap {
        var input = Allocation.createFromBitmap(rs, original)
        val output = Allocation.createTyped(rs, input.type)
        val script = ScriptIntrinsicConvolve3x3.create(rs, Element.U8_4(rs))
        script.setCoefficients(BlurKernels.BOX_3x3)
        for (i in 0 until radius) {
            script.setInput(input)
            script.forEach(output)
            input = output
        }
        output.copyTo(original)
        return original
    }
}