package com.example.waslatask.injection.component

import android.app.Application
import com.example.waslatask.BaseApplication
import com.example.waslatask.injection.modules.ActivityBuilderModule
import com.example.waslatask.injection.modules.ContextModule
import com.example.waslatask.injection.modules.NetworkModule
import dagger.BindsInstance
import dagger.Component
import dagger.android.AndroidInjectionModule
import dagger.android.AndroidInjector
import javax.inject.Singleton

@Singleton
@Component(
    modules = [AndroidInjectionModule::class, ActivityBuilderModule::class, ContextModule::class, NetworkModule::class]
)
interface AppComponent : AndroidInjector<BaseApplication> {

    @Component.Builder
    interface Builder {

        @BindsInstance
        fun application(application: Application): Builder

        fun build(): AppComponent

    }

}