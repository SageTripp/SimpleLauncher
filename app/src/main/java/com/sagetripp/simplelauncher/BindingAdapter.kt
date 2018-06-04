package com.sagetripp.simplelauncher

import android.databinding.BindingAdapter
import android.graphics.Bitmap
import android.graphics.Canvas
import android.graphics.drawable.BitmapDrawable
import android.graphics.drawable.Drawable
import android.support.annotation.IdRes
import android.support.annotation.LayoutRes
import android.support.v7.widget.DividerItemDecoration
import android.support.v7.widget.GridLayoutManager
import android.support.v7.widget.RecyclerView
import android.view.View
import android.widget.ImageView
import com.bumptech.glide.Glide
import com.sagetripp.blurlib.blur
import com.sagetripp.simplelauncher.base.BaseAdapter
import com.sagetripp.simplelauncher.extend.crop
import io.reactivex.Flowable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import org.reactivestreams.Subscriber
import java.util.concurrent.TimeUnit


@BindingAdapter("datas", "itemLayout")
fun RecyclerView.setSingleItem(datas: Iterable<Any>, @LayoutRes itemLayout: Int) {
    val adapter = object : BaseAdapter() {
        override fun getData(): List<Any> = datas.toList()
        override fun getLayoutIdByPosition(position: Int) = itemLayout
    }
    this.adapter = adapter
    layoutManager = GridLayoutManager(context, 4)
}

val RecyclerView.defaultDivider
    get() = DividerItemDecoration(context, DividerItemDecoration.HORIZONTAL or DividerItemDecoration.VERTICAL)


@BindingAdapter("divider")
fun RecyclerView.divider(divider: RecyclerView.ItemDecoration = defaultDivider) {
    addItemDecoration(divider)
}

@BindingAdapter("drawable")
fun ImageView.setDrawable(drawable: Drawable) {
    Glide.with(context).load(drawable).into(this)
}

@BindingAdapter("radius", "reference", "downSampling")
fun View.blur(radius: Int, @IdRes reference: Int, downSampling: Int) {
    println(this.javaClass.name)
    this.addOnLayoutChangeListener { v, _, _, _, _, _, _, _, _ ->
        Flowable.interval(80, 500, TimeUnit.MILLISECONDS)
                .subscribeOn(Schedulers.io())
                .onErrorResumeNext { subscriber: Subscriber<in Long> ->
                    subscriber.onNext(0)
                    println("背景失败==> ${this.javaClass.name}")
                }
                .map {
                    println("生成背景==> ${this.javaClass.name}")
                    val view = this.rootView.findViewById<View>(reference)
                    val scale = 1f / downSampling
                    val viewWidth = view.width
                    val viewHeight = view.height
                    val bmpWidth = Math.round(viewWidth * scale)
                    val bmpHeight = Math.round(viewHeight * scale)

                    var dest = Bitmap.createBitmap(1, 1, Bitmap.Config.ARGB_8888)
                    if (dest.width != bmpWidth || dest.height != bmpHeight) {
                        dest = Bitmap.createBitmap(bmpWidth, bmpHeight, Bitmap.Config.ARGB_8888)
                    }

                    val c = Canvas(dest)
                    if (downSampling > 1) {
                        c.scale(scale, scale)
                    }
                    view.draw(c)
                    dest = dest.crop(this, downSampling)
                    dest.blur(context, radius)
                }
                .take(1)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe({ dest ->
                    println("设置背景==> ${this.javaClass.name}")
                    v.background = BitmapDrawable(context.resources, dest)
                }, Throwable::printStackTrace)
    }

}

@BindingAdapter("layoutHeight")
fun View.setHeight(dp: Int) {
    layoutParams.height = dp
    layoutParams = layoutParams
    invalidate()
}

@BindingAdapter("marginHorizontal")
fun View.marginHorizontal(dp: Int) {

}


