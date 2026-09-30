package com.cristian.voicereminder.alarm
import android.content.*
import com.cristian.voicereminder.data.ReminderStore
class BootReceiver:BroadcastReceiver(){override fun onReceive(c:Context,i:Intent){val s=ReminderStore(c);val a=AlarmScheduler(c);s.all().filter{it.enabled}.forEach{a.schedule(it.id,it.hour,it.minute,it.repeat,it.message)}}}
