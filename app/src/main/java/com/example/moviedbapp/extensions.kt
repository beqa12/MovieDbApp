package com.example.moviedbapp

import android.content.Context
import android.widget.Toast
import androidx.appcompat.widget.AppCompatEditText
import androidx.appcompat.widget.AppCompatImageView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.bitmap.RoundedCorners
import com.example.moviedbapp.animations.AnimationsUtils
import com.example.moviedbapp.utils.IMAGE_BASE_URL


fun AppCompatEditText.focusChangeAnimation(backBtn: AppCompatImageView){
    this.setOnFocusChangeListener{_v, hasFocus ->
        if (hasFocus){
            AnimationsUtils.focusChangeAnimation(this, backBtn, 400, false)
        }
    }
}

fun Context.toast(message: String){
    Toast.makeText(this, message, Toast.LENGTH_LONG).show()
}

fun AppCompatImageView.load(imageUrl: String?, corner: Int){
    Glide.with(this)
        .asBitmap()
        .load(IMAGE_BASE_URL + imageUrl)
        .transform(RoundedCorners(corner))
        .into(this)
}