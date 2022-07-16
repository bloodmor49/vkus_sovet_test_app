package com.example.vkus_sovet_test_app.data.dto

import com.google.gson.annotations.SerializedName


data class SubMenuDto(
    @SerializedName("id")
    val id: String,
    @SerializedName("image")
    val imageURL: String,
    @SerializedName("name")
    val name: String,
    @SerializedName("content")
    val content: String,
    @SerializedName("price")
    val price: String,
    @SerializedName("weight")
    val weight: String?,
    @SerializedName("spicy")
    val spicy: String?,
)
