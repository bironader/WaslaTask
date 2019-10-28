package com.example.waslatask.injection.modules

import android.app.Application
import android.content.Context
import androidx.annotation.NonNull
import dagger.Module
import dagger.Provides
import javax.inject.Singleton


@Module
class ContextModule {

    @Singleton
    @Provides
    @NonNull
    fun provideContext(application: Application): Context {
        return application.applicationContext
    }


}