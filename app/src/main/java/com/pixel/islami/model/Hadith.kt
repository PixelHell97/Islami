package com.pixel.islami.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class Hadith(
    val title: String,
    val hadith: String
) : Parcelable
