package cardosofgui.android.core.navigation

import android.content.Context
import android.os.Bundle

interface AppDeeplinkNavigation {
    fun navigate(
        context: Context,
        route: AppRoute,
        bundle: Bundle? = null
    )
}