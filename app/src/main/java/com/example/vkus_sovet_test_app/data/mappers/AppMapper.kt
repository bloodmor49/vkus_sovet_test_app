package com.example.vkus_sovet_test_app.data.mappers

import com.example.vkus_sovet_test_app.data.dto.MenuDto
import com.example.vkus_sovet_test_app.data.dto.SubMenuDto
import com.example.vkus_sovet_test_app.data.mappers.Const.NO_WEIGHT
import com.example.vkus_sovet_test_app.data.mappers.Const.SPICY
import com.example.vkus_sovet_test_app.data.remote.ApiFactory.BASE_URL
import com.example.vkus_sovet_test_app.domain.entities.MenuItem
import com.example.vkus_sovet_test_app.domain.entities.SubMenuItem

fun MenuDto.toMenuItem() =
    MenuItem(
        menuId = menuId,
        imageURL = "$BASE_URL$imageURL" ,
        name = name,
        subMenuCount = subMenuCount.trim().toInt())

fun List<MenuDto>.toMenuItemList() =
    map { it.toMenuItem() }

fun SubMenuDto.toSubMenuItem() =
    SubMenuItem(
        id = id,
        imageURL = "$BASE_URL$imageURL",
        name = name,
        content = content,
        price = price,
        weight = weight ?: NO_WEIGHT,
        spicy = spicy == SPICY)

fun List<SubMenuDto>.toSubMenuItemList() =
    map { it.toSubMenuItem() }

object Const {
    const val NO_WEIGHT = ""
    const val SPICY = "Y"
}
