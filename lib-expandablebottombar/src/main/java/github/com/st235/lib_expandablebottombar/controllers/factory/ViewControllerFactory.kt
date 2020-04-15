package github.com.st235.lib_expandablebottombar.controllers.factory

import android.content.Context
import android.content.res.ColorStateList
import android.graphics.Color
import android.view.View
import androidx.annotation.ColorInt
import androidx.annotation.FloatRange
import androidx.annotation.Px
import androidx.appcompat.widget.AppCompatImageView
import github.com.st235.lib_expandablebottombar.ExpandableBottomBar
import github.com.st235.lib_expandablebottombar.ExpandableBottomBarMenuItem
import github.com.st235.lib_expandablebottombar.controllers.ItemViewController
import github.com.st235.lib_expandablebottombar.utils.ItemBackgroundFactory
import github.com.st235.lib_expandablebottombar.utils.DrawableHelper

internal abstract class ViewControllerFactory(
    protected val menuItem: ExpandableBottomBarMenuItem,
    protected val itemBackgroundFactory: ItemBackgroundFactory
) {

    @Px
    protected var itemVerticalPadding: Int = 0
    @Px
    protected var itemHorizontalPadding: Int = 0
    @Px
    protected var backgroundCornerRadius: Float = 0.0f
    @FloatRange(from = 0.0, to = 1.0)
    protected var backgroundOpacity: Float = 1.0f
    @ColorInt
    protected var backgroundColor: Int = Color.TRANSPARENT
    @ColorInt
    protected var inactiveRippleColor: Int = Color.TRANSPARENT

    protected lateinit var backgroundColorSelector: ColorStateList
    protected lateinit var onItemClickListener: (View) -> Unit

    fun itemMargins(
        @Px itemHorizontalPadding: Int,
        @Px itemVerticalPadding: Int
    ): ViewControllerFactory {
        this.itemVerticalPadding = itemVerticalPadding
        this.itemHorizontalPadding = itemHorizontalPadding
        return this
    }

    fun backgroundColor(@ColorInt color: Int): ViewControllerFactory {
        this.backgroundColor = color
        return this
    }

    fun inactiveRippleColor(@ColorInt color: Int): ViewControllerFactory {
        this.inactiveRippleColor = color
        return this
    }

    fun itemBackground(backgroundCornerRadius: Float,
                       @FloatRange(from = 0.0, to = 1.0) backgroundOpacity: Float): ViewControllerFactory {
        this.backgroundCornerRadius = backgroundCornerRadius
        this.backgroundOpacity = backgroundOpacity
        return this
    }

    fun itemsColors(backgroundColorSelector: ColorStateList): ViewControllerFactory {
        this.backgroundColorSelector = backgroundColorSelector
        return this
    }

    fun onItemClickListener(onItemClickListener: (View) -> Unit): ViewControllerFactory {
        this.onItemClickListener = onItemClickListener
        return this
    }

    abstract fun build(context: Context): ItemViewController

    protected fun createAppCompatImageView(context: Context) = AppCompatImageView(context).apply {
        setImageDrawable(
            DrawableHelper.createDrawable(
                context,
                menuItem.iconId,
                backgroundColorSelector
            )
        )
    }

    companion object {

        fun from(menuItem: ExpandableBottomBarMenuItem,
                 itemsMode: ExpandableBottomBar.ItemsMode,
                 itemBackgroundFactory: ItemBackgroundFactory
        ): ViewControllerFactory =
            when(itemsMode) {
                ExpandableBottomBar.ItemsMode.DEFAULT -> DefaultViewControllerFactory(menuItem, itemBackgroundFactory)
                ExpandableBottomBar.ItemsMode.SHORT -> ShortItemViewControllerFactory(menuItem, itemBackgroundFactory)
                ExpandableBottomBar.ItemsMode.UNDERLINE -> UnderlineViewControllerFactory(menuItem, itemBackgroundFactory)
            }
    }
}