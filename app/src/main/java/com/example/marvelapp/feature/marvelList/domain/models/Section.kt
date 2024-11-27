package com.example.marvelapp.feature.marvelList.domain.models

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class Section(
    val name: String,
    val items: ListImage = ListImage(),
):Parcelable

data class SectionItem(
    val name: String,
    val items: List<Item>
)