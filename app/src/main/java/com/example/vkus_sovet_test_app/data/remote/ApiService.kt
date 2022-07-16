package com.example.vkus_sovet_test_app.data.remote

import com.example.vkus_sovet_test_app.data.dto.MenuDto
import com.example.vkus_sovet_test_app.data.dto.ResponseDto
import com.example.vkus_sovet_test_app.data.dto.SubMenuDto
import retrofit2.http.Field
import retrofit2.http.FormUrlEncoded
import retrofit2.http.POST

interface ApiService {

    @POST("/api/getMenu.php")
    suspend fun getMenu(): ResponseDto<MenuDto>

    @FormUrlEncoded
    @POST("/api/getSubMenu.php")
    suspend fun getSubMenu(
        @Field("menuID") menuId: String,
    ): ResponseDto<SubMenuDto>
}