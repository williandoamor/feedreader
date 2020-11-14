package br.com.loadti.feedreader.data

import android.net.Uri


data class ItemFeed(

    val titulo: String?,
    val autor: String?,
    val data: Long,
    val link: Uri?,
    val image: String?
)