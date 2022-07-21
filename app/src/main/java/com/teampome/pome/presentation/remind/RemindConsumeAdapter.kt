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
    var clickedRecordId=-1
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
        clickedRecordId=getItem(position).id
        holder.bind(getItem(position))
        holder.itemView.setOnClickListener {
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
            for (i in 0 until friendsConsumeData.reactions.size) {
                // 무조건 3개가 날라옴..  뒤에 애들이 0인 경우
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
                    if(friendsConsumeData.plusCount==0){
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
                            binding.tvReactPlusCount.visibility=View.INVISIBLE
                        }
                    }else{ //추가 반응이 있는 경우
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
                        binding.tvReactPlusCount.text="+"+friendsConsumeData.plusCount.toString()
                    }

                }
            }
            binding.ivEmotion2.bringToFront()
            binding.ivEmotion1.bringToFront()
            binding.tvReactPlusCount.bringToFront()

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
        fun onClick(data: View, pos:Int, id:Int)
    }
    fun setReactionClickListener(listener: ReactionClickListener){
        this.listener=listener
    }
}