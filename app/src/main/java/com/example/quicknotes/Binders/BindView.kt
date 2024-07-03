package com.example.quicknotes.Binders

import android.graphics.Color
import androidx.cardview.widget.CardView
import androidx.databinding.BindingAdapter

object BindView {
    @JvmStatic
    @BindingAdapter("cardBackgroundColor")
    fun setCardBackgroundColor(view: CardView, priority: String) {
        val color = when(priority){
            "H" -> Color.parseColor("#023020")
            "M" -> Color.DKGRAY
            else -> Color.parseColor("#008000")
        }
        view.setCardBackgroundColor(color)
    }

}