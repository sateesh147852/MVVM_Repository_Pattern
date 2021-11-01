package com.app.viewmodelrepository.db

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.app.viewmodelrepository.model.Result

@Database(entities = [Result::class], version = 1 )
abstract class QuoteDataBase : RoomDatabase() {

  abstract fun quoteDao(): QuoteDao

  companion object {
    private var quoteDataBase: QuoteDataBase? = null

    fun getInstance(context: Context): QuoteDataBase? {
      if (quoteDataBase == null) {
        quoteDataBase = Room.databaseBuilder(context.applicationContext,QuoteDataBase::class.java,"quote").build()
      }
      return quoteDataBase
    }


  }

}