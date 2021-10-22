package com.redville.mealapp.presentation.categories

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.redville.mealapp.core.utils.LayoutType
import com.redville.mealapp.databinding.GridCategoryBinding
import com.redville.mealapp.domain.model.Category

@SuppressLint("NotifyDataSetChanged")
class CategoriesAdapter : RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    private var list: MutableList<Category> = mutableListOf()

    var layoutType = LayoutType.GRID

    lateinit var listener: (category: Category) -> Unit

    fun addData(list: List<Category>) {
        this.list = list.toMutableList()

        notifyDataSetChanged()
    }

    fun changeView(layoutType: LayoutType) {
        this.layoutType = layoutType
        notifyDataSetChanged()
    }

    override fun getItemViewType(position: Int) = layoutType.ordinal

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) = ViewHolderGridItem(
        GridCategoryBinding.inflate(LayoutInflater.from(parent.context), parent, false)
    )

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) =
        (holder as BaseViewHolder).bind(
            list[position], listener
        )

    override fun getItemCount() = list.size

}

class ViewHolderGridItem(private val binding: GridCategoryBinding) :
    BaseViewHolder(binding.root) {

    override fun bind(data: Category, listener: (category: Category) -> Unit) {
        binding.item = data

        binding.root.setOnClickListener {
            listener(data)
        }
    }

}

abstract class BaseViewHolder(private val root: View) : RecyclerView.ViewHolder(root) {

    abstract fun bind(data: Category, listener: (category: Category) -> Unit)

}