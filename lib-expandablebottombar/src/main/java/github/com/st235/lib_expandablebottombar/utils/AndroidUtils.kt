package github.com.st235.lib_expandablebottombar.utils

import android.os.Build
import android.view.View

typealias Scope<T> = () -> T

internal inline fun <T> applyOnApi(
    apiLevel: Int,
    aboveScope: Scope<T>,
    belowScope: Scope<T>
): T {
    return if (Build.VERSION.SDK_INT < apiLevel) {
        belowScope()
    } else {
        aboveScope()
    }
}

internal inline fun applyForApiLAndHigher(scope: Scope<Unit>) {
    if (Build.VERSION.SDK_INT > Build.VERSION_CODES.LOLLIPOP) {
        scope()
    }
}

internal fun View.show(isShown: Boolean = false, viewHiddenState: Int = View.GONE) {
    if (isShown) {
        this.visibility = View.VISIBLE
    } else {
        this.visibility = viewHiddenState
    }
}
