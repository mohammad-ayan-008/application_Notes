package com.example.quicknotes.Modules

import android.app.Application
import android.content.Context
import androidx.lifecycle.ViewModelProvider
import com.example.quicknotes.viewmodles.VModle
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.FragmentComponent
import dagger.hilt.android.qualifiers.ActivityContext
import dagger.hilt.android.qualifiers.ApplicationContext
import javax.inject.Singleton

@Module
@InstallIn(FragmentComponent::class)
class Utilities {


    @Provides
    fun modle(application: Application):VModle= ViewModelProvider.AndroidViewModelFactory(application).create(VModle::class.java)

}