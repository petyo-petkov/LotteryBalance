package com.example.lotterybalance.database

import androidx.room.TypeConverter
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale

class Converters {
    @TypeConverter
    fun fromDateToString(date: Date?): String? {
        if (date == null) {
            return null
        }
        val format = SimpleDateFormat("ddMMMyy", Locale.getDefault())
        return format.format(date)
    }

    @TypeConverter
    fun fromStringToDate(dateString: String?): Date? {
        if (dateString == null) {
            return null
        }
        val format = SimpleDateFormat("ddMMMyy", Locale.getDefault())
        return format.parse(dateString)
    }

    @TypeConverter
    fun fromString(value: String?): List<String>? {
        if (value == null) {
            return null
        }
        val listType = object : TypeToken<List<String>>() {}.type
        return Gson().fromJson(value, listType)
    }

    @TypeConverter
    fun fromList(list: List<String>?): String? {
        if (list == null) {
            return null
        }
        val gson = Gson()
        return gson.toJson(list)
    }
}