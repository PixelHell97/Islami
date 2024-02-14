package com.pixel.islami.api

import com.pixel.islami.api.model.radioResponses.RadioResources
import retrofit2.Call
import retrofit2.http.GET

interface WebServices {
    @GET("api/v3/radios")
    fun getRadioSources(): Call<RadioResources>
}
