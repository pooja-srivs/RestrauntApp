package com.example.restrauntapp.di

import com.example.restrauntapp.RestaurauntApp
import dagger.BindsInstance
import dagger.Component
import dagger.android.AndroidInjector
import dagger.android.support.AndroidSupportInjectionModule
import javax.inject.Singleton

@Singleton
@Component(
    modules = [
        AndroidSupportInjectionModule::class
    ]
)
interface AppComponent : AndroidInjector<RestaurauntApp> {

    @Component.Builder
    interface Builder{

        @BindsInstance
        fun application(app : RestaurauntApp) : Builder

        fun build() : AppComponent
    }
}