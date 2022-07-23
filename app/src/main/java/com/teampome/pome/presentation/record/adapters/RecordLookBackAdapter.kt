package com.teampome.pome.presentation.record.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.teampome.pome.R
import com.teampome.pome.data.remote.response.RecordsBack
import com.teampome.pome.data.remote.response.ResponseRecordsLookBack
import com.teampome.pome.databinding.ItemRecordListBinding
import com.teampome.pome.databinding.ItemRecordLookListBinding
import com.teampome.pome.presentation.record.RecordData

class RecordLookBackAdapter :
    ListAdapter<RecordsBack, RecordLookBackAdapter.RecordLookBackViewHolder>(DiffUtil) {

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): RecordLookBackViewHolder {
        val binding =
            ItemRecordLookListBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return RecordLookBackViewHolder(binding)
    }

    override fun onBindViewHolder(holder: RecordLookBackViewHolder, position: Int) {
        holder.onBind(currentList[position])
    }

    class RecordLookBackViewHolder(
        private val binding: ItemRecordLookListBinding
    ) : RecyclerView.ViewHolder(binding.root) {
        fun onBind(data: RecordsBack) {
            binding.recordsback = data
            when (data.startEmotion) {
                1 -> binding.ivFirstemotion.setImageResource(R.drawable.ic_emoji_happy_mint_56_with_background)
                2 -> binding.ivFirstemotion.setImageResource(R.drawable.ic_emoji_what_mint_56_with_background)
                3 -> binding.ivFirstemotion.setImageResource(R.drawable.ic_emoji_sad_mint_56_with_background)
            }
        }
    }

    companion object {
        val DiffUtil = object : DiffUtil.ItemCallback<RecordsBack>() {
            override fun areItemsTheSame(oldItem: RecordsBack, newItem: RecordsBack): Boolean {
                return oldItem.id == newItem.id
            }

            override fun areContentsTheSame(oldItem: RecordsBack, newItem: RecordsBack): Boolean {
                return oldItem == newItem
            }
        }
    }
}