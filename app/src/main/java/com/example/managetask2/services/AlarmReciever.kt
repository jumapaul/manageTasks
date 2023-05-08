package com.example.managetask2.services

import android.app.Notification
import android.app.NotificationManager
import android.app.PendingIntent
import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import androidx.core.app.NotificationCompat
import androidx.core.content.ContextCompat
import com.example.managetask2.R
import com.example.managetask2.constants.Constants.NOTIFICATION_ID
import com.example.managetask2.data.entity.TaskData
import com.example.managetask2.domain.repository.TaskManagerRepository
import com.example.managetask2.domain.use_cases.DailyReminderUseCase
import com.example.managetask2.presentation.MainActivity
import com.google.gson.Gson
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class AlarmReceiver @Inject constructor(
    repository: TaskManagerRepository
) : BroadcastReceiver() {

    private val dailyReminderUseCase = DailyReminderUseCase(repository)
    override fun onReceive(context: Context, intent: Intent?) {
        val taskDataString = intent?.getStringExtra("taskData")
        val taskData = Gson().fromJson(taskDataString, TaskData::class.java)
        val notificationManager = ContextCompat.getSystemService(
            context, NotificationManager::class.java
        ) as NotificationManager

        notificationManager.sendReminderNotification(
            applicationContext = context,
            channelId = context.getString(R.string.reminder_notification_id)
        )
        dailyReminderUseCase.executeDailyReminder(context.applicationContext, "", taskData, 0)
    }

    fun NotificationManager.sendReminderNotification(
        applicationContext: Context,
        channelId: String
    ) {
        val contentIntent = Intent(applicationContext, MainActivity::class.java)
        val pendingIntent = PendingIntent.getActivity(
            applicationContext, 1, contentIntent, PendingIntent.FLAG_UPDATE_CURRENT
        )

        val builder = Notification.Builder(applicationContext, channelId)
            .setContentTitle(applicationContext.getString(R.string.notification_title))
            .setContentText(applicationContext.getString(R.string.new_notification))
            .setSmallIcon(R.drawable.baseline_help_24)
            .setContentIntent(pendingIntent).setAutoCancel(true)

        notify(NOTIFICATION_ID, builder.build())
    }
}