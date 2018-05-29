package com.sagetripp.simplelauncher

import android.content.pm.ApplicationInfo
import android.databinding.DataBindingUtil
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import com.sagetripp.simplelauncher.bean.App
import com.sagetripp.simplelauncher.databinding.ActivityMainBinding
import com.sagetripp.simplelauncher.extend.getScreenWidth

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val mainBinding = DataBindingUtil.setContentView<ActivityMainBinding>(this, R.layout.activity_main)
        mainBinding.data = getAppList()
        mainBinding.dockerLabel = "主页"
        getScreenWidth { width, _ ->
            mainBinding.dockerHeight = width / 4 * 2
        }
    }

    private fun getAppList(): List<App> {
        val packages = packageManager.getInstalledPackages(0)
        println("app数量：${packages.size}")

        return packages
                .filter {
                    with(it.applicationInfo) {
                        flags and ApplicationInfo.FLAG_SYSTEM == 0
                    }
                }
                .map {
                    with(it.applicationInfo) {
                        App(loadLabel(packageManager).toString(),
                                loadIcon(packageManager)
                                        ?: resources.getDrawable(R.mipmap.ic_launcher),
                                flags and ApplicationInfo.FLAG_SYSTEM == 0)
                    }
                }
                .sortedBy { it.name }
                .sortedBy { !it.isSystem }

    }
}
