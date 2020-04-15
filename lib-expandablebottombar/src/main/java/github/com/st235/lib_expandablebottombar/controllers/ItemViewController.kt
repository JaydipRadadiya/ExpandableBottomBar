package github.com.st235.lib_expandablebottombar.controllers

import android.view.View
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.constraintlayout.widget.ConstraintSet
import androidx.core.view.AccessibilityDelegateCompat
import androidx.core.view.ViewCompat
import androidx.core.view.accessibility.AccessibilityNodeInfoCompat
import github.com.st235.lib_expandablebottombar.ExpandableBottomBarMenuItem
import github.com.st235.lib_expandablebottombar.utils.createChain

internal abstract class ItemViewController {

    abstract val menuItem: ExpandableBottomBarMenuItem

    protected abstract val itemView: View

    abstract fun select()

    abstract fun deselect()

    fun attachTo(
            parent: ConstraintLayout,
            previousIconId: Int,
            nextIconId: Int,
            menuItemHorizontalMargin: Int,
            menuItemVerticalMargin: Int
    ) {
        val lp = ConstraintLayout.LayoutParams(
                ConstraintLayout.LayoutParams.WRAP_CONTENT, ConstraintLayout.LayoutParams.WRAP_CONTENT
        )

        lp.setMargins(menuItemHorizontalMargin, menuItemVerticalMargin,
                menuItemHorizontalMargin, menuItemVerticalMargin)

        parent.addView(itemView, lp)

        val cl = ConstraintSet()
        cl.clone(parent)

        cl.connect(itemView.id, ConstraintSet.TOP, parent.id, ConstraintSet.TOP)
        cl.connect(itemView.id, ConstraintSet.BOTTOM, parent.id, ConstraintSet.BOTTOM)

        if (previousIconId == itemView.id) {
            cl.connect(itemView.id, ConstraintSet.START, parent.id, ConstraintSet.START)
        } else {
            cl.connect(itemView.id, ConstraintSet.START, previousIconId, ConstraintSet.END)
            cl.createChain(previousIconId, itemView.id, ConstraintSet.CHAIN_PACKED)
        }

        if (nextIconId == itemView.id) {
            cl.connect(itemView.id, ConstraintSet.END, parent.id, ConstraintSet.END)
        } else {
            cl.connect(itemView.id, ConstraintSet.END, nextIconId, ConstraintSet.START)
            cl.createChain(itemView.id, nextIconId, ConstraintSet.CHAIN_PACKED)
        }

        cl.applyTo(parent)
    }

    fun setAccessibleWith(
            prev: ItemViewController?,
            next: ItemViewController?
    ) {
        ViewCompat.setAccessibilityDelegate(itemView, object : AccessibilityDelegateCompat() {
            override fun onInitializeAccessibilityNodeInfo(
                    host: View?,
                    info: AccessibilityNodeInfoCompat?
            ) {
                info?.setTraversalAfter(prev?.itemView)
                info?.setTraversalBefore(next?.itemView)
                super.onInitializeAccessibilityNodeInfo(host, info)
            }
        })
    }
}