package com.teampome.pome.presentation.record.goal

import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.WindowManager
import androidx.fragment.app.DialogFragment
import androidx.fragment.app.activityViewModels
import com.teampome.pome.data.GoalService
import com.teampome.pome.databinding.FragmentDeleteGoalDialogBinding
import com.teampome.pome.presentation.record.viewmodels.GoalIdViewModel
import com.teampome.pome.util.setOnSingleClickListener
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class DeleteGoalDialogFragment(private val goalId: Int) : DialogFragment() {

    @Inject
    lateinit var service: GoalService
    private val viewModel by activityViewModels<GoalIdViewModel>()
    private var _binding: FragmentDeleteGoalDialogBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentDeleteGoalDialogBinding.inflate(layoutInflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        cancelDialog()
        backgroundDesign()
        deleteNetwork()
    }

    private fun cancelDialog() {
        binding.tvNo.setOnSingleClickListener {
            dismiss()
        }
    }

    private fun deleteNetwork() {
        binding.tvDelete.setOnSingleClickListener {
            viewModel.goalId.value = goalId
            dismiss()
        }
    }

    private fun backgroundDesign() {
        dialog?.window?.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
    }

    override fun onResume() {
        super.onResume()
        dialog?.window?.setLayout(
            WindowManager.LayoutParams.MATCH_PARENT,
            WindowManager.LayoutParams.WRAP_CONTENT
        )
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}