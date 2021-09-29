package com.example.moviedbapp.utils

import android.content.Context
import android.view.inputmethod.InputMethodManager
import androidx.appcompat.widget.AppCompatEditText

object KeyboardHelper {
    fun hideKeyboard(context: Context, editText: AppCompatEditText){
        val keyBoardManager = context.getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
        keyBoardManager.hideSoftInputFromWindow(editText.windowToken, 0)
        editText.clearFocus()
    }
}