package com.cristian.voicereminder.alarm
import android.app.*
import android.content.*
import android.media.RingtoneManager
import android.os.*
import androidx.core.app.NotificationCompat
import com.cristian.voicereminder.data.ReminderStore
import java.util.Calendar

class AlarmReceiver:BroadcastReceiver(){
 override fun onReceive(c:Context,i:Intent){val msg=i.getStringExtra("message")?:("Tienes un recordatorio.");val nm=c.getSystemService(NotificationManager::class.java);if(Build.VERSION.SDK_INT>=26)nm.createNotificationChannel(NotificationChannel("reminders","Recordatorios",NotificationManager.IMPORTANCE_HIGH));nm.notify(i.getLongExtra("id",0).toInt(),NotificationCompat.Builder(c,"reminders").setSmallIcon(android.R.drawable.ic_lock_idle_alarm).setContentTitle("Voice Reminder").setContentText(msg).setPriority(NotificationCompat.PRIORITY_MAX).setAutoCancel(true).build());RingtoneManager.getRingtone(c,RingtoneManager.getDefaultUri(RingtoneManager.TYPE_ALARM)).play();TTSManager(c).speak(msg);val id=i.getLongExtra("id",0);val repeat=i.getStringExtra("repeat")?:"Una vez";if(repeat!="Una vez"){val r=ReminderStore(c).all().find{it.id==id};if(r!=null)AlarmScheduler(c).schedule(r.id,r.hour,r.minute,r.repeat,r.message)}}}
