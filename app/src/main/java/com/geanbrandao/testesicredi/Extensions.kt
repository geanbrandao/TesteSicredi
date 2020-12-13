package com.geanbrandao.testesicredi

import android.app.Activity
import android.content.Intent

fun Activity.goToActivity(activityClass: Class<*>) {
    startActivity(Intent(this, activityClass))
}
