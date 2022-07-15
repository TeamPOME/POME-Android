package com.teampome.pome.presentation.friends.adapters

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.teampome.pome.R
import com.teampome.pome.databinding.ItemFriendConsumeListBinding
import com.teampome.pome.presentation.friends.FriendsConsumeData
import timber.log.Timber

class FriendsConsumeAdapter() :
    ListAdapter<FriendsConsumeData, FriendsConsumeAdapter.FriendsConsumeViewHolder>(
        DIFFUTIL
    ) {
    private lateinit var listener: FriendsConsumeListInterface
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FriendsConsumeViewHolder {
        val binding = ItemFriendConsumeListBinding.inflate(
            LayoutInflater.from(parent.context), parent, false
        )
        return FriendsConsumeViewHolder(binding)
    }

    override fun onBindViewHolder(holder: FriendsConsumeViewHolder, position: Int) {
        holder.bind(getItem(position))
        holder.itemView.setOnClickListener {
            listener.onClick(it, position, false)
        }
        holder.addEmojiButton.setOnClickListener {
            listener.onClick(it, position, true)
        }

    }

    override fun onBindViewHolder(
        holder: FriendsConsumeViewHolder,
        position: Int,
        payloads: MutableList<Any>
    ) {
        if (payloads.isEmpty())
            super.onBindViewHolder(holder, position, payloads)
        else {
            val bundle = payloads[0] as Bundle
            Timber.d("bundle=$bundle")
            val pos = bundle.getInt("emoji_position")
            Timber.d("emoji_position=$pos")
            holder.setEmoji(pos)
        }
    }

    fun changeItem(item: FriendsConsumeData, position: Int) {
        //여기서 position은 emoji_positon임
        val newList = currentList.mapIndexed { index, friendsConsumeData ->
            if (index == position) item else friendsConsumeData
        }.toList()
        submitList(newList)
    }

    class FriendsConsumeViewHolder(
        private val binding: ItemFriendConsumeListBinding
    ) :
        RecyclerView.ViewHolder(binding.root) {
        val addEmojiButton = binding.ivAddemotion

        fun bind(friendsConsumeData: FriendsConsumeData) {
            binding.tvFriendname.text = friendsConsumeData.name
            binding.tvFrienddate.text = friendsConsumeData.date
            binding.tvFrienddes.text = friendsConsumeData.description
            binding.tvFriendprice.text = friendsConsumeData.price
            binding.tvFriendtag.text = friendsConsumeData.tag
            //프로필 이미지, 반응들, 처음감정과 나중감정
        }

        fun setEmoji(position: Int) {
            val pos = position
            when (pos) {
                0 -> {
                    binding.ivAddemotion.setImageResource(R.drawable.ic_emoji_happy_mint_28)
                }
                1 -> {
                    binding.ivAddemotion.setImageResource(R.drawable.ic_emoji_smile_mint_28)
                }
                2 -> {
                    binding.ivAddemotion.setImageResource(R.drawable.ic_emoji_funny_mint_28)
                }
                3 -> {
                    binding.ivAddemotion.setImageResource(R.drawable.ic_emoji_flex_mint_28)
                }
                4 -> {
                    binding.ivAddemotion.setImageResource(R.drawable.ic_emoji_what_mint_28)
                }
                5 -> {
                    binding.ivAddemotion.setImageResource(R.drawable.ic_emoji_sad_mint_28)
                }
                else -> {
                    binding.ivAddemotion.setImageResource(R.drawable.ic_btn_emoji_add)
                }
            }
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

    interface FriendsConsumeListInterface {
        fun onClick(data: View, position: Int, addEmoji: Boolean)
    }

    fun setConsumeListClickListener(listener: FriendsConsumeListInterface) {
        this.listener = listener
    }
}
