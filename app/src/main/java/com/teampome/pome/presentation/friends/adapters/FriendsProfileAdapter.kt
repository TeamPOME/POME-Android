package com.teampome.pome.presentation.friends.adapters

import android.graphics.Color
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import coil.load
import com.teampome.pome.R
import com.teampome.pome.data.remote.response.ResponseFriendsProflie
import com.teampome.pome.databinding.ItemFriendProfileEmptyBinding
import com.teampome.pome.databinding.ItemFriendProfileListBinding
import com.teampome.pome.presentation.friends.FriendsProfileData

class FriendsProfileAdapter :
    RecyclerView.Adapter<ViewHolder>() {
    val friendsProfileList = mutableListOf<FriendsProfileData.friends>()
    val friendsReponseList = mutableListOf<ResponseFriendsProflie>()

    private lateinit var empty: ItemFriendProfileEmptyBinding
    private lateinit var full: ItemFriendProfileListBinding

    private lateinit var listener: FriendsListClickInterface
    var selectedItemPosition = -1
    var isEmpty = false
    var wholeSelected = true

    override fun getItemViewType(position: Int): Int = when {
        position <= 1 -> {
            VIEW_EMPTY
        }
        else -> {
            VIEW_FULL
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        when (viewType) {
            VIEW_FULL -> {
                full = ItemFriendProfileListBinding.inflate(
                    LayoutInflater.from(parent.context),
                    parent,
                    false
                )
                return FriendsProfileListViewHolder(full)
            }
            else -> {
                empty = ItemFriendProfileEmptyBinding.inflate(
                    LayoutInflater.from(parent.context),
                    parent,
                    false
                )
                return FriendsProfileEmptyViewHolder(empty)
            }
        }
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        when (holder) {
            is FriendsProfileListViewHolder -> {
                val profile_item = friendsProfileList
                holder.bind(profile_item[position])
                holder.itemView.setOnClickListener {
                    selectedItemPosition = position
                    wholeSelected = false
                    notifyDataSetChanged()
                }
                if (selectedItemPosition == position) {
                    holder.profile_name.setTextColor(Color.BLACK)
                } else {
                    holder.profile_name.setTextColor(Color.GRAY)
                }
            }
            is FriendsProfileEmptyViewHolder -> {
                holder.bind(friendsProfileList)
                holder.itemView.setOnClickListener {
                    wholeSelected = true
                    selectedItemPosition = -1
                    notifyDataSetChanged()
                }
                if (wholeSelected) {
                    holder.whole_text.setTextColor(Color.BLACK)
                } else {
                    holder.whole_text.setTextColor(Color.GRAY)
                }

            }
            else -> {
                //여기는 로그 띄우기
            }
        }
    }

    override fun getItemCount(): Int = friendsProfileList.size

    class FriendsProfileListViewHolder(private val binding: ItemFriendProfileListBinding) :
        RecyclerView.ViewHolder(binding.root) {
        val profile_name = binding.tvFriendname
        fun bind(friendsInfo: FriendsProfileData.friends) {
            binding.tvFriendname.text = friendsInfo.name
            binding.ivFriendprofile.load(friendsInfo.image)
        }
    }

    class FriendsProfileEmptyViewHolder(private val binding: ItemFriendProfileEmptyBinding) :
        RecyclerView.ViewHolder(binding.root) {
        val whole_text = binding.tvWhole
        fun bind(
            list: List<FriendsProfileData.friends>
        ) {
            if (list.size == 1) {
                binding.ivFriendprofileWhole.setImageResource(R.drawable.ic_friend_profile_empty)
            } else {
                binding.ivFriendprofileWhole.setImageResource(R.drawable.ic_friend_profile_full)
            }
        }
    }

    companion object {
        const val VIEW_FULL = 0
        const val VIEW_EMPTY = 1
    }

    interface FriendsListClickInterface {
        fun onProfileListClick(v: View, data: FriendsProfileData, pos: Int)
    }

    fun setOnProfileListClickListener(listener: FriendsListClickInterface) {
        this.listener = listener
    }
}


