package com.teampome.pome.presentation.friends.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.teampome.pome.R
import com.teampome.pome.databinding.ItemFriendsEmojiBinding
import com.teampome.pome.presentation.friends.FriendsConsumeData

class FriendsReactAdapter :
    RecyclerView.Adapter<FriendsReactAdapter.FriendsEmojiViewHolder>() {
    private lateinit var binding: ItemFriendsEmojiBinding
    val reactList = mutableListOf<FriendsConsumeData>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FriendsEmojiViewHolder {
        binding = ItemFriendsEmojiBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,
            false
        )
        return FriendsEmojiViewHolder(binding)
    }

    override fun onBindViewHolder(holder: FriendsEmojiViewHolder, position: Int) {
        holder.bind(reactList[position])
    }

    override fun getItemCount(): Int = reactList.size

    class FriendsEmojiViewHolder(private val binding: ItemFriendsEmojiBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(
            friendsEmojiData: FriendsConsumeData
        ) {
            val reaction = friendsEmojiData.reaction
            //reaction정보를 int로 얻어서 후에 연결

            when (reaction[adapterPosition]) {
                0 -> {
                    binding.ivFriendsEmoji.setImageResource(R.drawable.ic_emoji_happy_mint_28)
                }
                1 -> {
                    binding.ivFriendsEmoji.setImageResource(R.drawable.ic_emoji_smile_mint_28)
                }
                2 -> {
                    binding.ivFriendsEmoji.setImageResource(R.drawable.ic_emoji_funny_mint_28)
                }
                3 -> {
                    binding.ivFriendsEmoji.setImageResource(R.drawable.ic_emoji_flex_mint_28)
                }
                4 -> {
                    binding.ivFriendsEmoji.setImageResource(R.drawable.ic_emoji_what_mint_28)
                }
                5 -> {
                    binding.ivFriendsEmoji.setImageResource(R.drawable.ic_emoji_sad_mint_28)
                }
                else -> {

                }
            }
            binding.ivFriendsEmoji.setImageResource(R.drawable.ic_emoji_flex_mint_28)
        }
    }

}