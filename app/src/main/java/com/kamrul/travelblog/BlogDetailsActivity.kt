package com.kamrul.travelblog;

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.kamrul.travelblog.databinding.ActivityBlogDetailsBinding

class BlogDetailsActivity : AppCompatActivity() {
    lateinit var binding: ActivityBlogDetailsBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityBlogDetailsBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.imageMain.setImageResource(R.drawable.sydney_image)
        binding.imageAvatar.setImageResource(R.drawable.avatar)

        binding.textTitle.text = "G'day from Sydney"
        binding.textDate.text = "August 2, 2019"
        binding.textAuthor.text = "Grayson Wells"
        binding.textRating.text = "4.4"
        binding.textViews.text = "(2687 views)"
        binding.textDescription.text = "Australia is one of the most popular travel destinations in the world."

        binding.ratingBar.rating = 4.4f

        binding.imageBack.setOnClickListener { finish() }
    }
}
