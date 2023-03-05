package cardosofgui.android.pokedexcompose.feature.details.navigation

import android.content.Context
import android.content.Intent
import android.os.Bundle
import cardosofgui.android.core.navigation.AppDeepLink
import cardosofgui.android.core.navigation.routes.DetailsRoutes
import cardosofgui.android.pokedexcompose.feature.details.ui.DetailsMainActivity

object DetailsNavigation {
    @JvmStatic
    @AppDeepLink(DetailsRoutes.DetailsRoutes)
    fun openDetails(context: Context, bundle: Bundle): Intent {
        return DetailsMainActivity.getStartIntent(context).apply {
            putExtras(bundle)
        }
    }
}