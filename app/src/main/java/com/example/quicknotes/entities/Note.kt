package com.example.quicknotes.entities

import androidx.databinding.BaseObservable
import androidx.databinding.Bindable
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.example.quicknotes.BR
import java.time.Month

@Entity(tableName = "Notes")
data class Note(
    @PrimaryKey(autoGenerate = true)   var id:Int?=null,
    @ColumnInfo(name = "Titles")       var title:String,
    @ColumnInfo(name = "Notes")        var note:String,
    @ColumnInfo(name = "Date")         var date:String,
    @ColumnInfo(name = "Day")          var day:String,
    @ColumnInfo(name = "Month")        var month:String,
    @ColumnInfo(name = "priorities")   @get:Bindable var priority:String,
    @ColumnInfo(name = "status")       var stauts:Boolean

):BaseObservable(){
    var PROPRITY: String
        @Bindable get()=priority
        set(value){
            priority = value
            notifyPropertyChanged(BR.priority)
        }

}
