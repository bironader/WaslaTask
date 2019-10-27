package com.example.waslatask.injection.modules

import com.example.waslatask.features.main.MainActivity
import com.example.waslatask.injection.modules.mainModule.MainViewModelModule
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
abstract class ActivityBuilderModule {

    @ContributesAndroidInjector(modules = arrayOf(MainViewModelModule::class))
    abstract fun mainActivity(): MainActivity
}