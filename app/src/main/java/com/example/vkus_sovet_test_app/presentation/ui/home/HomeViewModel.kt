package com.example.vkus_sovet_test_app.presentation.ui.home

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.vkus_sovet_test_app.domain.entities.MenuItem
import com.example.vkus_sovet_test_app.domain.entities.SubMenuItem
import com.example.vkus_sovet_test_app.domain.usecases.GetMenuUseCase
import com.example.vkus_sovet_test_app.domain.usecases.GetSubMenuUseCase
import com.example.vkus_sovet_test_app.domain.util.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val getMenuUseCase: GetMenuUseCase,
    private val getSubMenuUseCase: GetSubMenuUseCase,
) : ViewModel() {

    private val _menu = MutableLiveData<List<MenuItem>>()
    val menu: LiveData<List<MenuItem>> = _menu

    private val _subMenu = MutableLiveData<List<SubMenuItem>>()
    val subMenu: LiveData<List<SubMenuItem>> = _subMenu

    fun loadMenu() {
        viewModelScope.launch {
                when (val menuResult =
                    getMenuUseCase.getMenu()) {
                    is Resource.Success -> {
                        _menu.value = menuResult.data!!
                        Log.d("ResponseData", "Success on VM ${menuResult.data}")
                    }
                    is Resource.Error -> {
                        Log.d("ResponseData", "Error on VM ${menuResult.message}")
                    }
                }
        }
    }

    fun loadSubMenu(menuItem: String) {
        viewModelScope.launch {
            when (val subMenuResult =
                getSubMenuUseCase.getSubMenu(menuItem)) {
                is Resource.Success -> {
                    Log.d("ResponseloadSubMenu", "Success on VM ${subMenuResult.data}")
                    _subMenu.value = subMenuResult.data!!
                }
                is Resource.Error -> {
                    Log.d("ResponseloadSubMenu", "Error on VM ${subMenuResult.message}")
                }
            }
        }
    }
}

