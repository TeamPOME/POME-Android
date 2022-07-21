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
    val friendsProfileList = mutableListOf<FriendsProfileData>()
    val friendsReponseList = mutableListOf<ResponseFriendsProflie>()

    private lateinit var empty: ItemFriendProfileEmptyBinding
    private lateinit var full: ItemFriendProfileListBinding
    var clickedId=0
    private lateinit var listener: FriendsListClickInterface
    var selectedItemPosition = -1
    var wholeSelected = true

    override fun getItemViewType(position: Int): Int = when (friendsReponseList[position].id==-1) {
        true -> {
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
                holder.bind(friendsReponseList[position])
                holder.itemView.setOnClickListener {
                    selectedItemPosition = position
                    wholeSelected = false
                    clickedId=friendsReponseList[position].id
                    listener.onProfileListClick(clickedId)
                    notifyDataSetChanged()
                }
                if (selectedItemPosition == position) {
                    holder.profile_name.setTextColor(Color.BLACK)
                } else {
                    holder.profile_name.setTextColor(Color.GRAY)
                }
            }
            is FriendsProfileEmptyViewHolder -> {
                holder.bind(friendsReponseList)
                holder.itemView.setOnClickListener {
                    wholeSelected = true
                    selectedItemPosition = -1
                    clickedId=0
                    listener.onProfileListClick(clickedId)
                    notifyDataSetChanged()
                }
                if (wholeSelected) {
                    holder.image.setImageResource(R.drawable.ic_friend_profile_empty)
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

    override fun getItemCount(): Int = friendsReponseList.size

    class FriendsProfileListViewHolder(private val binding: ItemFriendProfileListBinding) :
        RecyclerView.ViewHolder(binding.root) {
        val profile_name = binding.tvFriendname
        fun bind(friendsResponseList: ResponseFriendsProflie) {

            binding.tvFriendname.text = friendsResponseList.nickname
            binding.ivFriendprofile.load(friendsResponseList.profileImage)
        }
    }

    class FriendsProfileEmptyViewHolder(private val binding: ItemFriendProfileEmptyBinding) :
        RecyclerView.ViewHolder(binding.root) {
        val whole_text = binding.tvWhole
        val image=binding.ivFriendprofileWhole
        fun bind(
            list: MutableList<ResponseFriendsProflie>
        ) {
            if (list.size == 1) {
                binding.ivFriendprofileWhole.setImageResource(R.drawable.ic_friend_profile_empty)
            } else {
                binding.ivFriendprofileWhole.setImageResource(R.drawable.ic_friend_profile_full)
            }
            binding.tvWhole.text = list[0].nickname
        }
    }

    companion object {
        const val VIEW_FULL = 0
        const val VIEW_EMPTY = 1
    }

    interface FriendsListClickInterface {
        fun onProfileListClick(pos: Int)
    }

    fun setOnProfileListClickListener(listener: FriendsListClickInterface) {
        this.listener = listener
    }
}


