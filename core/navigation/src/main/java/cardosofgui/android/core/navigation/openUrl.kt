package cardosofgui.android.core.navigation

import android.content.Context
import android.content.Intent
import android.net.Uri

fun Context.openUrl(url: String) = openUri(Uri.parse(url))

fun Context.openUri(uri: Uri) = Intent(Intent.ACTION_VIEW, uri)