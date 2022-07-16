package com.example.vkus_sovet_test_app.presentation.ui.home.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.RequestManager
import com.example.vkus_sovet_test_app.R
import com.example.vkus_sovet_test_app.data.mappers.Const.NO_WEIGHT
import com.example.vkus_sovet_test_app.databinding.SubmenuItemCardBinding
import com.example.vkus_sovet_test_app.domain.entities.SubMenuItem
import javax.inject.Inject
import kotlin.math.roundToInt

class SubMenuAdapter @Inject constructor(
    private val glide: RequestManager,
) : RecyclerView.Adapter<SubMenuAdapter.SubMenuItemHolder>() {

    private var onItemClickListener: ((SubMenuItem) -> Unit)? = null

    class SubMenuItemHolder(val binding: SubmenuItemCardBinding) :
        RecyclerView.ViewHolder(binding.root)

    private val diffCallback = object : DiffUtil.ItemCallback<SubMenuItem>() {
        override fun areItemsTheSame(oldItem: SubMenuItem, newItem: SubMenuItem): Boolean {
            return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(oldItem: SubMenuItem, newItem: SubMenuItem): Boolean {
            return oldItem == newItem
        }
    }

    private val differ = AsyncListDiffer(this, diffCallback)

    var subMenuList: List<SubMenuItem>
        get() = differ.currentList
        set(value) = differ.submitList(value)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SubMenuItemHolder {
        return SubMenuItemHolder(SubmenuItemCardBinding.inflate(LayoutInflater.from(parent.context),
            parent,
            false))
    }

    override fun onBindViewHolder(holder: SubMenuItemHolder, position: Int) {
        val subMenuItem = subMenuList[position]
        with(holder.binding) {
            tvSubMenuContent.text = subMenuItem.content
            tvSubMenuItemName.text = subMenuItem.name
            tvSubMenuPrice.text = "${subMenuItem.price.toDouble().toInt()} ₽"
            if (subMenuItem.weight == NO_WEIGHT) tvSubMenuWeight.text = NO_WEIGHT
            else tvSubMenuWeight.text = "/ ${subMenuItem.weight}."
            ivSpicy.setImageResource(R.drawable.spicy_img)
            ivSpicy.isVisible = subMenuItem.spicy
            glide.load(subMenuItem.imageURL).into(ivSubItemImage)
            root.setOnClickListener {
                onItemClickListener?.let { click -> click(subMenuItem) }
            }
        }
    }

    override fun getItemCount(): Int {
        return subMenuList.size
    }

    fun setItemClickListener(listener: (SubMenuItem) -> Unit) {
        onItemClickListener = listener
    }

}