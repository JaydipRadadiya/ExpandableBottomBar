package github.com.st235.lib_expandablebottombar.controllers.factory

import android.content.Context
import android.graphics.Typeface
import android.text.Spannable
import android.text.SpannableString
import android.text.style.StyleSpan
import android.view.Gravity
import android.view.View
import android.widget.LinearLayout
import androidx.appcompat.widget.AppCompatTextView
import github.com.st235.lib_expandablebottombar.ExpandableBottomBarMenuItem
import github.com.st235.lib_expandablebottombar.R
import github.com.st235.lib_expandablebottombar.controllers.TextItemViewController
import github.com.st235.lib_expandablebottombar.controllers.ItemViewController
import github.com.st235.lib_expandablebottombar.utils.BackgroundFactory
import github.com.st235.lib_expandablebottombar.utils.toPx

internal class DefaultViewControllerFactory(
    menuItem: ExpandableBottomBarMenuItem,
    backgroundFactory: BackgroundFactory
) : ViewControllerFactory(menuItem, backgroundFactory) {

    override fun build(context: Context): ItemViewController {
        val itemView = createLinearLayout(context)
        val iconView = createAppCompatImageView(context)
        val textView = createTextView(context)
        val textLayoutParams = createTextLayoutParams()

        with(itemView) {
            addView(iconView)
            addView(textView, textLayoutParams)
            setOnClickListener(onItemClickListener)
        }

        return TextItemViewController(
            menuItem = menuItem,
            itemView = itemView,
            textView = textView,
            iconView = iconView,
            backgroundCornerRadius = backgroundCornerRadius,
            backgroundOpacity = backgroundOpacity,
            backgroundFactory = backgroundFactory
        )
    }

    private fun createLinearLayout(context: Context) = LinearLayout(context).apply {
        id = menuItem.itemId
        orientation = LinearLayout.HORIZONTAL
        setPadding(itemHorizontalPadding, itemVerticalPadding, itemHorizontalPadding, itemVerticalPadding)
        contentDescription = context.resources.getString(R.string.accessibility_item_description, menuItem.text)
        isFocusable = true
    }

    private fun createTextView(context: Context) = AppCompatTextView(context).apply {
        val rawText = SpannableString(menuItem.text)
        rawText.setSpan(StyleSpan(Typeface.BOLD), 0, rawText.length, Spannable.SPAN_EXCLUSIVE_EXCLUSIVE)
        setTextColor(backgroundColorSelector)
        text = rawText
        gravity = Gravity.CENTER
        visibility = View.GONE
        textSize = 15F
    }

    private fun createTextLayoutParams() = LinearLayout.LayoutParams(
        LinearLayout.LayoutParams.MATCH_PARENT,
        LinearLayout.LayoutParams.MATCH_PARENT
    ).apply {
        gravity = Gravity.CENTER
        setMargins(8.toPx(), 0, 0, 0)
    }
}
