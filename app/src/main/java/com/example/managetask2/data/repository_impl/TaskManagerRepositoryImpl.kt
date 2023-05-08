package com.example.managetask2.data.repository_impl

import android.app.AlarmManager
import android.app.PendingIntent
import android.content.Context
import android.content.Intent
import com.example.managetask2.constants.Constants.REMINDER_NOTIFICATION_REQUEST_CODE
import com.example.managetask2.data.database.TaskerMangerDao
import com.example.managetask2.data.entity.TaskData
import com.example.managetask2.domain.repository.TaskManagerRepository
import com.example.managetask2.services.AlarmReceiver
import com.google.gson.Gson
import java.util.Calendar
import java.util.Locale

class TaskManagerRepositoryImpl(
    private val dao: TaskerMangerDao,
) : TaskManagerRepository {

    override fun getAllTasks(): List<TaskData> = dao.getAllTasks()

    override suspend fun addTask(data: TaskData) = dao.addTasks(data)
    override fun setDailyReminder(context: Context, reminderTime: String, taskData: TaskData, reminderId: Int) {
        if (taskData.repeat == "Daily") {
            val alarmManager = context.getSystemService(Context.ALARM_SERVICE) as AlarmManager
            val (hours, mins) = reminderTime.split(":").map { it.toInt() }
            val intent =
                Intent(context.applicationContext, AlarmReceiver::class.java).let { intent ->
                    intent.putExtra("taskData",Gson().toJson(taskData))
                    PendingIntent.getBroadcast(
                        context.applicationContext,
                        REMINDER_NOTIFICATION_REQUEST_CODE,
                        intent,
                        PendingIntent.FLAG_UPDATE_CURRENT
                    )
                }

            val calender: Calendar = Calendar.getInstance(Locale.ENGLISH).apply {
                set(Calendar.HOUR_OF_DAY, hours)
                set(Calendar.MINUTE, mins)
            }

            if (Calendar.getInstance(Locale.ENGLISH)
                    .apply { add(Calendar.MINUTE, 1) }.timeInMillis - calender.timeInMillis > 0
            ) {
                calender.add(Calendar.DATE, 1)
            }

            alarmManager.setAlarmClock(
                AlarmManager.AlarmClockInfo(calender.timeInMillis, intent), intent
            )

        }
    }

    override fun cancelDailyReminder(
        context: Context, reminderId: Int
    ) {
        val alarmManager = context.getSystemService(Context.ALARM_SERVICE) as AlarmManager
        val intent = Intent(context, AlarmReceiver::class.java).let { intent ->
            PendingIntent.getBroadcast(
                context,
                REMINDER_NOTIFICATION_REQUEST_CODE,
                intent,
                PendingIntent.FLAG_IMMUTABLE or PendingIntent.FLAG_UPDATE_CURRENT
            )
        }

        alarmManager.cancel(intent)
    }

    //override suspend fun getTaskById(id: Int): TaskData = dao.getTaskById(id)

    //override fun getByDate(date: String): List<TaskData> = dao.getCategory(date)

    //override fun getImportant(important: Boolean): List<TaskData> = dao.getImportant(important)
}

//    override fun getAllBusinessTasks(): List<TaskData>{
//        return dao.getAllTasks().filter { it.category == CategoryType.BUSINESS.name }
//    }
//
//    override fun getAllHealthTasks(): List<TaskData> {
//        return dao.getAllTasks().filter { it.category == CategoryType.HEALTH.name }
//    }
//
//    override fun getAllEntertainmentTasks(): List<TaskData> {
//        return dao.getAllTasks().filter { it.category == CategoryType.ENTERTAINMENT.name }
//    }
//
//    override fun getAllHomeTasks(): List<TaskData> {
//        return dao.getAllTasks().filter { it.category == CategoryType.HOME.name }
//    }