package com.newbieloper.millie.data.remote

import com.google.gson.annotations.SerializedName

data class ErrorBody(
    @SerializedName("status") val status: String?,
    @SerializedName("code") val code: String?,
    @SerializedName("message") val message: String?
)