package cardosofgui.android.core.navigation

import com.airbnb.deeplinkdispatch.DeepLinkSpec

@DeepLinkSpec(prefix = [PREFIX_PATH])
annotation class AppDeepLink(vararg val value: String)
