package com.teampome.pome.presentation.remind

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.teampome.pome.R
import com.teampome.pome.data.remote.response.ResponseRemindData
import com.teampome.pome.databinding.ItemRemindConsumeBinding
import com.teampome.pome.presentation.friends.FriendsConsumeData
import com.teampome.pome.util.setImage

class RemindConsumeAdapter :
    ListAdapter<ResponseRemindData, RemindConsumeAdapter.RemindConsumeViewHolder>(
        DIFFUTIL
    ) {
    private lateinit var listener: ReactionClickListener
    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): RemindConsumeViewHolder {
        val binding = ItemRemindConsumeBinding.inflate(
            LayoutInflater.from(parent.context),
            parent, false
        )
        return RemindConsumeViewHolder(binding)
    }

    override fun onBindViewHolder(
        holder: RemindConsumeViewHolder,
        position: Int
    ) {
        holder.bind(getItem(position))
        holder.reactionButton.setOnClickListener {
            listener.onClick(it, position)
        }
    }

    class RemindConsumeViewHolder(
        private val binding: ItemRemindConsumeBinding
    ) : RecyclerView.ViewHolder(binding.root) {
        val reactionButton=binding.ivReactions

        fun bind(friendsConsumeData: ResponseRemindData) {
            binding.tvRemindDate.text = friendsConsumeData.timestamp
            binding.tvRemindContent.text = friendsConsumeData.content
            binding.tvRemindPrice.text = friendsConsumeData.amount.toString()
            binding.tvRemindTag.text = friendsConsumeData.goalMessage

            when(friendsConsumeData.startEmotion){
                1 -> {
                    binding.ivFirstEmotion.setImageResource(R.drawable.ic_emoji_mint_heart)
                }
                2 -> {
                    binding.ivFirstEmotion.setImageResource(R.drawable.ic_emoji_mint_what)
                }
                3 -> {
                    binding.ivFirstEmotion.setImageResource(R.drawable.ic_emoji_mint_sad)
                }
                else -> {
                    binding.ivFirstEmotion.setImageResource(R.drawable.ic_question_backgorund_34)
                }
            }
            when(friendsConsumeData.endEmotion){
                1 -> {
                    binding.ivSecondEmotion.setImageResource(R.drawable.ic_emoji_happy_pink_34)
                }
                2 -> {
                    binding.ivSecondEmotion.setImageResource(R.drawable.ic_emoji_what_pink_34)
                }
                3 -> {
                    binding.ivSecondEmotion.setImageResource(R.drawable.ic_emoji_sad_pink_34)
                }
                else -> {
                    binding.ivSecondEmotion.setImageResource(R.drawable.ic_question_backgorund_34)
                }
            }
        }

    }

    companion object {
        val DIFFUTIL = object : DiffUtil.ItemCallback<ResponseRemindData>() {
            override fun areItemsTheSame(
                oldItem: ResponseRemindData,
                newItem: ResponseRemindData
            ): Boolean = oldItem.content == newItem.content

            override fun areContentsTheSame(
                oldItem: ResponseRemindData,
                newItem: ResponseRemindData
            ): Boolean = oldItem == newItem

        }

    }

    interface ReactionClickListener{
        fun onClick(data: View, pos:Int)
    }
    fun setReactionClickListener(listener: ReactionClickListener){
        this.listener=listener
    }
}