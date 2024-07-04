package com.example.quicknotes.Databse

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.quicknotes.Dao.NotesDAo
import com.example.quicknotes.entities.Note

@Database(version = 2, entities = [Note::class], exportSchema = false)
abstract class Database_room:RoomDatabase() {
    companion object{
        @Volatile
        private var database: Database_room? = null

        @Synchronized
        fun getInstance(context: Context): Database_room {
              if (database ==null){
                 return Room.databaseBuilder(context, Database_room::class.java,"notesdata")
                     .fallbackToDestructiveMigration()
                     .build()
            } else {
                return database!!
            }

        }
    }
    abstract fun notesdao():NotesDAo
}