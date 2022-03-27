package com.kamrul.travelblog

import android.content.DialogInterface
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.android.material.dialog.MaterialAlertDialogBuilder
import com.google.android.material.snackbar.Snackbar
import com.kamrul.travelblog.databinding.ActivityMainBinding
import com.kamrul.travelblog.http.Blog
import com.kamrul.travelblog.http.BlogHttpClient

class MainActivity : AppCompatActivity() {

    companion object {
        private const val SORT_TITLE = 0
        private const val SORT_DATE = 1
    }

    private var currentSort = SORT_DATE

    private lateinit var binding: ActivityMainBinding
    private val adapter = MainAdapter { blog ->
        BlogDetailsActivity.start(this, blog)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.materialToolbar.setOnMenuItemClickListener { item ->
            if(item.itemId == R.id.sort) {
                onSortClicked()
            }
            false
        }

        binding.recyclerView.layoutManager = LinearLayoutManager(this)
        binding.recyclerView.adapter = adapter
        binding.refreshLayout.setOnRefreshListener {
            loadData()
        }

        loadData()
    }

    private fun onSortClicked() {
        val items = arrayOf("Title", "Date")
        MaterialAlertDialogBuilder(this)
            .setTitle("Sort order")
            .setSingleChoiceItems(items, currentSort) { dialog: DialogInterface, which: Int  ->
                dialog.dismiss()
                currentSort = which
                sortData()
            }.show()
    }

    private fun sortData() {
        if (currentSort == SORT_TITLE) {
            adapter.sortByTitle()
        } else if (currentSort == SORT_DATE) {
            adapter.sortByDate()
        }
    }

    private fun loadData() {
        binding.refreshLayout.isRefreshing = true
        BlogHttpClient.loadBlogArticles(
            onSuccess = { blogList: List<Blog> ->
                binding.refreshLayout.isRefreshing = false
                runOnUiThread { adapter.submitList(blogList) }
            },
            onError = {
                binding.refreshLayout.isRefreshing = false
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


