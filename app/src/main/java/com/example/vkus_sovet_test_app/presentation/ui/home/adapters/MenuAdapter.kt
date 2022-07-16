package com.example.vkus_sovet_test_app.presentation.ui.home.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.RequestManager
import com.example.vkus_sovet_test_app.R
import com.example.vkus_sovet_test_app.databinding.MenuItemCardBinding
import com.example.vkus_sovet_test_app.domain.entities.MenuItem
import javax.inject.Inject

class MenuAdapter @Inject constructor(
    private val glide: RequestManager,
) : ListAdapter<MenuItem, MenuAdapter.MenuItemHolder>(DiffCallback()) {

    private var selectedItemPosition = NO_ACTIVE_POSITION

    private var onItemClickListener: ((MenuItem) -> Unit)? = null

    class MenuItemHolder(val binding: MenuItemCardBinding) : RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MenuItemHolder {
        return MenuItemHolder(MenuItemCardBinding.inflate(LayoutInflater.from(parent.context),
            parent,
            false))
    }

    override fun onBindViewHolder(holder: MenuItemHolder, position: Int) {
        val menuItem = getItem(position)
        with(holder.binding) {
            tvMenuItemName.text = menuItem.name
            tvSubMenuCount.text = "${menuItem.subMenuCount} товаров"
            glide.load(menuItem.imageURL).into(ivItemImage)
            if (selectedItemPosition == position) {
                cardHolderMenu.setBackgroundColor(ContextCompat.getColor(holder.binding.root.context,
                    R.color.menu_item_background_selected))
            } else {
                cardHolderMenu.setBackgroundColor(ContextCompat.getColor(holder.binding.root.context,
                    R.color.menu_item_background))
            }
            root.setOnClickListener {
                selectedItemPosition = holder.adapterPosition
                notifyDataSetChanged()
                //#TODO решить проблему с нотификацией
                onItemClickListener?.let { click ->
                    click(menuItem)
                }
            }
        }

    }

    fun setItemClickListener(listener: (MenuItem) -> Unit) {
        onItemClickListener = listener
    }

    private class DiffCallback : DiffUtil.ItemCallback<MenuItem>() {
        override fun areItemsTheSame(oldItem: MenuItem, newItem: MenuItem): Boolean {
            return oldItem.menuId == newItem.menuId && oldItem.activated == newItem.activated
        }

        override fun areContentsTheSame(oldItem: MenuItem, newItem: MenuItem): Boolean {
            return oldItem.hashCode() == newItem.hashCode()
        }
    }

    companion object {
        const val NO_ACTIVE_POSITION = -1
    }
}