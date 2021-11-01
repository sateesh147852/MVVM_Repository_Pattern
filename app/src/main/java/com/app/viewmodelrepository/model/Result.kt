package com.app.viewmodelrepository.model

import androidx.room.Entity
import androidx.room.Index
import androidx.room.PrimaryKey

@Entity(tableName = "Result",indices = [Index(value = ["_id"], unique = true)])
data class Result(
  @PrimaryKey(autoGenerate = true)
  val quoteId: Int,
  val length: Int,
  val dateAdded: String,
  val _id: String,
  val author: String,
  val authorSlug: String,
  val content: String,
  val dateModified: String
)