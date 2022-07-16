package com.teampome.pome.presentation.record.screens

import android.icu.text.SimpleDateFormat
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.activityViewModels
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import com.prolificinteractive.materialcalendarview.CalendarDay
import com.prolificinteractive.materialcalendarview.CalendarMode
import com.prolificinteractive.materialcalendarview.format.ArrayWeekDayFormatter
import com.prolificinteractive.materialcalendarview.format.MonthArrayTitleFormatter
import com.teampome.pome.R
import com.teampome.pome.databinding.FragmentCalendarEndBottomSheetBinding
import com.teampome.pome.util.decorate.MinMaxDecorator
import java.util.*
import java.util.Calendar.*

class CalendarEndBottomSheet : BottomSheetDialogFragment() {

    private val viewModel by activityViewModels<CalendarViewModel>()
    private var _binding: FragmentCalendarEndBottomSheetBinding? = null
    private val binding get() = _binding!!


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentCalendarEndBottomSheetBinding.inflate(layoutInflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        calendarSetting()
        choiceDate()
        initSetHeight()
    }

    private fun calendarSetting() {
        val sdf = SimpleDateFormat("yyyy/MM/dd EEEE HH:mm:ss z", Locale.KOREAN)
        //시작일 받아오는 로직 추가하기
        //it은 date 형식인데, selectedDate는 캘린더 형식임.
        var customDate: String
        viewModel.startDate.observe(this) {
            //binding.mcCalendar.selectedDate = CalendarDay.from(it)
            customDate = sdf.format(it)
            //it은 calendarstartbottomsheet에서 받아오는 것
            Log.e("dateEndBottom777", "${customDate}")
        }

        //Log.e("dataresult", "$koreaDate")
        //Log.e("dateEndBottom12", "${binding.mcCalendar.selectedDate}")
        //현재 날짜 받아서 + selectedDate 계산
        // selectedDate 형변환 formatt
        //Log.e("dateEndBottom777", "${customDate}")
        val startTimeCalendar = getInstance()//객체 생성 구현된 ~~
        val endTimeCalendar = getInstance()

        Log.e("start", "$endTimeCalendar")

        //선택한 날짜 변수덜 int로 가져옴.
        val currentYear = startTimeCalendar.get(YEAR)
        val currentMonth = startTimeCalendar.get(MONTH)
        val currentDate = startTimeCalendar.get(DATE)


        //끝나는 달
        endTimeCalendar.set(MONTH, currentMonth + 1)

        //최소 선택 날짜, 최대 선택 날짜
        binding.mcCalendar.state().edit()
            .setFirstDayOfWeek(SUNDAY)
            .setMinimumDate(CalendarDay.from(currentYear, currentMonth, 1))
            .setMaximumDate(
                CalendarDay.from(
                    currentYear, currentMonth + 1, endTimeCalendar.getActualMaximum(
                        DAY_OF_MONTH
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
            endTimeCalendar.get(YEAR),
            endTimeCalendar.get(MONTH),
            endTimeCalendar.get(DATE)
        )

        // 현재 날짜 기준으로 decorate임.
        //min, max 파라미터에 선택한 날짜를 넣어두면 됨.
        val minMaxDecorator = MinMaxDecorator(stCalendarDay, enCalendarDay)
        binding.mcCalendar.addDecorators(minMaxDecorator)
    }

    private fun choiceDate() {
        binding.btnChoicedate.setOnClickListener {
            val date = binding.mcCalendar.selectedDate.date
            viewModel.endDate.value = date
            Log.d("date", "$date")
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
}