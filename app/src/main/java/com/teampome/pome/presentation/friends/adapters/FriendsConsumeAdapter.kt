package com.teampome.pome.presentation.friends.adapters

import android.content.ContentValues.TAG
import android.content.Context
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.recyclerview.widget.RecyclerView
import com.teampome.pome.R
import com.teampome.pome.data.remote.response.ResponseFriendsAll
import com.teampome.pome.databinding.ItemFriendConsumeListBinding
import com.teampome.pome.presentation.friends.FriendsConsumeData

class FriendsConsumeAdapter(val contextT: Context) :
    RecyclerView.Adapter<FriendsConsumeAdapter.FriendsConsumeViewHolder>() {
    var context = contextT
    var clicked_position = -1
    private lateinit var listener: FriendsConsumeListInterface
    var friendConsumeList = mutableListOf<ResponseFriendsAll>()
    var getEmoji = -1
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FriendsConsumeViewHolder {
        val binding = ItemFriendConsumeListBinding.inflate(
            LayoutInflater.from(parent.context), parent, false
        )
        return FriendsConsumeViewHolder(binding, context)
    }

    override fun onBindViewHolder(holder: FriendsConsumeViewHolder, position: Int) {
        holder.itemView.setOnClickListener {
            listener.onClick(it, position, false)
        }
        holder.bind(friendConsumeList[position])
        holder.addEmojiButton.setOnClickListener {
            listener.onClick(it, position, true)
        }
        if (clicked_position == position && getEmoji != -1) {
            holder.setEmoji(getEmoji)
        }
    }

    fun getEmojiPosition(position: Int, list_position: Int) {
        if (position != -1)
            clicked_position = list_position
        getEmoji = position
  //      notifyDataSetChanged()
        notifyItemChanged(list_position)
    }

//    fun changeItem(item: FriendsConsumeData, position: Int) {
//        //여기서 position은 emoji_positon임;
//        getEmoji=position
//        Log.d(TAG,"FriendsConsumeAdapter - changeItem() called changeItem으로 잘 넘어옴~")
//        val newList = currentList.mapIndexed { index, friendsConsumeData ->
//            Log.d(TAG,"FriendsConsumeAdapter - changeItem() called item=$item")
//            if (index == position) item
//
//            else friendsConsumeData
//        }.toList()
//        //submitList(newList)
//    }//post한 후 실행할 함수

    class FriendsConsumeViewHolder(
        private val binding: ItemFriendConsumeListBinding,
        val context: Context
    ) :
        RecyclerView.ViewHolder(binding.root) {
        val addEmojiButton = binding.ivAddemotion

        fun bind(friendsConsumeData: ResponseFriendsAll) {
            binding.apply{
                tvFriendname.text = friendsConsumeData.nickname
                tvFrienddate.text = friendsConsumeData.timestamp
                tvFrienddes.text = friendsConsumeData.content
                tvFriendprice.text = friendsConsumeData.amount.toString()
                tvFriendtag.text = friendsConsumeData.goalMessage
            }

            when(friendsConsumeData.startEmotion){
                1-> {
                    binding.ivFriendFirstemotion.setImageResource(R.drawable.ic_emoji_mint_heart)
                }
                2-> {
                    binding.ivFriendFirstemotion.setImageResource(R.drawable.ic_emoji_mint_what)
                }
                3-> {
                    binding.ivFriendFirstemotion.setImageResource(R.drawable.ic_emoji_mint_sad)
                }
            }
            when(friendsConsumeData.endEmotion){
                1->{
                    binding.ivFriendFirstemotion.setImageResource(R.drawable.ic_emoji_happy_pink_34)
                }
                2->{
                    binding.ivFriendFirstemotion.setImageResource(R.drawable.ic_emoji_what_pink_34)
                }
                3-> {
                    binding.ivFriendFirstemotion.setImageResource(R.drawable.ic_emoji_sad_pink_34)
                }
            }
            //여기서 동적 추가 해주기
            binding.lyWrapFriendEmoji.removeAllViews()
//            friendsConsumeData.reaction = friendsConsumeData.reaction.asReversed()

            friendsConsumeData.reaction.forEachIndexed { index, it ->
                val imageView = ImageView(context)

                val layoutParams = ConstraintLayout.LayoutParams(
                    ConstraintLayout.LayoutParams.WRAP_CONTENT,
                    ConstraintLayout.LayoutParams.WRAP_CONTENT
                )

                imageView.apply {
                    when (it) {
                        0 -> {
                            addEmojiButton.visibility = View.VISIBLE
                            addEmojiButton.bringToFront()
                        }
//                        1 -> {
//                            setImageResource(R.drawable.ic_emoji_happy_mint_28)
//                            layoutParams.apply {
//                                leftMargin = index * changeToDp()
//                                topToTop = R.id.ly_wrap_friend_emoji
//                                startToStart = R.id.ly_wrap_friend_emoji
//                            }
//                            binding.lyWrapFriendEmoji.addView(imageView, layoutParams)
//                        }
//                        2 -> {
//                            setImageResource(R.drawable.ic_emoji_smile_mint_28)
//                            layoutParams.apply {
//                                leftMargin = index * changeToDp()
//                                topToTop = R.id.ly_wrap_friend_emoji
//                                startToStart = R.id.ly_wrap_friend_emoji
//                            }
//                            binding.lyWrapFriendEmoji.addView(imageView, layoutParams)
//                        }
//                        3 -> {
//                            setImageResource(R.drawable.ic_emoji_funny_mint_28)
//                            layoutParams.apply {
//                                leftMargin = index * changeToDp()
//                                topToTop = R.id.ly_wrap_friend_emoji
//                                startToStart = R.id.ly_wrap_friend_emoji
//                            }
//                            binding.lyWrapFriendEmoji.addView(imageView, layoutParams)
//                        }
//                        4 -> {
//                            setImageResource(R.drawable.ic_emoji_flex_mint_28)
//                            layoutParams.apply {
//                                leftMargin = index * changeToDp()
//                                topToTop = R.id.ly_wrap_friend_emoji
//                                startToStart = R.id.ly_wrap_friend_emoji
//                            }
//                            binding.lyWrapFriendEmoji.addView(imageView, layoutParams)
//                        }
//                        5 -> {
//                            setImageResource(R.drawable.ic_emoji_what_mint_28)
//                            layoutParams.apply {
//                                leftMargin = index * changeToDp()
//                                topToTop = R.id.ly_wrap_friend_emoji
//                                startToStart = R.id.ly_wrap_friend_emoji
//                            }
//                            binding.lyWrapFriendEmoji.addView(imageView, layoutParams)
//                        }
//                        else -> {
//                            setImageResource(R.drawable.ic_emoji_sad_mint_28)
//                            layoutParams.apply {
//                                leftMargin = index * changeToDp()
//                                topToTop = R.id.ly_wrap_friend_emoji
//                                startToStart = R.id.ly_wrap_friend_emoji
//                            }
//                            binding.lyWrapFriendEmoji.addView(imageView, layoutParams)
//                        }
                    }
                }
                //imageView.bringToFront()

            }


        }

        fun setEmoji(position: Int) {
            val pos = position
            when (pos) {

                1 -> {
                    binding.ivAddemotion.setImageResource(R.drawable.ic_emoji_happy_mint_28)
                }
                2 -> {
                    binding.ivAddemotion.setImageResource(R.drawable.ic_emoji_smile_mint_28)
                }
                3 -> {
                    binding.ivAddemotion.setImageResource(R.drawable.ic_emoji_funny_mint_28)
                }
                4 -> {
                    binding.ivAddemotion.setImageResource(R.drawable.ic_emoji_flex_mint_28)
                }
                5 -> {
                    binding.ivAddemotion.setImageResource(R.drawable.ic_emoji_what_mint_28)
                }
                6 -> {
                    binding.ivAddemotion.setImageResource(R.drawable.ic_emoji_sad_mint_28)
                }
            }

        }

        fun changeToDp(): Int {
            val value = 22
            var displayMetrics = context.resources.displayMetrics
            var dp = Math.round(value * displayMetrics.density)
            return dp
        }
    }

    interface FriendsConsumeListInterface {
        fun onClick(data: View, position: Int, addEmoji: Boolean)
    }

    fun setConsumeListClickListener(listener: FriendsConsumeListInterface) {
        this.listener = listener
    }

    override fun getItemCount(): Int = friendConsumeList.size
}
