package com.android.weathersimple.utils

import android.app.Application
import android.app.NotificationChannel
import android.app.NotificationManager
import android.content.Context
import android.os.Build
import androidx.hilt.work.HiltWorkerFactory
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.LifecycleEventObserver
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.ProcessLifecycleOwner
import androidx.work.Configuration
import androidx.work.ExistingPeriodicWorkPolicy
import androidx.work.PeriodicWorkRequest
import androidx.work.WorkManager
import com.android.weathersimple.BuildConfig
import com.android.weathersimple.R
import com.android.weathersimple.utils.Constants.CHANNEL_ID
import com.android.weathersimple.utils.Constants.NOTIFICATION_TIME
import com.android.weathersimple.utils.Constants.NOTIFICATION_WORKER_TAG
import com.android.weathersimple.worker.NotifyWorker
import dagger.hilt.android.HiltAndroidApp
import timber.log.Timber
import java.util.concurrent.TimeUnit
import javax.inject.Inject

@HiltAndroidApp
class App : Application(), LifecycleEventObserver, Configuration.Provider {

    @Inject
    lateinit var workerFactory: HiltWorkerFactory

    var isAppBackgrounded: Boolean = false

    override fun onCreate() {
        super.onCreate()
        if (BuildConfig.DEBUG) {
            Timber.plant(Timber.DebugTree())
        }
        createNotificationChannel()
        setWorkRequest()
        ProcessLifecycleOwner.get().lifecycle.addObserver(this)
    }

    override fun getWorkManagerConfiguration() = Configuration.Builder()
        .setWorkerFactory(workerFactory)
        .build()

    private fun setWorkRequest() {
        val notificationWork = PeriodicWorkRequest
            .Builder(NotifyWorker::class.java, NOTIFICATION_TIME, TimeUnit.MINUTES)
            .build()
        WorkManager.getInstance(applicationContext).enqueueUniquePeriodicWork(NOTIFICATION_WORKER_TAG, ExistingPeriodicWorkPolicy.KEEP, notificationWork)
    }

    private fun createNotificationChannel() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            val name = getString(R.string.channel_name)
            val descriptionText = getString(R.string.channel_description)
            val importance = NotificationManager.IMPORTANCE_DEFAULT
            val channel = NotificationChannel(CHANNEL_ID, name, importance).apply {
                description = descriptionText
            }
            // Register the channel with the system
            val notificationManager: NotificationManager =
                getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
            notificationManager.createNotificationChannel(channel)
        }
    }

    override fun onStateChanged(source: LifecycleOwner, event: Lifecycle.Event) {
        Timber.e("SimpleWeather: $event")
        if (event == Lifecycle.Event.ON_START) {
            isAppBackgrounded = false
        } else if (event == Lifecycle.Event.ON_STOP) {
            isAppBackgrounded = true
        }
    }
}
