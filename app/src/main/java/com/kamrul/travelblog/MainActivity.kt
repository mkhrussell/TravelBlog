package com.kamrul.travelblog

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.android.material.snackbar.Snackbar
import com.kamrul.travelblog.databinding.ActivityMainBinding
import com.kamrul.travelblog.http.Blog
import com.kamrul.travelblog.http.BlogHttpClient

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private val adapter = MainAdapter()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.recyclerView.layoutManager = LinearLayoutManager(this)
        binding.recyclerView.adapter = adapter

        loadData()
    }

    private fun loadData() {
        BlogHttpClient.loadBlogArticles(
            onSuccess = { blogList: List<Blog> ->
                runOnUiThread { adapter.submitList(blogList) }
            },
            onError = {
                runOnUiThread { showErrorSnackbar() }
            }
        )
    }

    private fun showErrorSnackbar() {
        Snackbar.make(binding.root, "Error during loading blog articles", Snackbar.LENGTH_INDEFINITE).run {
            setActionTextColor(ContextCompat.getColor(this@MainActivity, R.color.orange500))
            setAction("Retry") {
                loadData()
                dismiss()
            }
        }.show()
    }
}


