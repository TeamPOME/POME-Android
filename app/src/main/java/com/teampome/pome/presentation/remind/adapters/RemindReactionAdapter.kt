package com.teampome.pome.presentation.remind.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.teampome.pome.R
import com.teampome.pome.data.remote.response.ReactInfo
import com.teampome.pome.databinding.ItemRemindReactBinding
import com.teampome.pome.presentation.remind.RemindReactionData

class RemindReactionAdapter :
    ListAdapter<ReactInfo, RemindReactionAdapter.RemindReactionHolder>(
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
        fun bind(remindReactionData: ReactInfo){
            when(remindReactionData.emotion){
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
            binding.tvRemindReaction.text=remindReactionData.nickname

        }

    }

    companion object {
        val DIFFUTIL = object : DiffUtil.ItemCallback<ReactInfo>() {
            override fun areItemsTheSame(
                oldItem: ReactInfo,
                newItem: ReactInfo
            ): Boolean = oldItem.emotion == newItem.emotion

            override fun areContentsTheSame(
                oldItem: ReactInfo,
                newItem: ReactInfo
            ): Boolean = oldItem == newItem

        }
    }
}