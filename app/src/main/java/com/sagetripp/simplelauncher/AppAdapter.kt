package com.sagetripp.simplelauncher

import android.graphics.Color
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import kotlinx.android.synthetic.main.adapter_app.view.*

class AppAdapter(val apps: List<MainActivity.App> = ArrayList()) : RecyclerView.Adapter<AppAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) = ViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.adapter_app, parent, false))

    override fun getItemCount() = apps.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        with(apps[position]) {
            holder.itemView.tv_app_name.setTextColor(if (isSystem) Color.RED else Color.BLUE)
            holder.itemView.iv_app_icon.setImageDrawable(icon)
            holder.itemView.tv_app_name.text = name
        }

    }

    inner class ViewHolder(itemView: View?) : RecyclerView.ViewHolder(itemView)
}