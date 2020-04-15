package github.com.st235.lib_expandablebottombar.controllers

import android.view.View
import android.widget.ImageView
import android.widget.TextView
import github.com.st235.lib_expandablebottombar.ExpandableBottomBarMenuItem
import github.com.st235.lib_expandablebottombar.utils.BackgroundFactory

internal class TextItemViewController(
    override val menuItem: ExpandableBottomBarMenuItem,
    override val itemView: View,
    private val textView: TextView,
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
        textView.visibility = View.VISIBLE
        textView.isSelected = true
        iconView.isSelected = true
        itemView.isSelected = true
    }

    override fun deselect() {
        itemView.background = null
        textView.visibility = View.GONE
        textView.isSelected = false
        iconView.isSelected = false
        itemView.isSelected = false
    }
}