package dev.albertocaro.cursocuvalles2025.data.database.converter

import androidx.room.TypeConverter
import java.util.Date

class DateConverter {

    @TypeConverter
    fun formDateToLong(date: Date?): Long? = date?.time

    @TypeConverter
    fun fromLongToDate(time: Long?): Date? = time?.let { Date(it) }
}