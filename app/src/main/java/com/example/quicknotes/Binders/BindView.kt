package com.example.quicknotes.Binders

import android.graphics.Color
import androidx.cardview.widget.CardView
import androidx.databinding.BindingAdapter

object BindView {
    @JvmStatic
    @BindingAdapter("cardBackgroundColor")
    fun setCardBackgroundColor(view: CardView, priority: String) {
        val color = when (priority) {
            "H" -> Color.parseColor("#004D40")
            "M" -> Color.parseColor("#1B5E20")
            else -> Color.parseColor("#2E7D32")
        }
        view.setCardBackgroundColor(color)
    }
}



