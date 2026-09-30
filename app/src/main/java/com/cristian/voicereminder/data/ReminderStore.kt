package com.cristian.voicereminder.data
import android.content.Context
import org.json.JSONArray
import org.json.JSONObject

class ReminderStore(context: Context) {
 private val p=context.getSharedPreferences("reminders",Context.MODE_PRIVATE)
 fun all(): List<Reminder> { val a=JSONArray(p.getString("data","[]")); return (0 until a.length()).map { val o=a.getJSONObject(it); Reminder(o.getLong("id"),o.getString("title"),o.getString("message"),o.getInt("hour"),o.getInt("minute"),o.getString("repeat"),o.getBoolean("enabled")) } }
 fun save(r: Reminder) { val list=all().filterNot{it.id==r.id}+r; val a=JSONArray(); list.forEach{a.put(JSONObject().apply{put("id",it.id);put("title",it.title);put("message",it.message);put("hour",it.hour);put("minute",it.minute);put("repeat",it.repeat);put("enabled",it.enabled)})}; p.edit().putString("data",a.toString()).apply() }
 fun delete(id:Long){saveDelete(id)}
 private fun saveDelete(id:Long){val a=JSONArray();all().filterNot{it.id==id}.forEach{a.put(JSONObject().apply{put("id",it.id);put("title",it.title);put("message",it.message);put("hour",it.hour);put("minute",it.minute);put("repeat",it.repeat);put("enabled",it.enabled)})};p.edit().putString("data",a.toString()).apply()}
}
