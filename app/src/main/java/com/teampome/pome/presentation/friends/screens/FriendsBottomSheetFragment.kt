package com.teampome.pome.presentation.friends.screens

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.lifecycleScope
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import com.teampome.pome.R
import com.teampome.pome.data.FriendService
import com.teampome.pome.databinding.FragmentFriendsBottomSheetBinding
import com.teampome.pome.presentation.friends.adapters.FriendsReactAdapter
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch
import timber.log.Timber
import javax.inject.Inject

@AndroidEntryPoint
class FriendsBottomSheetFragment : BottomSheetDialogFragment() {
    private var _binding: FragmentFriendsBottomSheetBinding? = null
    private val binding get() = _binding ?: error("binding이 되지 않았습니다.")
    private lateinit var friendsReactAdapter: FriendsReactAdapter

    @Inject
    lateinit var service: FriendService

    private lateinit var id: String

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        _binding = FragmentFriendsBottomSheetBinding.inflate(layoutInflater, container, false)

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initSetHeight()
        initAdapter()

        getBundle() //recordId값 받아오기
        initWholeData() //처음 들어갈 때 0으로 설정
        clickEmoji() //클릭한 이모지에 따른 감정 보여주기 설정
    }

    private fun emojiChange() {
        binding.apply {
            ivWholeemotion.setImageResource(R.drawable.ic_emoji_whole_grey_34)
            ivEmotionFun.setImageResource(R.drawable.ic_emoji_mint_fun)
            ivEmotionFlex.setImageResource(R.drawable.ic_emoji_mint_flex)
            ivEmotionSad.setImageResource(R.drawable.ic_emoji_mint_sad)
            ivEmotionSmile.setImageResource(R.drawable.ic_emoji_smile_mint_34)
            ivEmotionHeart.setImageResource(R.drawable.ic_emoji_mint_heart)
            ivEmotionWhat.setImageResource(R.drawable.ic_emoji_mint_what)
        }
    }

    private fun clickEmoji() {

        binding.ivWholeemotion.setOnClickListener {
            emojiChange()
            initWholeData()
        }
        binding.ivEmotionHeart.setOnClickListener {
            emojiChange()
            binding.ivEmotionHeart.setImageResource(R.drawable.ic_emoji_heart_mint_34_click)
            initReactionBottomSheet(1)
        }
        binding.ivEmotionSmile.setOnClickListener {
            emojiChange()
            binding.ivEmotionSmile.setImageResource(R.drawable.ic_emoji_smile_mint_34_click)
            initReactionBottomSheet(2)
        }
        binding.ivEmotionFun.setOnClickListener {
            emojiChange()
            binding.ivEmotionFun.setImageResource(R.drawable.ic_emoji_funny_mint_34_click)
            initReactionBottomSheet(3)
        }
        binding.ivEmotionFlex.setOnClickListener {
            emojiChange()
            binding.ivEmotionFlex.setImageResource(R.drawable.ic_emoji_flex_mint_34_click)
            initReactionBottomSheet(4)

        }
        binding.ivEmotionWhat.setOnClickListener {
            emojiChange()
            binding.ivEmotionWhat.setImageResource(R.drawable.ic_emoji_what_mint_34_click)
            initReactionBottomSheet(5)
        }
        binding.ivEmotionSad.setOnClickListener {
            emojiChange()
            binding.ivEmotionSad.setImageResource(R.drawable.ic_emoji_sad_mint_34_click)
            initReactionBottomSheet(6)
        }
    }

    private fun getBundle() {
        val bundle = arguments
        if (bundle != null) {
            id = bundle.getString("recordId").toString()
        }
    }

    private fun initAdapter() {
        friendsReactAdapter = FriendsReactAdapter()
        binding.rvFriendsReaction.adapter = friendsReactAdapter
    }

    private fun initWholeData() {
        binding.ivWholeemotion.setImageResource(R.drawable.ic_all_view_emoji_34)
        initReactionBottomSheet(0)
    }

    private fun initReactionBottomSheet(type: Int) {
        lifecycleScope.launch {
            runCatching {
                service.getFriendsReaction(recordId = id.toInt(), type = type)
                //TYPE=0은 전체 조회
            }.onSuccess {
                val data = it.data?.reactions
                binding.tvWhole.text = "전체 " + it.data?.total
                if (data?.size == 0)
                    binding.clNoemotion.visibility = View.VISIBLE
                else
                    binding.clNoemotion.visibility = View.INVISIBLE
                friendsReactAdapter.submitList(data)
            }.onFailure {
                Timber.d("$it")
            }
        }
    }

    private fun initSetHeight() {
        val height = resources.displayMetrics.heightPixels * 0.5
        val bottomSheet = binding.clWholebottomsheet
        bottomSheet.minHeight = height.toInt()
        bottomSheet.maxHeight = height.toInt()
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}