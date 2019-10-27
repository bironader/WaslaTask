package com.example.waslatask.injection.modules

import android.app.Application
import com.example.waslatask.BuildConfig
import com.facebook.stetho.okhttp3.StethoInterceptor
import com.google.gson.FieldNamingPolicy
import com.google.gson.Gson
import com.google.gson.GsonBuilder
import com.readystatesoftware.chuck.ChuckInterceptor
import dagger.Module
import dagger.Provides
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import timber.log.Timber
import javax.inject.Singleton

@Module
abstract class NetworkModule {

    @Module
    companion object {
        private val baseUrl: String
            get() = BuildConfig.GoogleSuggestUrl

        @Provides
        @Singleton
        fun provideRetrofit(okHttpClient: OkHttpClient): Retrofit {
            return Retrofit.Builder()
                .baseUrl(baseUrl)
                .client(okHttpClient)
                .addConverterFactory(GsonConverterFactory.create(provideGson()))
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .build();
        }

        @Provides
        @Singleton
        fun provideGson(): Gson {
            return GsonBuilder()
                .setDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS'Z'")
                .setFieldNamingPolicy(FieldNamingPolicy.LOWER_CASE_WITH_UNDERSCORES)
                .create();
        }

        @Provides
        @Singleton
        internal fun provideHttpLoggingInterceptor(): HttpLoggingInterceptor {
            val loggingInterceptor = HttpLoggingInterceptor { message -> Timber.d(message) }
            loggingInterceptor.level = HttpLoggingInterceptor.Level.BODY
            return loggingInterceptor
        }

        @Provides
        @Singleton
        fun provideStethoInterceptor(): StethoInterceptor {
            return StethoInterceptor()
        }

        @Provides
        @Singleton
        fun provideChuckInterceptor(application: Application): ChuckInterceptor {
            return ChuckInterceptor(application.applicationContext)
        }

        @Provides
        @Singleton
        fun provideHeaderInterceptor(): Interceptor {
            val chain = Interceptor { chain ->
                var request = chain.request()

                request = request.newBuilder()
                    .addHeader("Content-Type", "application/json")
                    .addHeader("X-Requested-With", " XMLHttpRequest")
                    .addHeader("Accept-Language", "en")
                    .build()
                chain.proceed(request)
            }
            return chain;
        }


    }
}