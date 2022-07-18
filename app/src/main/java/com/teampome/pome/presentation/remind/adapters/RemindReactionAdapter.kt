package com.teampome.pome.presentation.remind.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.teampome.pome.R
import com.teampome.pome.databinding.ItemRemindReactBinding
import com.teampome.pome.presentation.remind.RemindReactionData

class RemindReactionAdapter :
    ListAdapter<RemindReactionData, RemindReactionAdapter.RemindReactionHolder>(
        DIFFUTIL
    ) {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RemindReactionHolder {
        val binding = ItemRemindReactBinding.inflate(
            LayoutInflater.from(parent.context),
            parent, false
        )
        return RemindReactionHolder(binding)
    }

    override fun onBindViewHolder(holder: RemindReactionHolder, position: Int) {
        holder.bind(getItem(position))
    }

    class RemindReactionHolder(private val binding: ItemRemindReactBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(remindReactionData: RemindReactionData){
            when(remindReactionData.emoji){
                1-> {
                    binding.ivRemindReaction.setImageResource(R.drawable.ic_emoji_mint_heart_fiter_54)
                }
                2->{
                    binding.ivRemindReaction.setImageResource(R.drawable.ic_emoji_mint_smile_react_54)
                }
                3-> {
                    binding.ivRemindReaction.setImageResource(R.drawable.ic_emoji_mint_funny_react_54)
                }
                4-> {
                    binding.ivRemindReaction.setImageResource(R.drawable.ic_emoji_mint_flex_react_54)
                }
                5->{
                    binding.ivRemindReaction.setImageResource(R.drawable.ic_emoji_mint_what_fiter_54)
                }
                6-> {
                    binding.ivRemindReaction.setImageResource(R.drawable.ic_emoji_mint_sad_filter_54)
                }
            }
            binding.tvRemindReaction.text=remindReactionData.name

        }

    }

    companion object {
        val DIFFUTIL = object : DiffUtil.ItemCallback<RemindReactionData>() {
            override fun areItemsTheSame(
                oldItem: RemindReactionData,
                newItem: RemindReactionData
            ): Boolean = oldItem.name == newItem.name

            override fun areContentsTheSame(
                oldItem: RemindReactionData,
                newItem: RemindReactionData
            ): Boolean = oldItem == newItem

        }
    }
}