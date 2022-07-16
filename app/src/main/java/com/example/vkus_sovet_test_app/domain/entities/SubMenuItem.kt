package com.example.vkus_sovet_test_app.domain.entities


data class SubMenuItem(
    val id: String,
    val imageURL: String,
    val name: String,
    val content: String,
    val price: String,
    val weight: String,
    val spicy: Boolean,
)
