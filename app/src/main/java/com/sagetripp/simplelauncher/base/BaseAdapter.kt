package com.sagetripp.simplelauncher.base

import android.databinding.DataBindingUtil
import android.databinding.ViewDataBinding
import android.support.annotation.LayoutRes
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.ViewGroup
import com.sagetripp.simplelauncher.BR

abstract class BaseAdapter<D> : RecyclerView.Adapter<BaseAdapter.ViewHolder<D>>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder<D> {
        val layoutInflater =
                LayoutInflater.from(parent.context)
        val binding: ViewDataBinding = DataBindingUtil.inflate(
                layoutInflater, viewType, parent, false)
        return ViewHolder(binding)
    }

    override fun getItemCount(): Int = getData().size

    override fun onBindViewHolder(holder: ViewHolder<D>, position: Int) {
        holder.bind(getData()[position])
    }

    override fun getItemViewType(position: Int): Int {
        if (position == 0)
            return super.getItemViewType(position)
        return getLayoutIdByPosition(position)
    }

    open fun getData(): List<D> {
        return emptyList()
    }

    @LayoutRes
    open fun getLayoutIdByPosition(position: Int): Int = 0

    class ViewHolder<in D>(val binding: ViewDataBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(data: D) {
            binding.setVariable(BR.data, data)
            binding.executePendingBindings()
        }
    }
}