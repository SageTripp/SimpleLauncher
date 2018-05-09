package com.sagetripp.simplelauncher

import android.databinding.BindingAdapter
import android.support.annotation.LayoutRes
import android.support.v7.widget.GridLayoutManager
import android.support.v7.widget.RecyclerView
import com.sagetripp.simplelauncher.base.BaseAdapter

@BindingAdapter(value = ["datas", "itemLayout"], requireAll = true)
fun <D> setItem(recyclerView: RecyclerView, datas: Iterable<D>, @LayoutRes itemLayout: Int) {
    val adapter = object : BaseAdapter<D>() {
        override fun getData(): List<D> = datas.toList()
        override fun getLayoutIdByPosition(position: Int) = itemLayout
    }
    recyclerView.adapter = adapter
    recyclerView.layoutManager = GridLayoutManager(recyclerView.context, 3)
}
