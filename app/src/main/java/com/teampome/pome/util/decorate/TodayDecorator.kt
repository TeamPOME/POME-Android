package com.teampome.pome.util.decorate

import android.content.Context
import androidx.core.content.ContextCompat
import com.prolificinteractive.materialcalendarview.CalendarDay
import com.prolificinteractive.materialcalendarview.DayViewDecorator
import com.prolificinteractive.materialcalendarview.DayViewFacade
import com.teampome.pome.R

class TodayDecorator(context: Context) : DayViewDecorator {
    private var date = CalendarDay.today()
    private val drawable = ContextCompat.getDrawable(context, R.drawable.selector_calendar_element)

    override fun shouldDecorate(day: CalendarDay?): Boolean {
        return day?.equals(date)!!
    }

    override fun decorate(view: DayViewFacade?) {
        if (drawable != null) {
            view?.setBackgroundDrawable(drawable)
        }
    }
}