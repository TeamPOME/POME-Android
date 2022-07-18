package com.teampome.pome.presentation.mypage

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.teampome.pome.R
import com.teampome.pome.databinding.FragmentMyPageBinding
import com.teampome.pome.databinding.FragmentRecordBinding
import com.teampome.pome.util.base.BaseFragment
import com.teampome.pome.util.decorate.VerticalItemDecorator


class MyPageFragment : BaseFragment<FragmentMyPageBinding>(R.layout.fragment_my_page) {
    private var marshMellowAdapter = MarshMellowAdapter()
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.lifecycleOwner = this
        initAdapter()
    }

    private fun initAdapter() {
        binding.rcvMarsh.adapter = marshMellowAdapter
        addMarshList()

    }

    private fun addMarshList() {
        marshMellowAdapter.submitList(
            listOf(
                MarshMellowData(
                    R.drawable.ic_level, R.drawable.ic_badge, R.drawable.marshmallow_level_3_pink
                ),
                MarshMellowData(
                    R.drawable.ic_level, R.drawable.ic_badge, R.drawable.marshmallow_level_2_blue
                ),
                MarshMellowData(
                    R.drawable.ic_level, R.drawable.ic_badge, R.drawable.marshmallow_level_2_yellow
                ),
                MarshMellowData(
                    R.drawable.ic_level, R.drawable.ic_badge, R.drawable.marshmallow_level_4_mint
                )
            )
        )

    }

}
