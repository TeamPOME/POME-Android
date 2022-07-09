package com.teampome.pome.presentation.friends

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.teampome.pome.databinding.ItemFriendConsumeListBinding

class FriendsConsumeAdapter :
    ListAdapter<FriendsConsumeData, FriendsConsumeAdapter.FriendsConsumeViewHolder>(
        DIFFUTIL
    ) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FriendsConsumeViewHolder {
        val binding = ItemFriendConsumeListBinding.inflate(
            LayoutInflater.from(parent.context), parent, false
        )
        return FriendsConsumeViewHolder(binding)
    }

    override fun onBindViewHolder(holder: FriendsConsumeViewHolder, position: Int) {
        holder.bind(getItem(position))
    }

    class FriendsConsumeViewHolder(private val binding: ItemFriendConsumeListBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(friendsConsumeData: FriendsConsumeData) {
            binding.tvFriendname.text = friendsConsumeData.name
            binding.tvFrienddate.text = friendsConsumeData.date
            binding.tvFrienddes.text = friendsConsumeData.description
            binding.tvFriendprice.text = friendsConsumeData.price
            binding.tvFriendtag.text = friendsConsumeData.tag
            //프로필 이미지, 반응들, 처음감정과 나중감정

        }

    }


    companion object {
        val DIFFUTIL = object : DiffUtil.ItemCallback<FriendsConsumeData>() {
            override fun areItemsTheSame(
                oldItem: FriendsConsumeData,
                newItem: FriendsConsumeData
            ): Boolean = oldItem.name == newItem.name

            override fun areContentsTheSame(
                oldItem: FriendsConsumeData,
                newItem: FriendsConsumeData
            ): Boolean = oldItem == newItem

        }
    }


}