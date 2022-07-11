package com.teampome.pome.presentation.record

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.teampome.pome.R
import com.teampome.pome.databinding.FragmentRecordBinding
import com.teampome.pome.util.CustomItemDecorator
import com.teampome.pome.util.VerticalItemDecorator

class RecordFragment : Fragment() {

    private var _binding: FragmentRecordBinding? = null
    private val binding get() = _binding ?: error("Binding 초기화 x")
    private lateinit var recordAdapter: RecordAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentRecordBinding.inflate(layoutInflater, container, false)

        initAdapter()

        return binding.root
    }

    private fun initAdapter() {
        recordAdapter = RecordAdapter()
        binding.rvRecord.adapter = recordAdapter
        addList()
        binding.rvRecord.addItemDecoration(CustomItemDecorator(12))
        binding.rvRecord.addItemDecoration(VerticalItemDecorator(6))
    }

    private fun addList() {
        recordAdapter.submitList(
            listOf(
                RecordData(1, "06.24", 1, "10,000원", "gg"),
                RecordData(1, "06.25", 1, "100,000원", "gg"),
                RecordData(1, "06.26", 1, "200,000원", "gg"),
                RecordData(1, "06.27", 1, "300,000원", "gg"),
                RecordData(1, "06.28", 1, "500,000원", "gg"),
                RecordData(1, "06.29", 1, "1,000,000원", "gg")
            )
        )
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}