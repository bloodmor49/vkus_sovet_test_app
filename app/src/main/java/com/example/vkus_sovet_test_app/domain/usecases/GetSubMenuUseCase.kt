package com.example.vkus_sovet_test_app.domain.usecases

import com.example.vkus_sovet_test_app.domain.AppRepository
import com.example.vkus_sovet_test_app.domain.entities.MenuItem
import com.example.vkus_sovet_test_app.domain.entities.SubMenuItem
import com.example.vkus_sovet_test_app.domain.util.Resource
import javax.inject.Inject

class GetSubMenuUseCase @Inject constructor(
    private val repository: AppRepository,
) {
    suspend fun getSubMenu(menuId: String): Resource<List<SubMenuItem>> = repository.getSubMenu(menuId)
}