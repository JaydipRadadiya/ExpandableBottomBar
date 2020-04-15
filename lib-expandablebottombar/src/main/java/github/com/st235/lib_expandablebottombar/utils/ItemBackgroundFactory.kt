package github.com.st235.lib_expandablebottombar.utils

import android.graphics.drawable.Drawable
import androidx.annotation.ColorInt
import androidx.annotation.FloatRange
import androidx.annotation.Px

internal class ItemBackgroundFactory {

    fun createHighlightedMenu(
        @ColorInt activeColor: Int,
        @ColorInt rippleColor: Int,
        @Px cornerRadius: Float,
        @FloatRange(from = 0.0, to = 1.0) opacity: Float
    ): Drawable {
        return RippleEffect.create(activeColor, rippleColor, cornerRadius, opacity)
    }
}