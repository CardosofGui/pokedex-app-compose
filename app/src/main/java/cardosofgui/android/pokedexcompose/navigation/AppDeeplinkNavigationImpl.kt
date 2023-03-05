package cardosofgui.android.pokedexcompose.navigation

import android.app.Activity
import android.content.Context
import android.content.Intent
import android.os.Bundle
import cardosofgui.android.core.navigation.AppDeeplinkNavigation
import cardosofgui.android.core.navigation.AppRoute
import cardosofgui.android.core.navigation.openUrl
import cardosofgui.android.pokedexcompose.deeplink.DeepLinkDelegate

class AppDeeplinkNavigationImpl(
    private val deepLinkDelegate: DeepLinkDelegate
): AppDeeplinkNavigation {
    override fun navigate(
        context: Context,
        route: AppRoute,
        bundle: Bundle?
    ) {
        val intent = context.openUrl("${route.prefix}${route.path}").apply {
            (bundle?:route.bundle)?.let { putExtras(it) }
        }
        dispatchIntent(
            context = context,
            intent = intent
        )
    }

    private fun dispatchIntent(
        context: Context,
        intent: Intent
    ){
        deepLinkDelegate.dispatchFrom(context as Activity, intent)
    }
}