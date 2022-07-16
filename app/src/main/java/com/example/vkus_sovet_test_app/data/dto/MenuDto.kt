package com.example.vkus_sovet_test_app.data.dto

import com.google.gson.annotations.SerializedName

data class MenuDto(
    @SerializedName("menuID")
    val menuId:String,
    @SerializedName("image")
    val imageURL:String,
    @SerializedName("name")
    val name: String,
    @SerializedName("subMenuCount")
    val subMenuCount: String,
)
