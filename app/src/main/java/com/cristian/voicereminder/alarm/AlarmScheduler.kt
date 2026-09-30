package com.cristian.voicereminder.alarm
import android.app.*
import android.content.*
import java.util.Calendar

class AlarmScheduler(private val c:Context){
 private val am=c.getSystemService(AlarmManager::class.java)
 fun schedule(id:Long,hour:Int,minute:Int,repeat:String,message:String){cancel(id);val cal=Calendar.getInstance().apply{set(Calendar.HOUR_OF_DAY,hour);set(Calendar.MINUTE,minute);set(Calendar.SECOND,0);set(Calendar.MILLISECOND,0);if(timeInMillis<=System.currentTimeMillis())add(Calendar.DAY_OF_YEAR,1)};val pi=pending(id,message,repeat);am.setExactAndAllowWhileIdle(AlarmManager.RTC_WAKEUP,cal.timeInMillis,pi)}
 fun cancel(id:Long){am.cancel(pending(id,"",""))}
 private fun pending(id:Long,msg:String,repeat:String)=PendingIntent.getBroadcast(c,id.toInt(),Intent(c,AlarmReceiver::class.java).apply{putExtra("id",id);putExtra("message",msg);putExtra("repeat",repeat)},PendingIntent.FLAG_UPDATE_CURRENT or PendingIntent.FLAG_IMMUTABLE)
}
