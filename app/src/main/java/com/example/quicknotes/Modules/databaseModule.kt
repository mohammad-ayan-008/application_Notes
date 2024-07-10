package com.example.quicknotes.Modules

import android.content.Context
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.quicknotes.Dao.NotesDAo
import com.example.quicknotes.Databse.Database_room
import com.example.quicknotes.Fragments.List_data
import com.example.quicknotes.Fragments.Login_Fragment
import com.example.quicknotes.entities.Note
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class Module {

    @Provides
    @Singleton
    fun provideDatabase(@ApplicationContext applicationContext: Context):Database_room{
        return Database_room.getInstance(applicationContext)
    }

    @Provides
    fun getDao(databaseRoom: Database_room):NotesDAo{
        return databaseRoom.notesdao()
    }

    @Singleton
    @Provides
    fun frag_1():List_data= List_data()

    @Singleton
    @Provides
    fun frag_2():Login_Fragment= Login_Fragment()

}