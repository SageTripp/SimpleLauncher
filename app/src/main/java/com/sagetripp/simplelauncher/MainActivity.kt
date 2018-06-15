package com.sagetripp.simplelauncher

import android.content.Intent
import android.databinding.DataBindingUtil
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import com.sagetripp.simplelauncher.bean.Soft
import com.sagetripp.simplelauncher.databinding.ActivityMainBinding
import com.sagetripp.simplelauncher.extend.boxStore
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

    private fun getAppList(): List<Soft> {
        val launchers = packageManager.queryIntentActivities(Intent(Intent.ACTION_MAIN, null).addCategory(Intent.CATEGORY_LAUNCHER), 0)

        return launchers.map {
            Soft(it.loadLabel(packageManager).toString(), it.loadIcon(packageManager), it).also { soft ->
                boxStore.boxFor(Soft::class.java).apply {
                    put(soft)
                }
            }
        }
                .sortedBy { it.name }
    }
}
