package com.eoisaac.wod.viewModels

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.eoisaac.wod.R
import com.eoisaac.wod.utils.StringContent
import java.util.*

class WorkoutModelView : ViewModel() {

    private var greeting = MutableLiveData<StringContent>()

    fun timeBasedGreeting(): LiveData<StringContent> {
        return greeting
    }

    fun getTimeBasedGreeting(date: Date = Date()) {
        val calendar = Calendar.getInstance()
        calendar.time = date

        val hour = calendar.get(Calendar.HOUR_OF_DAY)
        val greetingResourceId = when (hour) {
            in 6..11 -> R.string.morning_greeting
            in 12..17 -> R.string.afternoon_greeting
            else -> R.string.evening_greeting
        }

        this.greeting.value = StringContent.StringResource(resourceId = greetingResourceId)
    }
}