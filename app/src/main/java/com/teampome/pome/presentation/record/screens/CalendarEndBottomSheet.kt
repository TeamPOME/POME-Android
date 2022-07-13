package com.teampome.pome.presentation.record.screens

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import com.prolificinteractive.materialcalendarview.CalendarDay
import com.prolificinteractive.materialcalendarview.CalendarMode
import com.prolificinteractive.materialcalendarview.format.ArrayWeekDayFormatter
import com.prolificinteractive.materialcalendarview.format.MonthArrayTitleFormatter
import com.teampome.pome.R
import com.teampome.pome.databinding.FragmentCalendarEndBottomSheetBinding
import com.teampome.pome.util.decorate.MinMaxDecorator
import com.teampome.pome.util.decorate.TodayDecorator
import timber.log.Timber
import java.util.*

class CalendarEndBottomSheet : BottomSheetDialogFragment() {

    private var _binding: FragmentCalendarEndBottomSheetBinding? = null
    private val binding get() = _binding!!
    private var mOnClickListener: OnClickListener? = null

    interface OnClickListener {
        fun onReceiveEndData(name: String)
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        mOnClickListener = activity as OnClickListener
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentCalendarEndBottomSheetBinding.inflate(layoutInflater, container,false)
        calendarSetting()
        choiceDate()
        return binding.root
    }

    override fun onDetach() {
        super.onDetach()
        mOnClickListener = null
    }

    private fun calendarSetting() {
        //시작일 받아오는 로직 추가하기..
        val startDate = arguments?.getString("date123")
        Timber.d("startDate $startDate")
        binding.mcCalendar.selectedDate = CalendarDay.from(2022, 7-1,27)
        val startTimeCalendar = Calendar.getInstance()
        val endTimeCalendar = Calendar.getInstance()

        val currentYear = startTimeCalendar.get(Calendar.YEAR)
        val currentMonth = startTimeCalendar.get(Calendar.MONTH)
        val currentDate = startTimeCalendar.get(Calendar.DATE)

        //끝나는 달
        endTimeCalendar.set(Calendar.MONTH, currentMonth + 1)

        //최소 선택 날짜, 최대 선택 날짜
        binding.mcCalendar.state().edit()
            .setFirstDayOfWeek(Calendar.SUNDAY)
            .setMinimumDate(CalendarDay.from(currentYear, currentMonth, 1))
            .setMaximumDate(
                CalendarDay.from(
                    currentYear, currentMonth + 1, endTimeCalendar.getActualMaximum(
                        Calendar.DAY_OF_MONTH
                    )
                )
            )
            .setCalendarDisplayMode(CalendarMode.MONTHS)
            .commit()

        //월, 요일 한글로 보이도록
        binding.mcCalendar.setTitleFormatter(MonthArrayTitleFormatter(resources.getTextArray(R.array.custom_months)))
        binding.mcCalendar.setWeekDayFormatter(ArrayWeekDayFormatter(resources.getTextArray(R.array.custom_weekdays)))

        val stCalendarDay = CalendarDay.from(currentYear, currentMonth, currentDate)
        val enCalendarDay = CalendarDay.from(
            endTimeCalendar.get(Calendar.YEAR),
            endTimeCalendar.get(Calendar.MONTH),
            endTimeCalendar.get(Calendar.DATE)
        )

        val minMaxDecorator = MinMaxDecorator(stCalendarDay, enCalendarDay)
        val todayDecorator = TodayDecorator(requireContext())
        binding.mcCalendar.addDecorators(minMaxDecorator, todayDecorator)
    }

    private fun choiceDate() {
        binding.btnChoicedate.setOnClickListener {
            val date = binding.mcCalendar.selectedDate
            val choiceDate : String = if (date.month.toString().length == 1){
                "${date.year}.0${date.month+1}.${date.day}"
            } else {
                "${date.year}.${date.month+1}.${date.day}"
            }
            mOnClickListener?.onReceiveEndData(choiceDate)
            dismiss()
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}