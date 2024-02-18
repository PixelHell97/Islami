package com.pixel.islami.api.model.radioResponses

import com.google.gson.annotations.SerializedName

data class Radio(
    @field:SerializedName("id")
    val id: Int? = null,
    @field:SerializedName("name")
    val name: String? = null,
    @field:SerializedName("url")
    val url: String? = null,
)
