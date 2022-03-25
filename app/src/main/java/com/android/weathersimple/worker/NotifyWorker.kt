package com.android.weathersimple.worker

import android.app.PendingIntent
import android.content.Context
import android.content.Intent
import androidx.core.app.NotificationCompat
import androidx.core.app.NotificationManagerCompat
import androidx.hilt.work.HiltWorker
import androidx.work.CoroutineWorker
import androidx.work.WorkerParameters
import com.android.weathersimple.R
import com.android.weathersimple.domain.model.WeatherDomain
import com.android.weathersimple.domain.usecase.GetAllWeatherDataFromApiUseCase
import com.android.weathersimple.domain.usecase.GetWeatherDataFromDbUseCase
import com.android.weathersimple.domain.usecase.InsertWeatherDataToDbUseCase
import com.android.weathersimple.ui.MainActivity
import com.android.weathersimple.utils.App
import com.android.weathersimple.utils.Constants.CHANNEL_ID
import dagger.assisted.Assisted
import dagger.assisted.AssistedInject
import kotlinx.coroutines.flow.first

@HiltWorker
class NotifyWorker @AssistedInject constructor(
    @Assisted context: Context,
    @Assisted workerParameters: WorkerParameters,
    private val getAllWeatherDataFromApiUseCase: GetAllWeatherDataFromApiUseCase,
    private val insertWeatherDataToDbUseCase: InsertWeatherDataToDbUseCase,
    private val getWeatherDataFromDbUseCase: GetWeatherDataFromDbUseCase

) : CoroutineWorker(context, workerParameters) {
    override suspend fun doWork(): Result {
        val data = getAllWeatherDataFromApiUseCase.execute()
        insertWeatherDataToDbUseCase.execute(data)
        getWeatherDataFromDbUseCase.execute().first().filter {
            it.isFavourite
        }.forEach {
            if ((applicationContext as App).isAppBackgrounded) {
                showWeatherNotification(it)
            }
        }
        return Result.success()
    }

    private fun showWeatherNotification(weatherDomain: WeatherDomain) {
        val main = "Current Temperature : ${weatherDomain.temp} \u2103 \nWeather : ${weatherDomain.weatherName}"

        val contentIntent = PendingIntent.getActivity(
            applicationContext, 0,
            Intent(applicationContext, MainActivity::class.java), PendingIntent.FLAG_IMMUTABLE
        )
        val builder = NotificationCompat.Builder(applicationContext, CHANNEL_ID)
            .setContentTitle("${weatherDomain.city} weather update")
            .setSmallIcon(R.drawable.ic_cloud)
            .setStyle(
                NotificationCompat.BigTextStyle()
                    .bigText(main)
            )
            .setContentText(main)
            .setAutoCancel(true)
            .setContentIntent(contentIntent)
            .setPriority(NotificationCompat.PRIORITY_DEFAULT)

        with(NotificationManagerCompat.from(applicationContext)) {
            // notificationId is a unique int for each notification that you must define
            notify(weatherDomain.id, builder.build())
        }
    }
}
