package com.teampome.pome.presentation.record

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.teampome.pome.databinding.ItemRecordListBinding

class RecordAdapter : ListAdapter<RecordData, RecordAdapter.RecordViewHolder>(DiffUtil) {

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