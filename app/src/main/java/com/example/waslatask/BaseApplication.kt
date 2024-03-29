package com.example.waslatask

import android.content.Context
import androidx.multidex.MultiDex
import com.blankj.utilcode.util.Utils
import com.example.waslatask.injection.component.DaggerAppComponent
import dagger.android.AndroidInjector
import dagger.android.support.DaggerApplication


class BaseApplication : DaggerApplication() {


    override fun onCreate() {
        super.onCreate()
        Utils.init(this)
    }

    override fun attachBaseContext(base: Context) {
        super.attachBaseContext(base)
        MultiDex.install(this)
    }

    override fun applicationInjector(): AndroidInjector<out DaggerApplication> {

        return DaggerAppComponent.builder().application(this).build();
    }
}