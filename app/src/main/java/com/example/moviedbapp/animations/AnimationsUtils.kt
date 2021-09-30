package com.example.moviedbapp.animations

import android.animation.Animator
import android.animation.AnimatorSet
import android.animation.ObjectAnimator
import android.view.View
import android.view.animation.Animation
import android.view.animation.ScaleAnimation
import androidx.appcompat.widget.AppCompatEditText
import androidx.appcompat.widget.AppCompatImageView
import com.example.moviedbapp.gone
import com.example.moviedbapp.show

object AnimationsUtils {

    fun focusChangeAnimation(editText: AppCompatEditText, backBtn: AppCompatImageView, duration: Long, decrease: Boolean){
        val animatorSet = AnimatorSet()
        val rotation = if (decrease) -180f else 0f
        val alpha = if (decrease) 0f else 1f
        val backBtnRotate = ObjectAnimator.ofFloat(backBtn, View.ROTATION,rotation)
        val backBtnAlpha = ObjectAnimator.ofFloat(backBtn, View.ALPHA, alpha)
        animatorSet.duration = duration
        val startScale = if (decrease) 0.92f else 1f
        val endScale = if (decrease) 1f else 0.92f
        val anim: Animation = ScaleAnimation(startScale, endScale,
            1f, 1f,
            Animation.RELATIVE_TO_SELF, 1f,
            Animation.RELATIVE_TO_SELF, 1f
        )
        anim.fillAfter = true
        anim.duration = duration
        animatorSet.play(backBtnAlpha).with(backBtnRotate)
        animatorSet.addListener(object : Animator.AnimatorListener{
            override fun onAnimationRepeat(animation: Animator?) {

            }

            override fun onAnimationEnd(animation: Animator?) {
                if (decrease) {
                    backBtn.gone()
                }
            }

            override fun onAnimationCancel(animation: Animator?) {
            }

            override fun onAnimationStart(animation: Animator?) {
                if (!decrease) {
                    backBtn.show()
                }
            }

        })
        animatorSet.start()
        editText.startAnimation(anim)
    }
}