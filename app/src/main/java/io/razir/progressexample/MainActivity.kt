package io.razir.progressexample

import android.graphics.Color
import android.os.Bundle
import android.os.Handler
import android.support.v4.content.ContextCompat
import android.support.v7.app.AppCompatActivity
import android.widget.Button
import io.razir.progressbutton.*
import kotlinx.android.synthetic.main.activity_main.*


class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        buttonAnimatedDrawable.setOnClickListener {
            showAnimatedDrawable(buttonAnimatedDrawable)
        }
        buttonProgressRightText.setOnClickListener {
            showProgressRight(buttonProgressRightText)
        }
        buttonProgressLeftText.setOnClickListener {
            showProgressLeft(buttonProgressLeftText)
        }
        buttonProgressCenter.setOnClickListener {
            showProgressCenter(buttonProgressCenter)
        }
        buttonProgressCustomStyle.setOnClickListener {
            showProgressCustom(buttonProgressCustomStyle)
        }
        buttonProgressMixed.setOnClickListener {
            showMixed(buttonProgressMixed)
        }
    }

    private fun showAnimatedDrawable(button: Button) {
        val animatedDrawable = ContextCompat.getDrawable(this, R.drawable.animated_check)!!
        //Defined bounds are required for your drawable
        val drawableSize = resources.getDimensionPixelSize(R.dimen.doneSize)
        animatedDrawable.setBounds(0, 0, drawableSize, drawableSize)

        button.showDrawable(animatedDrawable) {
            buttonTextRes = R.string.saved
            textMarginRes = R.dimen.drawableTextMargin
        }

        button.isEnabled = false

        Handler().postDelayed({
            button.isEnabled = true
            button.hideDrawable(R.string.animatedDrawable)

        }, 3000)
    }

    private fun showProgressRight(button: Button) {
        button.showProgress {
            buttonTextRes = R.string.loading
            progressColor = Color.WHITE
        }

        button.isEnabled = false
        Handler().postDelayed({
            button.isEnabled = true
            button.hideProgress(R.string.progressRight)
        }, 3000)
    }

    private fun showProgressLeft(button: Button) {
        button.showProgress {
            buttonTextRes = R.string.loading
            progressColor = Color.WHITE
            gravity = DrawableButton.GRAVITY_TEXT_START
        }

        button.isEnabled = false
        Handler().postDelayed({
            button.isEnabled = true
            button.hideProgress(R.string.progressLeft)
        }, 3000)
    }

    private fun showProgressCenter(button: Button) {
        button.showProgress {
            progressColor = Color.WHITE
            gravity = DrawableButton.GRAVITY_CENTER
        }

        button.isEnabled = false
        Handler().postDelayed({
            button.isEnabled = true
            button.hideProgress(R.string.progressCenter)
        }, 3000)
    }

    private fun showProgressCustom(button: Button) {
        button.showProgress {
            buttonTextRes = R.string.loading
            progressColors = intArrayOf(Color.WHITE, Color.MAGENTA, Color.GREEN)
            gravity = DrawableButton.GRAVITY_TEXT_END
            progressRadiusRes = R.dimen.progressRadius
            progressStrokeRes = R.dimen.progressStroke
            textMarginRes = R.dimen.textMarginStyled
        }
        button.isEnabled = false
        Handler().postDelayed({
            button.isEnabled = true
            button.hideProgress(R.string.progressCustomStyle)
        }, 5000)
    }

    private fun showMixed(button: Button) {
        val animatedDrawable = ContextCompat.getDrawable(this, R.drawable.animated_check)!!
        //Defined bounds are required for your drawable
        val drawableSize = resources.getDimensionPixelSize(R.dimen.doneSize)
        animatedDrawable.setBounds(0, 0, drawableSize, drawableSize)

        button.showProgress {
            buttonTextRes = R.string.loading
            progressColor = Color.WHITE
        }
        button.isEnabled = false


        Handler().postDelayed({
            button.isEnabled = true

            button.showDrawable(animatedDrawable) {
                buttonTextRes = R.string.saved
            }
            Handler().postDelayed({
                button.hideDrawable(R.string.progressCustomStyle)
            }, 2000)
        }, 3000)
    }
}

