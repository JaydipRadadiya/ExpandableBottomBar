package github.com.st235.lib_expandablebottombar.controllers

import android.view.View
import android.widget.ImageView
import android.widget.TextView
import androidx.annotation.ColorInt
import github.com.st235.lib_expandablebottombar.ExpandableBottomBarMenuItem
import github.com.st235.lib_expandablebottombar.utils.ItemBackgroundFactory

internal class TextItemViewController(
    override val menuItem: ExpandableBottomBarMenuItem,
    override val itemView: View,
    private val textView: TextView,
    private val iconView: ImageView,
    @ColorInt  private val backgroundColor: Int,
    @ColorInt private val inactiveColorRipple: Int,
    private val backgroundCornerRadius: Float,
    private val backgroundOpacity: Float,
    private val itemBackgroundFactory: ItemBackgroundFactory
): ItemViewController() {

    override fun select() {
        itemView.background = itemBackgroundFactory.createHighlightedMenu(
            activeColor = menuItem.activeColor,
            rippleColor = menuItem.activeRippleColor,
            cornerRadius = backgroundCornerRadius,
            opacity = backgroundOpacity
        )
        textView.visibility = View.VISIBLE
        textView.isSelected = true
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
        textView.visibility = View.GONE
        textView.isSelected = false
        iconView.isSelected = false
        itemView.isSelected = false
    }
}