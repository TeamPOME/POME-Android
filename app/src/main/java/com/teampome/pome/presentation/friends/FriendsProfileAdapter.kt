package com.teampome.pome.presentation.friends

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import com.teampome.pome.databinding.ItemFriendProfileEmptyBinding
import com.teampome.pome.databinding.ItemFriendProfileListBinding

class FriendsProfileAdapter :
    RecyclerView.Adapter<RecyclerView.ViewHolder>() {
    val friendsProfileList = mutableListOf<FriendsProfileInterface>()

    override fun getItemViewType(position: Int): Int = when (friendsProfileList[position]) {
        is FriendsProfileData -> {
            VIEW_FULL
        }
        else -> {
            VIEW_EMPTY
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        when (viewType) {
            VIEW_FULL -> {
                val binding = ItemFriendProfileListBinding.inflate(
                    LayoutInflater.from(parent.context),
                    parent,
                    false
                )
                return FriendsProfileListViewHolder(binding)
            }

            else -> {
                val binding = ItemFriendProfileEmptyBinding.inflate(
                    LayoutInflater.from(parent.context),
                    parent,
                    false
                )
                return FriendsProfileEmptyViewHolder(binding)
            }
        }
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        when(holder){
            is FriendsProfileListViewHolder -> {
                holder.bind(friendsProfileList[position] as FriendsProfileData)
            }
            is FriendsProfileEmptyViewHolder ->{
                holder.bind(friendsProfileList[position] as FriendsProfileWholeData)

            }
            else -> {
                //여기는 로그 띄우기
            }
        }

    }

    override fun getItemCount(): Int = friendsProfileList.size
    class FriendsProfileListViewHolder(private val binding: ItemFriendProfileListBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(friendProfileData: FriendsProfileData) {
            binding.tvFriendname.text = friendProfileData.name
            //이미지 넣기
        }


    }

    class FriendsProfileEmptyViewHolder(private val binding: ItemFriendProfileEmptyBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(friendsProfileWholeData: FriendsProfileWholeData) {
            //딱히 없음
            //binding.tvWhole.text="전체"

        }
    }

    companion object {
        const val VIEW_FULL = 0
        const val VIEW_EMPTY = 1
    }

}


