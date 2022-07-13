package com.teampome.pome.presentation.friends

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.teampome.pome.R
import com.teampome.pome.databinding.ItemFriendConsumeListBinding

class FriendsConsumeAdapter(private val emojiClick: (Int) -> (Unit)) :
    ListAdapter<FriendsConsumeData, FriendsConsumeAdapter.FriendsConsumeViewHolder>(
        DIFFUTIL
    ) {

    private lateinit var listener: FriendsConsumeListInterface
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FriendsConsumeViewHolder {
        val binding = ItemFriendConsumeListBinding.inflate(
            LayoutInflater.from(parent.context), parent, false
        )
        return FriendsConsumeViewHolder(binding, emojiClick)
    }

    override fun onBindViewHolder(holder: FriendsConsumeViewHolder, position: Int) {
        holder.bind(getItem(position))
        holder.itemView.setOnClickListener {
            listener.onClick(it, position, false)
        }
        holder.addEmojiButton.setOnClickListener {
            listener.onClick(it, position, true)
        }
        holder.setEmoji(getEmojiPosition())
    }

    class FriendsConsumeViewHolder(
        private val binding: ItemFriendConsumeListBinding,
        private val emojiClick: (Int) -> (Unit)
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
            when (position) {
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

    var emoji_position: Int = -1
    fun setEmojiPosition(pos: Int) {
        emoji_position = pos
    }//받은 값을 설정

    fun getEmojiPosition(): Int = emoji_position
}
