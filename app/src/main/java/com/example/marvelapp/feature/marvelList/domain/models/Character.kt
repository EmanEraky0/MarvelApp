package com.example.marvelapp.feature.marvelList.domain.models

import android.os.Parcelable
import kotlinx.parcelize.Parcelize


@Parcelize
data class Character(
    val id: Int = 0,
    val name: String = "",
    val title: String = "",
    val description: String = "",
    val resourceURI: String = "",
    val modified: String = "",
    val thumbnail : Thumbnail?=null,

    val comics :ListImage?=null,
    val stories : ListImage?=null,
    val events : ListImage?=null,
    val series : ListImage?=null,

    var itemSections :ArrayList<Section>?=null,
    val urls: List<Url>?=null

    ):Parcelable

