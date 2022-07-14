package com.teampome.pome.presentation.friends.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import com.teampome.pome.R
import com.teampome.pome.databinding.ItemFriendProfileEmptyBinding
import com.teampome.pome.databinding.ItemFriendProfileListBinding
import com.teampome.pome.presentation.friends.FriendsProfileData
import com.teampome.pome.presentation.friends.FriendsProfileInterface
import com.teampome.pome.presentation.friends.FriendsProfileWholeData

class FriendsProfileAdapter :
    RecyclerView.Adapter<RecyclerView.ViewHolder>() {
    val friendsProfileList = mutableListOf<FriendsProfileInterface>()
    private lateinit var empty_binding: ItemFriendProfileEmptyBinding
    private lateinit var full_binding: ItemFriendProfileListBinding
    private var holderList = listOf<ItemFriendProfileListBinding>()
    private lateinit var holder: RecyclerView.ViewHolder
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
                full_binding = ItemFriendProfileListBinding.inflate(
                    LayoutInflater.from(parent.context),
                    parent,
                    false
                )
                holder = FriendsProfileListViewHolder(full_binding)
                return FriendsProfileListViewHolder(full_binding)
            }
            else -> {
                empty_binding = ItemFriendProfileEmptyBinding.inflate(
                    LayoutInflater.from(parent.context),
                    parent,
                    false
                )
                return FriendsProfileEmptyViewHolder(empty_binding)
            }
        }
    }

    val itemListBinding: ItemFriendProfileListBinding? = null

    var old_position = -1

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {

        when (holder) {
            is FriendsProfileListViewHolder -> {
                holder.bind(friendsProfileList[position] as FriendsProfileData)
                holder.itemView.setOnClickListener {
                }
            }
            is FriendsProfileEmptyViewHolder -> {
                holder.bind(
                    friendsProfileList[position] as FriendsProfileWholeData,
                    friendsProfileList as MutableList<FriendsProfileData>
                )
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
        fun bind(
            friendsProfileWholeData: FriendsProfileWholeData,
            list: MutableList<FriendsProfileData>
        ) {
            if (list.size == 1) {
                binding.ivFriendprofileWhole.setImageResource(R.drawable.ic_friend_profile_empty)
            } else {
                binding.ivFriendprofileWhole.setImageResource(R.drawable.ic_friend_profile_full)
            }
        }

        fun emptyClick(friendsProfileWholeData: FriendsProfileWholeData) {
            //전체를 클릭한 경우
        }
    }

    companion object {
        const val VIEW_FULL = 0
        const val VIEW_EMPTY = 1
    }
}


