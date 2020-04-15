package github.com.st235.lib_expandablebottombar.controllers.factory

import android.content.Context
import android.view.Gravity
import android.widget.FrameLayout
import android.widget.LinearLayout
import github.com.st235.lib_expandablebottombar.ExpandableBottomBarMenuItem
import github.com.st235.lib_expandablebottombar.R
import github.com.st235.lib_expandablebottombar.controllers.ItemViewController
import github.com.st235.lib_expandablebottombar.controllers.IconOnlyItemViewController
import github.com.st235.lib_expandablebottombar.utils.ItemBackgroundFactory

internal class ShortItemViewControllerFactory(
    menuItem: ExpandableBottomBarMenuItem,
    itemBackgroundFactory: ItemBackgroundFactory
): ViewControllerFactory(menuItem, itemBackgroundFactory) {

    override fun build(context: Context): ItemViewController {
        val itemView = createFrameLayout(context)
        val iconView = createAppCompatImageView(context)

        with(itemView) {
            addView(iconView, createIconLayoutParams())
            setOnClickListener(onItemClickListener)
        }

        return IconOnlyItemViewController(
            menuItem = menuItem,
            itemView = itemView,
            iconView = iconView,
            backgroundColor = backgroundColor,
            inactiveColorRipple = inactiveRippleColor,
            backgroundCornerRadius = backgroundCornerRadius,
            backgroundOpacity = backgroundOpacity,
            itemBackgroundFactory = itemBackgroundFactory
        )
    }

    private fun createFrameLayout(context: Context) = FrameLayout(context).apply {
        id = menuItem.itemId
        setPadding(itemHorizontalPadding, itemVerticalPadding, itemHorizontalPadding, itemVerticalPadding)
        contentDescription = context.resources.getString(R.string.accessibility_item_description, menuItem.text)
        isFocusable = true
    }

    private fun createIconLayoutParams() = LinearLayout.LayoutParams(
        LinearLayout.LayoutParams.WRAP_CONTENT,
        LinearLayout.LayoutParams.WRAP_CONTENT
    ).apply {
        gravity = Gravity.CENTER
    }
}
