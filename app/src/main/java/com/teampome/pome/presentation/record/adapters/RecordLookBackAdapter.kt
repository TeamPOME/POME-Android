package com.teampome.pome.presentation.record.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.teampome.pome.databinding.ItemRecordListBinding
import com.teampome.pome.presentation.record.RecordData

class RecordLookBackAdapter :
    ListAdapter<RecordData, RecordLookBackAdapter.RecordLookBackViewHolder>(DiffUtil) {

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): RecordLookBackViewHolder {
        val binding =
            ItemRecordListBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return RecordLookBackViewHolder(binding)
    }

    override fun onBindViewHolder(holder: RecordLookBackViewHolder, position: Int) {
        holder.onBind(currentList[position])
    }

    class RecordLookBackViewHolder(
        private val binding: ItemRecordListBinding
    ) : RecyclerView.ViewHolder(binding.root) {
        fun onBind(data: RecordData) {
            binding.tvDate.text = data.date
            binding.tvAmount.text = data.amount
            binding.tvDescription.text = data.description
        }
    }

    companion object {
        val DiffUtil = object : DiffUtil.ItemCallback<RecordData>() {
            override fun areItemsTheSame(oldItem: RecordData, newItem: RecordData): Boolean {
                return oldItem.id == newItem.id
            }

            override fun areContentsTheSame(oldItem: RecordData, newItem: RecordData): Boolean {
                return oldItem == newItem
            }
        }
    }
}