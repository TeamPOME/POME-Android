package com.teampome.pome.presentation.login

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.teampome.pome.databinding.ItemFriendListBinding

class AddFriendAdapter : ListAdapter<AddFriendData, AddFriendAdapter.RepoViewHolder>(
    DIFFUTIL
) {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RepoViewHolder {
        val binding =
            ItemFriendListBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return RepoViewHolder(binding)

    }

    override fun onBindViewHolder(holder: RepoViewHolder, position: Int) {
        holder.onBind(getItem(position))
    }

    class RepoViewHolder(
        private val binding: ItemFriendListBinding
    ) : RecyclerView.ViewHolder(binding.root) {
        fun onBind(friendData: AddFriendData) {
            binding.friend = friendData
        }
    }

    companion object {
        val DIFFUTIL = object : DiffUtil.ItemCallback<AddFriendData>() {
            override fun areItemsTheSame(
                oldItem: AddFriendData,
                newItem: AddFriendData
            ): Boolean {
                return oldItem.name == newItem.name
            }

            override fun areContentsTheSame(
                oldItem: AddFriendData,
                newItem: AddFriendData
            ): Boolean {
                return oldItem == newItem
            }
        }
    }
}