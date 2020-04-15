package github.com.st235.lib_expandablebottombar.utils

import android.annotation.TargetApi
import android.content.res.ColorStateList
import android.graphics.drawable.Drawable
import android.graphics.drawable.RippleDrawable
import android.graphics.drawable.ShapeDrawable
import android.graphics.drawable.StateListDrawable
import android.graphics.drawable.shapes.RoundRectShape
import android.os.Build
import android.util.StateSet
import android.view.View
import androidx.annotation.ColorInt
import androidx.annotation.FloatRange
import androidx.annotation.Px
import androidx.core.graphics.ColorUtils
import androidx.core.view.ViewCompat

internal object RippleEffect {

    fun create(
        @ColorInt backgroundColor: Int,
        @ColorInt rippleColor: Int,
        @Px backgroundRoundRadius: Float,
        @FloatRange(from = 0.0, to = 1.0) opacity: Float
    ): Drawable =
        applyOnApi(
            Build.VERSION_CODES.LOLLIPOP,
            aboveScope = {
                getPressedColorRippleDrawable(
                    backgroundColor,
                    rippleColor,
                    backgroundRoundRadius,
                    opacity
                )
            },
            belowScope = {
                createStateListDrawable(
                    backgroundColor,
                    rippleColor,
                    backgroundRoundRadius,
                    opacity
                )
            }
        )

    @TargetApi(Build.VERSION_CODES.LOLLIPOP)
    private fun getPressedColorRippleDrawable(
        @ColorInt normalColor: Int,
        @ColorInt pressedColor: Int,
        @Px backgroundRoundRadius: Float,
        @FloatRange(from = 0.0, to = 1.0) opacity: Float
    ): Drawable = RippleDrawable(
        createColorStateList(pressedColor),
        createShapeDrawable(normalColor, backgroundRoundRadius, opacity),
        null
    ).apply {
        val states =
            arrayOf(intArrayOf(android.R.attr.state_enabled))
        val colors = intArrayOf(pressedColor)
        val colorStateList = ColorStateList(states, colors)
        setColor(colorStateList)
    }

    private fun createStateListDrawable(
        @ColorInt backgroundColor: Int,
        @ColorInt rippleColor: Int,
        @Px backgroundRoundRadius: Float,
        @FloatRange(from = 0.0, to = 1.0) opacity: Float
    ) =
        StateListDrawable().apply {
            addState(
                intArrayOf(
                    android.R.attr.state_pressed
                ),
                createShapeDrawable(rippleColor, backgroundRoundRadius, opacity)
            )
            addState(
                StateSet.WILD_CARD,
                createShapeDrawable(backgroundColor, backgroundRoundRadius, opacity)
            )
        }

    private fun createShapeDrawable(
        @ColorInt backgroundColor: Int,
        @Px roundRadius: Float,
        @FloatRange(from = 0.0, to = 1.0) opacity: Float
    ) = ShapeDrawable(
        RoundRectShape(
            floatArrayOf(
                roundRadius, roundRadius,
                roundRadius, roundRadius,
                roundRadius, roundRadius,
                roundRadius, roundRadius
            ),
            null,
            null
        )
    ).apply {
        paint.color = ColorUtils.setAlphaComponent(backgroundColor, (opacity * 255).toInt())
    }

    private fun createColorStateList(@ColorInt pressedColor: Int) =
        ColorStateList.valueOf(pressedColor)
}
