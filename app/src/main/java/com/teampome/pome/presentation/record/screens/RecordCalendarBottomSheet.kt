package com.teampome.pome.presentation.record.screens

import android.content.Context
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import com.prolificinteractive.materialcalendarview.CalendarDay
import com.prolificinteractive.materialcalendarview.CalendarMode
import com.prolificinteractive.materialcalendarview.format.ArrayWeekDayFormatter
import com.prolificinteractive.materialcalendarview.format.MonthArrayTitleFormatter
import com.teampome.pome.R
import com.teampome.pome.databinding.FragmentRecordCalendarBottomSheetBinding
import com.teampome.pome.util.decorate.MinMaxDecorator
import com.teampome.pome.util.decorate.TodayDecorator
import java.util.*

class RecordCalendarBottomSheet : BottomSheetDialogFragment() {

    private var _binding: FragmentRecordCalendarBottomSheetBinding? = null
    private val binding get() = _binding!!
    private var onListenerDate: OnListenerDate? = null

    interface OnListenerDate {
        fun onReceiveDate(date: Date)
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        onListenerDate = activity as OnListenerDate
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentRecordCalendarBottomSheetBinding.inflate(layoutInflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        calendarSetting()
        choiceDate()
        initSetHeight()
    }

    private fun calendarSetting() {
        binding.mcCalendar.selectedDate = CalendarDay.today()
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
            .setMinimumDate(CalendarDay.from(currentYear, currentMonth-1, 1))
            .setMaximumDate(
                CalendarDay.from(
                    currentYear, currentMonth + 2, endTimeCalendar.getActualMaximum(
                        Calendar.DAY_OF_MONTH
                    )
                )
            )
            .setCalendarDisplayMode(CalendarMode.MONTHS)
            .commit()

        //월, 요일 한글로 보이도록
        binding.mcCalendar.setTitleFormatter(MonthArrayTitleFormatter(resources.getTextArray(R.array.custom_months)))
        binding.mcCalendar.setWeekDayFormatter(ArrayWeekDayFormatter(resources.getTextArray(R.array.custom_weekdays)))

        val stCalendarDay = CalendarDay.from(currentYear, currentMonth-1, currentDate)
        val enCalendarDay = CalendarDay.from(
            endTimeCalendar.get(Calendar.YEAR),
            endTimeCalendar.get(Calendar.MONTH)+2,
            endTimeCalendar.get(Calendar.DATE)
        )

        val minMaxDecorator = MinMaxDecorator(stCalendarDay, enCalendarDay)
        val todayDecorator = TodayDecorator(requireContext())
        binding.mcCalendar.addDecorators(minMaxDecorator, todayDecorator)
    }

    private fun choiceDate() {
        binding.btnChoicedate.setOnClickListener {
            val date = binding.mcCalendar.selectedDate.date
            onListenerDate?.onReceiveDate(date)
            dismiss()
        }
    }

    private fun initSetHeight() {
        val height = resources.displayMetrics.heightPixels * 0.6
        val bottomSheet = binding.clCalendar
        bottomSheet.minHeight = height.toInt()
        bottomSheet.maxHeight = height.toInt()
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    override fun onDetach() {
        super.onDetach()
        onListenerDate = null
    }

}