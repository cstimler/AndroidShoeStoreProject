// https://stackoverflow.com/questions/45967818/android-data-binding-two-way-conversion-methods

package com.udacity.shoestore.models

import android.R
import android.content.res.Resources
import android.net.ParseException
import android.view.View

import android.widget.TextView

import androidx.databinding.InverseMethod
import java.text.DecimalFormat
import java.text.NumberFormat
import java.util.*



object Converter {
    @InverseMethod("toDouble")
    fun toString(
        view: TextView, oldValue: Double,
        value: Double
    ): String {
        val numberFormat: NumberFormat = getNumberFormat(view)
        try {
            // Don't return a different value if the parsed value
            // doesn't change
            val inView = view.text.toString()
            val parsed: Double = numberFormat.parse(inView).toDouble()
            if (parsed == value) {
                return view.text.toString()
            }
        } catch (e: ParseException) {
            // Old number was broken
        }
        return numberFormat.format(value)
    }

    fun toDouble(
        view: TextView, oldValue: Double,
        value: String?
    ): Double {
        val numberFormat: NumberFormat = getNumberFormat(view)
        return try {
            numberFormat.parse(value).toDouble()
        } catch (e: ParseException) {
            val resources: Resources = view.resources
            val errStr: String = "bad string!"
            view.error = errStr
            oldValue
        }
    }

    private fun getNumberFormat(view: View): NumberFormat {
        val resources: Resources = view.getResources()
        val locale: Locale = resources.getConfiguration().locale
        val format: NumberFormat = NumberFormat.getNumberInstance(locale)
        if (format is DecimalFormat) {
            val decimalFormat: DecimalFormat = format as DecimalFormat
            decimalFormat.setGroupingUsed(false)
        }
        return format
    }
}
