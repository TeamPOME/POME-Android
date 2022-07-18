package com.teampome.pome.presentation.record.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.teampome.pome.databinding.ItemGoalListBinding
import com.teampome.pome.presentation.record.GoalListData

class GoalListAdapter : ListAdapter<GoalListData, GoalListAdapter.GoalListViewHolder>(DiffUtil) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): GoalListViewHolder {
        val binding =
            ItemGoalListBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return GoalListViewHolder(binding)
    }

    override fun onBindViewHolder(holder: GoalListViewHolder, position: Int) {
        holder.onBind(currentList[position])
    }

    class GoalListViewHolder(
        private val binding: ItemGoalListBinding
    ) : RecyclerView.ViewHolder(binding.root) {
        fun onBind(data: GoalListData) {
            binding.tvGoal.text = data.goal
        }
    }

    companion object {
        val DiffUtil = object : DiffUtil.ItemCallback<GoalListData>() {
            override fun areItemsTheSame(oldItem: GoalListData, newItem: GoalListData): Boolean {
                return oldItem.goal == newItem.goal
            }

            override fun areContentsTheSame(oldItem: GoalListData, newItem: GoalListData): Boolean {
                return oldItem == newItem
            }
        }
    }
}