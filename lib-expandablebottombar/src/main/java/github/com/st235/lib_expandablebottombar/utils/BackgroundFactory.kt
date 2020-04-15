package github.com.st235.lib_expandablebottombar.utils

import android.graphics.drawable.Drawable
import androidx.annotation.ColorInt

internal class BackgroundFactory {

    fun createHighlightedMenu(
        @ColorInt activeColor: Int,
        backgroundCornerRadius: Float,
        backgroundOpacity: Float
    ): Drawable {
        return DrawableHelper.createShapeDrawable(
            activeColor,
            backgroundCornerRadius,
            backgroundOpacity
        )
    }
}