package com.example.vkus_sovet_test_app.data.dto

import com.google.gson.annotations.SerializedName

data class ResponseDto<T>(
    @SerializedName("menuList")
    val menus: List<T>,
    @SerializedName("status")
    val status: String
)
