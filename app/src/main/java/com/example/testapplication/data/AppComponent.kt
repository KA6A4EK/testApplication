package com.example.testapplication.data

import android.content.Context
import com.example.testapplication.MainActivity
import dagger.Component
import dagger.Module
import dagger.Provides



@Component(modules = [DaoModule::class, ProvideViewModel::class, RepositoryModule::class,ContextModule::class])
interface AppComponent {
    fun provideContext() : Context
    fun provideViewModel(): ProductViewModel

    fun provideDao(): dao

    fun provideRepository(): repository

    fun inject(mainActivity: MainActivity)
}

@Module
class ContextModule(private val context: Context) {
    @Provides
    fun provideContext(): Context {
        return context
    }

}