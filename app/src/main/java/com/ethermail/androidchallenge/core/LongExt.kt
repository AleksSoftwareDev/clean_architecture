package com.ethermail.androidchallenge.core

import java.sql.Date
import java.text.SimpleDateFormat
import java.util.Locale

fun Long.toDayMonthYear(): String {
    val date = Date(this)
    val format = SimpleDateFormat("dd/MM/yyyy", Locale.getDefault())
    return format.format(date)
}