package github.com.st235.lib_expandablebottombar.controllers

import android.view.View
import android.widget.ImageView
import androidx.annotation.ColorInt
import github.com.st235.lib_expandablebottombar.ExpandableBottomBarMenuItem
import github.com.st235.lib_expandablebottombar.utils.ItemBackgroundFactory

internal class IconOnlyItemViewController(
    override val menuItem: ExpandableBottomBarMenuItem,
    override val itemView: View,
    private val iconView: ImageView,
    private val backgroundCornerRadius: Float,
    private val backgroundOpacity: Float,
    @ColorInt private val backgroundColor: Int,
    @ColorInt private val inactiveColorRipple: Int,
    private val itemBackgroundFactory: ItemBackgroundFactory
): ItemViewController() {

    override fun select() {
        itemView.background = itemBackgroundFactory.createHighlightedMenu(
            activeColor = menuItem.activeColor,
            rippleColor = menuItem.activeRippleColor,
            cornerRadius = backgroundCornerRadius,
            opacity = backgroundOpacity
        )
        iconView.isSelected = true
        itemView.isSelected = true
    }

    override fun deselect() {
        itemView.background = itemBackgroundFactory.createHighlightedMenu(
            activeColor = backgroundColor,
            rippleColor = inactiveColorRipple,
            cornerRadius = backgroundCornerRadius,
            opacity = backgroundOpacity
        )
        iconView.isSelected = false
        itemView.isSelected = false
    }
}
