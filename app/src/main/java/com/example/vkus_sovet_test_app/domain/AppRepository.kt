package com.example.vkus_sovet_test_app.domain

import com.example.vkus_sovet_test_app.domain.entities.MenuItem
import com.example.vkus_sovet_test_app.domain.entities.SubMenuItem
import com.example.vkus_sovet_test_app.domain.util.Resource

interface AppRepository {
    suspend fun getMenu(): Resource<List<MenuItem>>
    suspend fun getSubMenu(menuId: String): Resource<List<SubMenuItem>>
}