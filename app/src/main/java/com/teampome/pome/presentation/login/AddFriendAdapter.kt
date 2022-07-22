package com.teampome.pome.presentation.login

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import coil.load
import com.teampome.pome.R
import com.teampome.pome.data.remote.request.RequestFriendsData
import com.teampome.pome.data.remote.response.ResponseFriendsData
import com.teampome.pome.databinding.ItemFriendListBinding
import com.teampome.pome.util.setOnSingleClickListener

class AddFriendAdapter(
    private val itemClick: (ResponseFriendsData) -> Unit
) : ListAdapter<ResponseFriendsData, AddFriendAdapter.RepoViewHolder>(
        DIFFUTIL
    ) {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RepoViewHolder {
        val binding =
            ItemFriendListBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return RepoViewHolder(binding,itemClick)

    }

    override fun onBindViewHolder(holder: RepoViewHolder, position: Int) {
        holder.onBind(getItem(position))
    }

    class RepoViewHolder(
        private val binding: ItemFriendListBinding,
        private val itemClick: (ResponseFriendsData) -> (Unit)
    ) : RecyclerView.ViewHolder(binding.root) {
        fun onBind(friendData: ResponseFriendsData) {
            binding.friend = friendData
            binding.ivProfile.load(friendData.profileImage)
            binding.btnAdd.setOnSingleClickListener {
                !binding.btnAdd.isClickable
                binding.btnAdd.setImageResource(R.drawable.ic_add_complete)
                itemClick(friendData)
            }
        }
    }

    companion object {
        val DIFFUTIL = object : DiffUtil.ItemCallback<ResponseFriendsData>() {
            override fun areItemsTheSame(
                oldItem: ResponseFriendsData,
                newItem: ResponseFriendsData
            ): Boolean {
                return oldItem.nickname == newItem.nickname
            }

            override fun areContentsTheSame(
                oldItem: ResponseFriendsData,
                newItem: ResponseFriendsData
            ): Boolean {
                return oldItem == newItem
            }
        }
    }
}