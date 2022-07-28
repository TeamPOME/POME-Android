package com.teampome.pome.presentation.remind.screens

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.lifecycleScope
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import com.teampome.pome.R
import com.teampome.pome.data.FriendService
import com.teampome.pome.databinding.FragmentRemindReactionBottomSheetBinding
import com.teampome.pome.presentation.remind.adapters.RemindReactionAdapter
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch
import timber.log.Timber
import javax.inject.Inject

@AndroidEntryPoint
class RemindReactionBottomSheet : BottomSheetDialogFragment() {
    private var _binding: FragmentRemindReactionBottomSheetBinding? = null
    private val binding get() = _binding ?: error("binding이 되지 않는다.")
    private lateinit var remindReactionAdapter: RemindReactionAdapter

    @Inject
    lateinit var service: FriendService
    lateinit var id: String

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding =
            FragmentRemindReactionBottomSheetBinding.inflate(layoutInflater, container, false)

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        initHeight()
        initAdapter()

        getBundle()
        initWholeData()
        clickEmoji()
    }

    private fun getBundle() {
        val bundle = arguments
        if (bundle != null) {
            id = bundle.getString("recordId").toString()
        }
    }

    private fun initRemindBottomSheet(type: Int) {
        lifecycleScope.launch {
            runCatching {
                service.getFriendsReaction(recordId = id.toInt(), type = type)
            }.onSuccess {
                val data = it.data?.reactions
                binding.tvWhole.text = "전체 " + it.data?.total
                if (data?.size == 0)
                    binding.clNoemotion.visibility = View.VISIBLE
                else
                    binding.clNoemotion.visibility = View.INVISIBLE
                remindReactionAdapter.submitList(data)
            }.onFailure {
                Timber.d("$it")
            }
        }
    }

    private fun initAdapter() {
        remindReactionAdapter = RemindReactionAdapter()
        binding.rvRemindReaction.adapter = remindReactionAdapter
    }

    private fun initHeight() {
        val bottomSheet = binding.clRemindbottomsheet
        val height = resources.displayMetrics.heightPixels * 0.6
        bottomSheet.minHeight = height.toInt()
        bottomSheet.maxHeight = height.toInt()
    }

    private fun initWholeData() {
        binding.ivWholeemotion.setImageResource(R.drawable.ic_all_view_emoji_34)
        initRemindBottomSheet(0)
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

    private fun clickEmoji(){
        binding.ivWholeemotion.setOnClickListener {
            emojiChange()
            initWholeData()
        }
        binding.ivEmotionHeart.setOnClickListener {
            emojiChange()
            binding.ivEmotionHeart.setImageResource(R.drawable.ic_emoji_heart_mint_34_click)
            initRemindBottomSheet(1)
        }
        binding.ivEmotionSmile.setOnClickListener {
            emojiChange()
            binding.ivEmotionSmile.setImageResource(R.drawable.ic_emoji_smile_mint_34_click)
            initRemindBottomSheet(2)
        }
        binding.ivEmotionFun.setOnClickListener {
            emojiChange()
            binding.ivEmotionFun.setImageResource(R.drawable.ic_emoji_funny_mint_34_click)
            initRemindBottomSheet(3)
        }
        binding.ivEmotionFlex.setOnClickListener {
            emojiChange()
            binding.ivEmotionFlex.setImageResource(R.drawable.ic_emoji_flex_mint_34_click)
            initRemindBottomSheet(4)

        }
        binding.ivEmotionWhat.setOnClickListener {
            emojiChange()
            binding.ivEmotionWhat.setImageResource(R.drawable.ic_emoji_what_mint_34_click)
            initRemindBottomSheet(5)
        }
        binding.ivEmotionSad.setOnClickListener {
            emojiChange()
            binding.ivEmotionSad.setImageResource(R.drawable.ic_emoji_sad_mint_34_click)
            initRemindBottomSheet(6)
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}