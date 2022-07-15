package com.teampome.pome.presentation.mypage


import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.teampome.pome.databinding.ItemListMarshmellowBinding

class MarshMellowAdapter : ListAdapter<MarshMellowData, MarshMellowAdapter.MarshMellowViewHolder>(
    DIFFUTIL
) {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MarshMellowViewHolder {
        val binding =
            ItemListMarshmellowBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return MarshMellowViewHolder(binding)

    }

    override fun onBindViewHolder(holder: MarshMellowViewHolder, position: Int) {
        holder.onBind(getItem(position))
    }

    class MarshMellowViewHolder(
        private val binding: ItemListMarshmellowBinding
    ) : RecyclerView.ViewHolder(binding.root) {
        fun onBind(marshMellowData: MarshMellowData) {
            binding.marshmellow = marshMellowData
        }
    }

    companion object {
        val DIFFUTIL = object : DiffUtil.ItemCallback<MarshMellowData>() {
            override fun areItemsTheSame(
                oldItem: MarshMellowData,
                newItem: MarshMellowData
            ): Boolean {
                return oldItem.label == newItem.label
            }

            override fun areContentsTheSame(
                oldItem: MarshMellowData,
                newItem: MarshMellowData
            ): Boolean {
                return oldItem == newItem
            }
        }
    }
}