package com.example.vkus_sovet_test_app.domain.entities

data class MenuItem(
    val menuId:String,
    val imageURL:String,
    val name: String,
    val subMenuCount: Int,
    var activated: Boolean = false
)
