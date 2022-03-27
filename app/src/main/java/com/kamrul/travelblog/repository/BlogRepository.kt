package com.kamrul.travelblog.repository

import android.content.Context
import com.kamrul.travelblog.database.DatabaseProvider
import com.kamrul.travelblog.http.Blog
import com.kamrul.travelblog.http.BlogHttpClient
import java.util.concurrent.Executors

class BlogRepository(context: Context) {
    private val httpClient = BlogHttpClient
    private val database = DatabaseProvider.getInstance(context.applicationContext)
    private val executor = Executors.newSingleThreadExecutor()

    fun loadDataFromDatabase(callback: (List<Blog>) -> Unit) {
        executor.execute {
            callback(database.blogDao().getAll())
        }
    }

    fun loadDataFromNetwork(onSuccess: (List<Blog>) -> Unit, onError: () -> Unit) {
        executor.execute {
            val blogList = httpClient.loadBlogArticles()
            if (blogList == null) {
                onError()
            } else {
                val blogDAO = database.blogDao()
                blogDAO.deleteAll()
                blogDAO.insertAll(blogList)

                onSuccess(blogList)
            }
        }
    }
}