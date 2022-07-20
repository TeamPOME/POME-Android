package com.teampome.pome.presentation.record

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.teampome.pome.R
import com.teampome.pome.data.remote.response.ResponseRecordsLook
import com.teampome.pome.databinding.ItemRecordListBinding

class RecordAdapter : ListAdapter<ResponseRecordsLook, RecordAdapter.RecordViewHolder>(DiffUtil) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecordViewHolder {
        val binding =
            ItemRecordListBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return RecordViewHolder(binding)
    }

    override fun onBindViewHolder(holder: RecordViewHolder, position: Int) {
        holder.onBind(currentList[position])
    }

    class RecordViewHolder(
        private val binding: ItemRecordListBinding
    ) : RecyclerView.ViewHolder(binding.root) {
        fun onBind(data: ResponseRecordsLook) {
            binding.tvDate.text = data.date
            binding.tvAmount.text = data.amount.toString()
            binding.tvDescription.text = data.content
            when (data.startEmotion) {
                1 -> binding.ivFirstemotion.setImageResource(R.drawable.ic_emoji_happy_mint_56_with_background)
                2 -> binding.ivFirstemotion.setImageResource(R.drawable.ic_emoji_what_mint_56_with_background)
                3 -> binding.ivFirstemotion.setImageResource(R.drawable.ic_emoji_sad_mint_56_with_background)
            }
            binding.ivSecondemotion.setImageResource(R.drawable.ic_question_with_background)
        }
    }

    companion object {
        val DiffUtil = object : DiffUtil.ItemCallback<ResponseRecordsLook>() {
            override fun areItemsTheSame(oldItem: ResponseRecordsLook, newItem: ResponseRecordsLook): Boolean {
                return oldItem.content == newItem.content
            }

            override fun areContentsTheSame(oldItem: ResponseRecordsLook, newItem: ResponseRecordsLook): Boolean {
                return oldItem == newItem
            }
        }
    }


}