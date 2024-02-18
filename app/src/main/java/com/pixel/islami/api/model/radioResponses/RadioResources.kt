package com.pixel.islami.api.model.radioResponses

import com.google.gson.annotations.SerializedName

data class RadioResources(
    @field:SerializedName("radios")
    val radios: List<Radio?>?,
)
