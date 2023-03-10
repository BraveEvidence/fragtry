package com.rnappfragment

import android.content.Context
import android.graphics.Color
import android.view.ViewGroup
import android.widget.LinearLayout
import android.widget.TextView


class CenteredText(context: Context): LinearLayout(context){

    private var textView: TextView
    private var secondtextView: TextView

    init {
        val layoutParams: ViewGroup.LayoutParams =
            LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT)
        setLayoutParams(layoutParams)
        orientation = VERTICAL

        textView = TextView(context)
        addView(textView)

        secondtextView = TextView(context)
        addView(secondtextView)
        secondtextView.text = "Hello I am second"
        secondtextView.textSize = 25F
        secondtextView.setTextColor(Color.RED)
    }

    fun configureText(text: String){
        textView.text = text
    }

}