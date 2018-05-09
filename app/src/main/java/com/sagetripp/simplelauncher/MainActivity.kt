package com.sagetripp.simplelauncher

import android.content.pm.ApplicationInfo
import android.databinding.DataBindingUtil
import android.graphics.drawable.Drawable
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import com.sagetripp.simplelauncher.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val mainBinding = DataBindingUtil.setContentView<ActivityMainBinding>(this, R.layout.activity_main)
        mainBinding.data = getAppList()
    }

    fun getAppList(): List<App> {
        val packages = packageManager.getInstalledPackages(0)
        println("app数量：${packages.size}")
        return packages
                .map { packageInfo ->
                    with(packageInfo.applicationInfo) {
                        App(loadLabel(packageManager).toString(),
                                loadIcon(packageManager)
                                        ?: resources.getDrawable(R.mipmap.ic_launcher),
                                flags and ApplicationInfo.FLAG_SYSTEM == 0)
                    }
                }
                .sortedBy { it.name }
                .sortedBy { !it.isSystem }

    }

    data class App(val name: String, val icon: Drawable, val isSystem: Boolean = false)
}
