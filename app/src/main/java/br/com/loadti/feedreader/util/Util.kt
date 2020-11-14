package br.com.loadti.feedreader.util

import android.content.Context
import android.content.Intent
import android.content.pm.ResolveInfo

class Util {

    companion object {

        fun existeIntentView(intent: Intent, context: Context): Boolean {

            val activityes: List<ResolveInfo> =
                context.packageManager.queryIntentActivities(intent, 0)
            return activityes.isNotEmpty()

        }

    }
}