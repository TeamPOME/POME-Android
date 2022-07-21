package com.teampome.pome.presentation.record.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.teampome.pome.data.remote.response.ResponseGoalCreate
import com.teampome.pome.databinding.ItemGoalListBinding
import com.teampome.pome.presentation.record.GoalListData
import retrofit2.Response

class GoalListAdapter(private val itemClick: (ResponseGoalCreate) -> Unit) :
    ListAdapter<ResponseGoalCreate, GoalListAdapter.GoalListViewHolder>(DiffUtil) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): GoalListViewHolder {
        val binding =
            ItemGoalListBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return GoalListViewHolder(binding, itemClick)
    }

    override fun onBindViewHolder(holder: GoalListViewHolder, position: Int) {
        holder.onBind(currentList[position])
    }

    class GoalListViewHolder(
        private val binding: ItemGoalListBinding,
        private val itemClick: (ResponseGoalCreate) -> (Unit)
    ) : RecyclerView.ViewHolder(binding.root) {
        fun onBind(data: ResponseGoalCreate) {
            binding.tvGoal.text = data.category
            binding.root.setOnClickListener {
                itemClick(data)
            }
        }
    }

    companion object {
        val DiffUtil = object : DiffUtil.ItemCallback<ResponseGoalCreate>() {
            override fun areItemsTheSame(
                oldItem: ResponseGoalCreate,
                newItem: ResponseGoalCreate
            ): Boolean {
                return oldItem.id == newItem.id
            }

            override fun areContentsTheSame(
                oldItem: ResponseGoalCreate,
                newItem: ResponseGoalCreate
            ): Boolean {
                return oldItem == newItem
            }
        }
    }
}