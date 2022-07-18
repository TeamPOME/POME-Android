package com.teampome.pome.presentation.friends.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.teampome.pome.R
import com.teampome.pome.databinding.ItemFriendsEmojiBinding
import com.teampome.pome.databinding.ItemFriendsReactionBinding
import com.teampome.pome.presentation.friends.FriendReactionData
import com.teampome.pome.presentation.friends.FriendsConsumeData

class FriendsReactAdapter :
    ListAdapter<FriendReactionData, FriendsReactAdapter.FriendsEmojiViewHolder>( DIFFUTIL) {
    class FriendsEmojiViewHolder(
        private val binding:ItemFriendsReactionBinding
    ):RecyclerView.ViewHolder(binding.root) {
        fun bind(friendReactionData: FriendReactionData){
            when(friendReactionData.emoji){
                1-> {
                    binding.ivFriendsReaction.setImageResource(R.drawable.ic_click_emoji_happy)
                }
                2->{
                    binding.ivFriendsReaction.setImageResource(R.drawable.ic_emoji_mint_smile)
                }
                3-> {
                    binding.ivFriendsReaction.setImageResource(R.drawable.ic_emoji_mint_fun)
                }
                4-> {
                    binding.ivFriendsReaction.setImageResource(R.drawable.ic_emoji_mint_flex)
                }
                5->{
                    binding.ivFriendsReaction.setImageResource(R.drawable.ic_emoji_mint_what)
                }
                6-> {
                    binding.ivFriendsReaction.setImageResource(R.drawable.ic_click_emoji_sad)
                }
            }
            binding.tvFriendsReaction.text=friendReactionData.name

        }
    }


    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): FriendsReactAdapter.FriendsEmojiViewHolder {
        val binding=ItemFriendsReactionBinding.inflate(
            LayoutInflater.from(parent.context),parent,false
        )
        return FriendsEmojiViewHolder(binding)
    }

    override fun onBindViewHolder(
        holder: FriendsReactAdapter.FriendsEmojiViewHolder,
        position: Int
    ) {
        holder.bind(getItem(position))
    }

    companion object {
        val DIFFUTIL = object : DiffUtil.ItemCallback<FriendReactionData>() {
            override fun areItemsTheSame(
                oldItem: FriendReactionData,
                newItem: FriendReactionData
            ): Boolean = oldItem.name == newItem.name

            override fun areContentsTheSame(
                oldItem: FriendReactionData,
                newItem: FriendReactionData
            ): Boolean = oldItem == newItem
        }
    }

}