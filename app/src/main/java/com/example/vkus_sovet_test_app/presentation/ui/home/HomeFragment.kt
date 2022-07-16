package com.example.vkus_sovet_test_app.presentation.ui.home

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.vkus_sovet_test_app.R
import com.example.vkus_sovet_test_app.databinding.FragmentHomeBinding
import com.example.vkus_sovet_test_app.domain.entities.MenuItem
import com.example.vkus_sovet_test_app.presentation.ui.home.adapters.MenuAdapter
import com.example.vkus_sovet_test_app.presentation.ui.home.adapters.SubMenuAdapter


import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class HomeFragment : Fragment() {

    lateinit var homeViewModel: HomeViewModel

    private var _binding: FragmentHomeBinding? = null
    private val binding: FragmentHomeBinding
        get() = _binding ?: throw RuntimeException("FragmentHomeBinding = null ")

    @Inject
    lateinit var menuAdapter: MenuAdapter

    @Inject
    lateinit var subMenuAdapter: SubMenuAdapter

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View {
        _binding = FragmentHomeBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setUpViewModel()
        subscribeToObservers()
        setUpRecyclerViews()
        setUpListeners()
    }

    private fun setUpListeners() {
        menuAdapter.setItemClickListener {
            setSubMenuAndName(it)
        }
    }

    private fun setSubMenuAndName(it: MenuItem) {
        homeViewModel.loadSubMenu(it.menuId)
        binding.tvSubMenuName.text = it.name
    }

    private fun setUpViewModel() {
        homeViewModel =
            ViewModelProvider(this)[HomeViewModel::class.java]
        homeViewModel.loadMenu()
    }

    private fun subscribeToObservers() {
        homeViewModel.menu.observe(viewLifecycleOwner) {
            menuAdapter.submitList(it)
        }
        homeViewModel.subMenu.observe(viewLifecycleOwner) {
            subMenuAdapter.subMenuList = it
        }
    }

    private fun setUpRecyclerViews() {
        binding.rvMenu.apply {
            adapter = menuAdapter
            layoutManager = LinearLayoutManager(
                requireContext(),
                LinearLayoutManager.HORIZONTAL,
                false
            )
        }
        binding.rvSubMenu.apply {
            adapter = subMenuAdapter
            layoutManager = GridLayoutManager(
                requireContext(),
                2
            )
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

}