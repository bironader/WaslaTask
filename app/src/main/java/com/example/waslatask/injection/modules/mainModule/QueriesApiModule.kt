package com.example.waslatask.injection.modules.mainModule

import com.example.waslatask.data.remote.QueriesApi
import dagger.Module
import dagger.Provides
import retrofit2.Retrofit

@Module
 class QueriesApiModule {


    @Provides
    fun provideQueriesApi(retrofit: Retrofit): QueriesApi {
        return retrofit.create(QueriesApi::class.java)

    }
}