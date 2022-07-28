package com.teampome.pome.presentation.remind

import android.content.ContentValues.TAG
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.teampome.pome.R
import com.teampome.pome.data.remote.response.ResponseRemindData
import com.teampome.pome.databinding.ItemRemindConsumeBinding

class RemindConsumeAdapter :
    RecyclerView.Adapter<RemindConsumeAdapter.RemindConsumeViewHolder>() {
    var clickedRecordId = -1
    var remindConsumeList = mutableListOf<ResponseRemindData>()
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
        holder.bind(remindConsumeList[position])
        holder.itemView.setOnClickListener {
            clickedRecordId = remindConsumeList[position].id
            listener.onClick(it, position, clickedRecordId)
        }
    }

    class RemindConsumeViewHolder(
        private val binding: ItemRemindConsumeBinding
    ) : RecyclerView.ViewHolder(binding.root) {
        fun bind(friendsConsumeData: ResponseRemindData) {
            binding.tvRemindDate.text = friendsConsumeData.timestamp
            binding.tvRemindContent.text = friendsConsumeData.content
            binding.tvRemindPrice.text = friendsConsumeData.amount.toString()
            binding.tvRemindTag.text = friendsConsumeData.goalMessage

            when (friendsConsumeData.startEmotion) {
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
            when (friendsConsumeData.endEmotion) {
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
            for (i in 0 until friendsConsumeData.reactions.size) {
                if (i == 1) {
                    binding.ivEmotion1.visibility = View.VISIBLE
                    if (friendsConsumeData.reactions[i] == 1) {
                        binding.ivEmotion1.setImageResource(R.drawable.ic_emoji_happy_mint_28)
                    } else if (friendsConsumeData.reactions[i] == 2) {
                        binding.ivEmotion1.setImageResource(R.drawable.ic_emoji_smile_mint_28)
                    } else if (friendsConsumeData.reactions[i] == 3) {
                        binding.ivEmotion1.setImageResource(R.drawable.ic_emoji_funny_mint_28)
                    } else if (friendsConsumeData.reactions[i] == 4) {
                        binding.ivEmotion1.setImageResource(R.drawable.ic_emoji_flex_mint_28)
                    } else if (friendsConsumeData.reactions[i] == 5) {
                        binding.ivEmotion1.setImageResource(R.drawable.ic_emoji_what_mint_28)
                    } else if (friendsConsumeData.reactions[i] == 6) {
                        binding.ivEmotion1.setImageResource(R.drawable.ic_emoji_sad_mint_28)
                    } else { //0번이면
                        binding.ivEmotion1.visibility = View.GONE
                    }
                } else {
                    binding.ivEmotion2.visibility = View.VISIBLE
                    if (friendsConsumeData.plusCount == 0) {
                        binding.tvReactPlusCount.visibility=View.INVISIBLE
                        if (friendsConsumeData.reactions[i] == 1) {
                            binding.ivEmotion2.setImageResource(R.drawable.ic_emoji_happy_mint_28)
                        } else if (friendsConsumeData.reactions[i] == 2) {
                            binding.ivEmotion2.setImageResource(R.drawable.ic_emoji_smile_mint_28)
                        } else if (friendsConsumeData.reactions[i] == 3) {
                            binding.ivEmotion2.setImageResource(R.drawable.ic_emoji_funny_mint_28)
                        } else if (friendsConsumeData.reactions[i] == 4) {
                            binding.ivEmotion2.setImageResource(R.drawable.ic_emoji_flex_mint_28)
                        } else if (friendsConsumeData.reactions[i] == 5) {
                            binding.ivEmotion2.setImageResource(R.drawable.ic_emoji_what_mint_28)
                        } else if (friendsConsumeData.reactions[i] == 6) {
                            binding.ivEmotion2.setImageResource(R.drawable.ic_emoji_sad_mint_28)
                        } else { //0번이면
                            binding.ivEmotion2.visibility = View.GONE
                        }
                    } else {
                        binding.tvReactPlusCount.visibility=View.VISIBLE
                        if (friendsConsumeData.reactions[i] == 1) {
                            binding.ivEmotion2.setImageResource(R.drawable.ic_emoji_happy_mint_28_overlay)
                        } else if (friendsConsumeData.reactions[i] == 2) {
                            binding.ivEmotion2.setImageResource(R.drawable.ic_emoji_smile_mint_28_overlay)
                        } else if (friendsConsumeData.reactions[i] == 3) {
                            binding.ivEmotion2.setImageResource(R.drawable.ic_emoji_funny_mint_28_overlay)
                        } else if (friendsConsumeData.reactions[i] == 4) {
                            binding.ivEmotion2.setImageResource(R.drawable.ic_emoji_flex_mint_28_overlay)
                        } else if (friendsConsumeData.reactions[i] == 5) {
                            binding.ivEmotion2.setImageResource(R.drawable.ic_emoji_what_mint_28_overlay)
                        } else if (friendsConsumeData.reactions[i] == 6) {
                            binding.ivEmotion2.setImageResource(R.drawable.ic_emoji_sad_mint_28_overlay)
                        } else { //0번이면
                            binding.ivEmotion2.visibility = View.GONE
                        }
                    }

                }
            }
            binding.ivEmotion2.bringToFront()
            binding.ivEmotion1.bringToFront()
            binding.tvReactPlusCount.bringToFront()
        }
    }

    interface ReactionClickListener {
        fun onClick(data: View, pos: Int, id: Int)
    }

    fun setReactionClickListener(listener: ReactionClickListener) {
        this.listener = listener
    }

    override fun getItemCount(): Int = remindConsumeList.size
}