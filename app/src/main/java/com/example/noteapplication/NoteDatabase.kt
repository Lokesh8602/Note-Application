package com.example.noteapplication

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase


@Database(entities = arrayOf(Note::class), version = 1, exportSchema = false)
 abstract class NoteDatabase: RoomDatabase(){
     abstract fun getNotesDao(): NoteDao

     companion object{
         @Volatile
         private var Instance: NoteDatabase? =null
         fun getDatabase(context: Context): NoteDatabase {
             // if the INSTANCE is not null, then return it,
             // if it is, then create the database
             return Instance?: synchronized(this) {
                 val instance = Room.databaseBuilder(
                     context.applicationContext,
                     NoteDatabase::class.java,
                     "note_database"
                 ).build()
                 Instance = instance
                 // return instance
                 instance
             }
         }
     }
}