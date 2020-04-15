package github.com.st235.lib_expandablebottombar.controllers

import android.view.View
import android.widget.ImageView
import github.com.st235.lib_expandablebottombar.ExpandableBottomBarMenuItem
import github.com.st235.lib_expandablebottombar.utils.BackgroundFactory

internal class IconOnlyItemViewController(
    override val menuItem: ExpandableBottomBarMenuItem,
    override val itemView: View,
    private val iconView: ImageView,
    private val backgroundCornerRadius: Float,
    private val backgroundOpacity: Float,
    private val backgroundFactory: BackgroundFactory
): ItemViewController() {

    override fun select() {
        itemView.background = backgroundFactory.createHighlightedMenu(
            activeColor = menuItem.activeColor,
            backgroundCornerRadius = backgroundCornerRadius,
            backgroundOpacity = backgroundOpacity
        )
        iconView.isSelected = true
        itemView.isSelected = true
    }

    override fun deselect() {
        itemView.background = null
        iconView.isSelected = false
        itemView.isSelected = false
    }
}
