package com.example.waslatask.injection.modules.mainModule

import androidx.lifecycle.ViewModel
import com.example.waslatask.features.main.MainViewModel
import com.example.waslatask.injection.ViewModelKey
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap

@Module
abstract class MainViewModelModule {

    @Binds
    @IntoMap
    @ViewModelKey(MainViewModel::class)
    abstract fun bindMainViewModel(mainViewModel: MainViewModel): ViewModel

}