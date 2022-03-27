package com.kamrul.travelblog.http

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class BlogData(val data: List<Blog>) : Parcelable

@Parcelize
data class Blog(
    val id: String,
    var author: Author,
    val title: String,
    val date: String,
    val image: String,
    val description: String,
    val views: Int,
    val rating: Float
) : Parcelable {
    fun getImageUrl() = BlogHttpClient.BASE_URL + BlogHttpClient.PATH + image
}

@Parcelize
data class Author(val name: String, val avatar: String) : Parcelable {
    fun getAvatarUrl() = BlogHttpClient.BASE_URL + BlogHttpClient.PATH + avatar
}
