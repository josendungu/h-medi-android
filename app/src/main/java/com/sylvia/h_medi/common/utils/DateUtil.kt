package com.sylvia.h_medi.common.utils

import java.time.LocalDate
import java.time.LocalTime
import java.time.ZoneOffset
import java.time.format.DateTimeFormatter
import java.util.*

object DateUtils {

    fun longToDate(long: Long): LocalDate {
        return LocalDate.ofEpochDay(long)
    }

    fun dateToLong(date: LocalDate): Long {
        return date.toEpochDay()
    }

    fun dateToString(date: LocalDate): String{
        val format = DateTimeFormatter.ofPattern("d MMMM yyyy")
        return date.format(format)
    }

    fun stringToDate(string: String): LocalDate {
        val dateFormatter = DateTimeFormatter.ofPattern("d MMMM yyyy")
        return LocalDate.parse(string, dateFormatter)
    }

    fun stringToLong(string: String): Long {
        val dateFormatter = DateTimeFormatter.ofPattern("d MMMM yyyy")

        return LocalDate.parse(string, dateFormatter)
            .atStartOfDay(ZoneOffset.UTC)
            .toInstant()
            .toEpochMilli()
    }


    fun timeToString(time: LocalTime): String{
        val format = DateTimeFormatter.ofPattern("H:mm:ss")
        return time.format(format)
    }


    fun dbStringToTime(string: String): LocalTime {
        val dateFormatter = DateTimeFormatter.ofPattern("H:mm:ss")
        return LocalTime.parse(string, dateFormatter)
    }


}