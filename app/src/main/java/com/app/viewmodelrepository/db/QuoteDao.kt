package com.app.viewmodelrepository.db

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.app.viewmodelrepository.model.Result

@Dao
interface QuoteDao {

  @Insert(onConflict = OnConflictStrategy.REPLACE)
  suspend fun insertAll(results: List<Result>)

  @Query("SELECT * FROM Result")
  suspend fun getResults(): List<Result>

  @Query("DELETE FROM Result")
  suspend fun deleteAll()

}