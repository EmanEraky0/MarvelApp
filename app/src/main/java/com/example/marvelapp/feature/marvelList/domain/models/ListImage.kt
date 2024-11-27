package com.example.marvelapp.feature.marvelList.domain.models

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class ListImage(
    val available: Int=0,
    val collectionURI: String="",
    val items: ArrayList<Item> = arrayListOf(),
    val returned: Int=0
):Parcelable