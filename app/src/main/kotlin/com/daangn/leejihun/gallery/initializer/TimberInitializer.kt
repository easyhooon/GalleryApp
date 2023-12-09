package com.daangn.leejihun.gallery.initializer

import android.content.Context
import androidx.startup.Initializer
import com.daangn.leejihun.gallery.BuildConfig
import timber.log.Timber

// TODO buildType 으로 변경(release 로 빌드 시 아예 해당 class 가 호출되지 않도록)
class TimberInitializer : Initializer<Unit> {

    override fun create(context: Context) {
        if (BuildConfig.DEBUG) {
            Timber.plant(Timber.DebugTree())
        }
    }

    override fun dependencies(): List<Class<out Initializer<*>>> {
        return emptyList()
    }
}
