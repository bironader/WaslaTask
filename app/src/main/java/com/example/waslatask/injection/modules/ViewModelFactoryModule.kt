package com.example.waslatask.injection.modules



import androidx.lifecycle.ViewModelProvider
import com.example.waslatask.features.ViewModelProviderFactory
import dagger.Binds
import dagger.Module

@Module
abstract class ViewModelFactoryModule {

    @Binds
    abstract fun bindViewModelFactory(viewModelFactory: ViewModelProviderFactory): ViewModelProvider.Factory

}