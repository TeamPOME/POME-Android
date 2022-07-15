package com.teampome.pome.presentation.friends.screens

import android.content.Context
import android.os.Bundle
import android.view.View
import android.widget.ImageView
import com.skydoves.balloon.balloon
import com.teampome.pome.R
import com.teampome.pome.databinding.FragmentFriendsBinding
import com.teampome.pome.presentation.friends.FriendsConsumeData
import com.teampome.pome.presentation.friends.FriendsProfileData
import com.teampome.pome.presentation.friends.FriendsProfileWholeData
import com.teampome.pome.presentation.friends.adapters.FriendsConsumeAdapter
import com.teampome.pome.presentation.friends.adapters.FriendsProfileAdapter
import com.teampome.pome.presentation.friends.adapters.FriendsReactAdapter
import com.teampome.pome.util.base.BaseFragment
import com.teampome.pome.util.decorate.FriendsConsumeItemDecorator
import com.teampome.pome.util.decorate.FriendsProfileItemDecorator
import timber.log.Timber

class FriendsFragment : BaseFragment<FragmentFriendsBinding>(R.layout.fragment_friends) {
    private lateinit var friendsConsumeAdapter: FriendsConsumeAdapter
    private lateinit var friendsProfileAdapter: FriendsProfileAdapter
    private lateinit var friendsReactAdapter:FriendsReactAdapter

    private val friendsBottomSheetFragment = FriendsBottomSheetFragment()
    private val friendsEmojiBalloon by balloon<FriendsEmojiBalloon>()
    private var emoji_position: Int = -1
    private var list_position: Int = -1
    private lateinit var emojiList: List<ImageView>
//    private val getSharedPreference =
//        activity?.getSharedPreferences("emoji_store", Context.MODE_PRIVATE)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initConsumeAdapter()

        initListAdapter()

        //후에 서버통신 할 에정
        initWholeData()

        getFriendProfileList()
        getFriendsConsumeData()

        consumeClick()
        addFriendsDecoration()

        initBalloonList()
        //balloon에 있는 이모지들 초기화

        //initSharedPreference()
        //addEmoji(list_position)
        //emojiSet()

        //

    }

//    private fun initSharedPreference() {
//        getSharedPreference?.edit()?.putString(
//            list_position.toString(), emoji_position.toString()
//        )
//        Timber.d("저장오나료")
//    } //data위치와 이모지 번호를 sharedpreference에 저장

//    private fun emojiSet() {
//        //sharedpreference로 넣기
//        when (emoji_position) {
//            0 -> {
//                binding.ivPlusfriend.setImageResource(R.drawable.ic_emoji_happy_mint_28)
//            }
//            1 -> {
//                binding.ivPlusfriend.setImageResource(R.drawable.ic_emoji_smile_mint_28)
//            }
//            2 -> {
//                binding.ivPlusfriend.setImageResource(R.drawable.ic_emoji_funny_mint_28)
//            }
//            3 -> {
//                binding.ivPlusfriend.setImageResource(R.drawable.ic_emoji_flex_mint_28)
//            }
//            4 -> {
//                binding.ivPlusfriend.setImageResource(R.drawable.ic_emoji_what_mint_28)
//            }
//            5 -> {
//                binding.ivPlusfriend.setImageResource(R.drawable.ic_emoji_sad_mint_28)
//            }
//            else -> {
//                //-1인 경우
//            }
//        }
//        //local에 저장하기
//
//    }

    private fun initConsumeAdapter() {
        friendsConsumeAdapter = FriendsConsumeAdapter()
        binding.rcvFriendsconsumelist.adapter = friendsConsumeAdapter
    }

    private fun initListAdapter() {
        friendsProfileAdapter = FriendsProfileAdapter()
        binding.rcvFriends.adapter = friendsProfileAdapter
    }


    private fun getFriendsConsumeData() {
        //추후에 서버에서 가져오기
        binding.rcvFriendsconsumelist.visibility = View.VISIBLE
        binding.clFriendsempty.visibility = View.INVISIBLE
        friendsConsumeAdapter.submitList(
            listOf(
                FriendsConsumeData(
                    name = "ㅇㅈㅇ12",
                    description = "일이삼사오육칠팔구십일이삼사오육칠팔구",
                    date = "07.09",
                    price = "4,400",
                    first_emotion = 1,
                    second_emotion = 2,
                    tag = "탐탐은 민초가 짱",
                    reaction = listOf(1, 2, 3)
                ),
                FriendsConsumeData(
                    name = "ㅇㅈㅇ2",
                    description = "탐탐민초 추천",
                    date = "07.09",
                    price = "4,400",
                    first_emotion = 1,
                    second_emotion = 2,
                    tag = "탐탐은 민초가 짱",
                    reaction = listOf(2, 5)
                ),
                FriendsConsumeData(
                    name = "ㅇㅈㅇ3",
                    description = "탐탐민초 추천",
                    date = "07.09",
                    price = "4,400",
                    first_emotion = 1,
                    second_emotion = 2,
                    tag = "탐탐은 민초가 짱",
                    reaction = listOf(4, 5)
                ),
                FriendsConsumeData(
                    name = "ㅇㅈㅇ4",
                    description = "탐탐민초 추천",
                    date = "07.09",
                    price = "4,400",
                    first_emotion = 1,
                    second_emotion = 2,
                    tag = "탐탐은 민초가 짱",
                    reaction = listOf(2, 4, 5)
                ),
                FriendsConsumeData(
                    name = "ㅇㅈㅇ5",
                    description = "탐탐민초 추천",
                    date = "07.09",
                    price = "4,400",
                    first_emotion = 1,
                    second_emotion = 2,
                    tag = "탐탐은 민초가 짱",
                    reaction = listOf(3, 5)
                ),
                FriendsConsumeData(
                    name = "ㅇㅈㅇ6",
                    description = "탐탐민초 추천",
                    date = "07.09",
                    price = "4,400",
                    first_emotion = 1,
                    second_emotion = 2,
                    tag = "탐탐은 민초가 짱",
                    reaction = listOf(1)
                ),
                FriendsConsumeData(
                    name = "ㅇㅈㅇ7",
                    description = "탐탐민초 추천",
                    date = "07.09",
                    price = "4,400",
                    first_emotion = 1,
                    second_emotion = 2,
                    tag = "탐탐은 민초가 짱",
                    reaction = listOf(1)
                ),
                FriendsConsumeData(
                    name = "ㅇㅈㅇ8",
                    description = "탐탐민초 추천",
                    date = "07.09",
                    price = "4,400",
                    first_emotion = 1,
                    second_emotion = 2,
                    tag = "탐탐은 민초가 짱",
                    reaction = listOf(1)
                ),
                FriendsConsumeData(
                    name = "ㅇㅈㅇ9",
                    description = "탐탐민초 추천",
                    date = "07.09",
                    price = "4,400",
                    first_emotion = 1,
                    second_emotion = 2,
                    tag = "탐탐은 민초가 짱",
                    reaction = listOf(1)
                )
            )
        )
    }

    private fun initWholeData() {
        friendsProfileAdapter.friendsProfileList.add(
            FriendsProfileWholeData("전체", "tmp")
        )
    }

    private fun getFriendProfileList() {
        binding.rcvFriends.visibility = View.VISIBLE
        binding.clFriendsempty.visibility = View.INVISIBLE
        binding.ivEmptyfriends.setImageResource(R.drawable.ic_friend_profile_full)
        friendsProfileAdapter.friendsProfileList.addAll(
            listOf(
                FriendsProfileData("황연진입니다", "tmp"),
                FriendsProfileData("김수빈", "tmp"),
                FriendsProfileData("양지영", "tmp"),
                FriendsProfileData("황연진", "tmp"),
                FriendsProfileData("김수빈", "tmp"),
                FriendsProfileData("양지영", "tmp"),
                FriendsProfileData("황연진", "tmp"),
                FriendsProfileData("김수빈", "tmp"),
                FriendsProfileData("양지영", "tmp")
            )
        )
    }

    private fun addFriendsDecoration() {
        binding.rcvFriendsconsumelist.addItemDecoration(FriendsConsumeItemDecorator(12))
        binding.rcvFriends.addItemDecoration(FriendsProfileItemDecorator(18))
    }

    private fun initProfileClick(){
        friendsProfileAdapter.setOnProfileListClickListener(object:FriendsProfileAdapter.FriendsListClickInterface{
            override fun onProfileListClick(v: View, data: FriendsProfileData, pos: Int) {
                //
            }
        })
    }

    private fun initBalloonList() {
        emojiList = listOf<ImageView>(
            friendsEmojiBalloon.getContentView().findViewById<ImageView>(R.id.iv_react_heart),
            friendsEmojiBalloon.getContentView().findViewById<ImageView>(R.id.iv_react_smile),
            friendsEmojiBalloon.getContentView().findViewById<ImageView>(R.id.iv_react_fun),
            friendsEmojiBalloon.getContentView().findViewById<ImageView>(R.id.iv_react_flex),
            friendsEmojiBalloon.getContentView().findViewById(R.id.iv_react_what),
            friendsEmojiBalloon.getContentView().findViewById<ImageView>(R.id.iv_react_sad)
        )
    }

    private fun addEmoji(list_pos: Int) {
        emoji_position = -1
        for (i in emojiList.indices) {
            emojiList[i].setOnClickListener {
                emoji_position = i
                //Timber.d("clicked_emoji=$emoji_position, 리스트 순번=$list_pos")
                //initSharedPreference()
                friendsEmojiBalloon.dismiss()
            }
        }
        //서버에서 날리고 ~
        //friendsConsumeAdapter.changeItem(//, postion)
    }
    //클릭된 이모지의 위치 알아낸 후 sharedpreference에 저장

    private fun consumeClick() {
        friendsConsumeAdapter.setConsumeListClickListener(object :
            FriendsConsumeAdapter.FriendsConsumeListInterface {
            override fun onClick(data: View, position: Int, addEmoji: Boolean) {
                //bottom sheet로 반응 나오게 하기
                if (!addEmoji) {
                    if (!friendsBottomSheetFragment.isAdded)
                        friendsBottomSheetFragment.show(
                            childFragmentManager,
                            friendsBottomSheetFragment.tag
                        )//클릭한 부분이 다르기 때문에 addEmoji로 구분함.
                } else {
                    list_position = position
                    friendsEmojiBalloon.showAlignBottom(data)
                    addEmoji(list_position)
                    friendsEmojiBalloon.dismiss()
                }//balloon을 띄우기 위해 현재 클릭된 data의 위치를 알려주고 해당 위치 밑에 balloon을 띄운다.
            }
        })
    }
    private fun profileClick(){

    }
}