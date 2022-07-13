package com.teampome.pome.presentation.friends.adapters

import android.view.LayoutInflater
import android.view.View
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
    private var listener: FriendsListClickInterface? = null

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

    val itemListBinding: ItemFriendProfileListBinding? = null


    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        when (holder) {
            is FriendsProfileListViewHolder -> {
                holder.bind(friendsProfileList[position] as FriendsProfileData)

                holder.itemView.setOnClickListener {
                    holder.initprofileColor(R.color.pome_disabled_mint)
                    holder.profileClick(position, R.color.pome_red)

                }
            }
            is FriendsProfileEmptyViewHolder -> {
                holder.bind(
                    friendsProfileList[position] as FriendsProfileWholeData,
                    friendsProfileList as MutableList<FriendsProfileData>
                )
                holder.itemView.setOnClickListener {
                    holder.emptyClick(friendsProfileList[position] as FriendsProfileWholeData)
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

        fun bind(friendProfileData: FriendsProfileData) {
            binding.tvFriendname.text = friendProfileData.name
            //이미지 넣기
        }

        fun profileClick(position: Int, color: Int) {
            //서버 연결 시 클릭한 프로필에 해당한 데이터만 가져올 함수
            binding.tvFriendname.setTextColor(color)
        }

        fun initprofileColor(color: Int) {
            binding.tvFriendname.setTextColor(color)
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

    interface FriendsListClickInterface {
        fun onProfileListClick(v: View, data: FriendsProfileData, pos: Int)
    }

    fun setOnProfileListClickListener(listener: FriendsListClickInterface) {
        this.listener = listener
    }

    private fun profileClick(
        viewHolder: ViewHolder,
        binding: ItemFriendProfileListBinding,
        data: FriendsProfileInterface,
        position: Int
    ) {


    }


}


