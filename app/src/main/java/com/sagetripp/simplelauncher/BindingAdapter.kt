package com.sagetripp.simplelauncher

import android.app.Activity
import android.databinding.BindingAdapter
import android.graphics.drawable.Drawable
import android.support.annotation.LayoutRes
import android.support.v7.widget.DividerItemDecoration
import android.support.v7.widget.GridLayoutManager
import android.support.v7.widget.RecyclerView
import android.view.View
import android.widget.ImageView
import android.widget.Toast
import com.bumptech.glide.Glide
import com.sagetripp.simplelauncher.base.BaseAdapter

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

@BindingAdapter("layoutHeight")
fun View.setHeight(dp: Int) {
    layoutParams.height = dp
    layoutParams = layoutParams
}

@BindingAdapter("marginHorizontal")
fun View.marginHorizontal(dp: Int) {

}


