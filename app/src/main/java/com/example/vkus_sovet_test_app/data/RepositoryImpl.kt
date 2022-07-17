package com.example.vkus_sovet_test_app.data

import android.util.Log
import com.example.vkus_sovet_test_app.data.mappers.toMenuItemList
import com.example.vkus_sovet_test_app.data.mappers.toSubMenuItemList
import com.example.vkus_sovet_test_app.data.remote.ApiFactory
import com.example.vkus_sovet_test_app.domain.AppRepository
import com.example.vkus_sovet_test_app.domain.entities.MenuItem
import com.example.vkus_sovet_test_app.domain.entities.SubMenuItem
import com.example.vkus_sovet_test_app.domain.util.Resource
import javax.inject.Inject


class RepositoryImpl @Inject constructor(
    private val apiFactory: ApiFactory,
) : AppRepository {
    override suspend fun getMenu(): Resource<List<MenuItem>> {
        return try {
            Resource.Success(data = apiFactory.retrofit.getMenu().menus.toMenuItemList())
        } catch (e: Exception) {
            e.printStackTrace()
            Resource.Error(e.message ?: "Неизвестная ошибка в методе getMenu. Проверьте интернет соединение.")
        }
    }

    override suspend fun getSubMenu(menuId: String): Resource<List<SubMenuItem>> {
        return try {
            val dataResult = apiFactory.retrofit.getSubMenu(menuId)
            Log.d("getSubMenu",dataResult.menus.toSubMenuItemList().toString())
            if (dataResult.status == "false") {
                Resource.Error("Ошибка запроса подменю")
            } else {
                Resource.Success(data = dataResult.menus.toSubMenuItemList())
            }
        } catch (e: Exception) {
            e.printStackTrace()
            Resource.Error(e.message ?: "Неизвестная ошибка getSubMenu. Проверьте интернет соединение")
        }
    }
}