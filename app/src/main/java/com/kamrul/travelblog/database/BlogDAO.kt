package com.kamrul.travelblog.database

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.kamrul.travelblog.http.Blog

@Dao
interface BlogDAO {
    @Query("SELECT * FROM blogs")
    fun getAll(): List<Blog>

    @Insert
    fun insertAll(blogList: List<Blog>)

    @Query("DELETE FROM blogs")
    fun deleteAll()
}