package at.favre.app.blurbenchmark.blur

import android.graphics.Bitmap

/**
 * 模糊算法接口
 */
interface IBlur {

    /**
     * Takes a bitmap and blurs it with the given blur radius. This will NOT copy the original
     * but reuses it, so if this instance will be used somewhere else, manual copying is needed.
     *
     * @param radius   blurRadius, keep in mind some algorithms don't take all values (e.g. ScriptIntrinsicBlur will only take 1-25)
     * @param original bitmap
     * @return blurred original bitmap
     */
    fun blur(radius: Int, original: Bitmap): Bitmap

    companion object {
        //threshold for live blurring
        val MS_THRESHOLD_FOR_SMOOTH = 16
    }
}