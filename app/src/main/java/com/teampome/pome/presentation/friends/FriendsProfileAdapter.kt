package com.teampome.pome.presentation.friends

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.teampome.pome.databinding.ItemFriendProfileListBinding

class FriendsProfileAdapter :
    ListAdapter<FriendsProfileData, FriendsProfileAdapter.FriendsProfileViewModel>(
        DIFFUTIL
    ) {
    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): FriendsProfileAdapter.FriendsProfileViewModel {
        val binding = ItemFriendProfileListBinding.inflate(
            LayoutInflater.from(parent.context), parent, false
        )
        return FriendsProfileViewModel(binding)
    }

    override fun onBindViewHolder(
        holder: FriendsProfileAdapter.FriendsProfileViewModel,
        position: Int
    ) {
        holder.bind(getItem(position))
    }

    class FriendsProfileViewModel(private val binding: ItemFriendProfileListBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(friendsProfileData: FriendsProfileData) {
            //이미지 설정
            binding.tvFriendname.text = friendsProfileData.name

        }
    }


    companion object {
        val DIFFUTIL = object : DiffUtil.ItemCallback<FriendsProfileData>() {
            override fun areItemsTheSame(
                oldItem: FriendsProfileData,
                newItem: FriendsProfileData
            ): Boolean {
                TODO("Not yet implemented")
            }

            override fun areContentsTheSame(
                oldItem: FriendsProfileData,
                newItem: FriendsProfileData
            ): Boolean {
                TODO("Not yet implemented")
            }

        }
    }

}


