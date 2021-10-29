package com.redville.mealapp.presentation.meals

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.redville.mealapp.R
import com.redville.mealapp.core.utils.LayoutType
import com.redville.mealapp.databinding.RowMealBinding
import com.redville.mealapp.domain.model.Meal

@SuppressLint("NotifyDataSetChanged")
class MealsAdapter : RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    private var list : MutableList<Meal> = mutableListOf()
    var layoutType = LayoutType.LINEAR
    lateinit var listener : (meal: Meal) -> Unit

    fun addData(list: List<Meal>) {
        this.list = list.toMutableList()
        notifyDataSetChanged()
    }

    override fun getItemViewType(position: Int) = layoutType.ordinal

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) = ViewHolderItem(
        RowMealBinding.inflate(LayoutInflater.from(parent.context), parent, false)
    )

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) =
        (holder as BaseViewHolder).bind(
            list[position], listener
        )

    override fun getItemCount() = list.size
}

class ViewHolderItem(private val binding: RowMealBinding) :
    BaseViewHolder(binding.root) {

    override fun bind(data: Meal, listener: (meal: Meal) -> Unit) {
        binding.item = data

        binding.root.setOnClickListener {
            listener(data)
        }

        if (data.liked == 1) binding.imgLike.setImageResource(R.drawable.ic_likeon)
        else binding.imgLike.setImageResource(R.drawable.ic_likeoff)
    }

}

abstract class BaseViewHolder(private val root: View) : RecyclerView.ViewHolder(root) {

    abstract fun bind(data: Meal, listener: (meal: Meal) -> Unit)

}

