package com.teampome.pome.presentation.remind

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.teampome.pome.databinding.ItemRemindConsumeBinding
import com.teampome.pome.presentation.friends.FriendsConsumeData

class RemindConsumeAdapter :
    ListAdapter<FriendsConsumeData, RemindConsumeAdapter.RemindConsumeViewHolder>(
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

        fun bind(friendsConsumeData: FriendsConsumeData) {
            binding.tvRemindDate.text = friendsConsumeData.date
            binding.tvRemindContent.text = friendsConsumeData.description
            binding.tvRemindPrice.text = friendsConsumeData.price.toString()
            binding.tvRemindTag.text = friendsConsumeData.tag
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

    interface ReactionClickListener{
        fun onClick(data: View, pos:Int)
    }
    fun setReactionClickListener(listener: ReactionClickListener){
        this.listener=listener
    }
}