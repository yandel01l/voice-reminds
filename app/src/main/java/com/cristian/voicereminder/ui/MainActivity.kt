package com.cristian.voicereminder.ui
import android.Manifest
import android.app.*
import android.content.*
import android.os.*
import android.provider.Settings
import android.view.*
import android.widget.*
import com.cristian.voicereminder.alarm.*
import com.cristian.voicereminder.data.*
import java.util.*

class MainActivity:Activity(){
 lateinit var store:ReminderStore;lateinit var scheduler:AlarmScheduler;lateinit var list:LinearLayout
 override fun onCreate(b:Bundle?){super.onCreate(b);store=ReminderStore(this);scheduler=AlarmScheduler(this);build();if(Build.VERSION.SDK_INT>=33)requestPermissions(arrayOf(Manifest.permission.POST_NOTIFICATIONS),10)}
 fun build(){val root=LinearLayout(this).apply{orientation=LinearLayout.VERTICAL;padding(24)};val title=TextView(this).apply{text="Voice Reminder";textSize=30f;setTypeface(null,1)};root.addView(title);val add=Button(this).apply{text="＋ Nuevo recordatorio";setOnClickListener{dialog()}};root.addView(add);val test=Button(this).apply{text="🔊 Probar voz";setOnClickListener{TTSManager(this@MainActivity).speak("Hola Cristian. Esta es una prueba de Voice Reminder.")}};root.addView(test);list=LinearLayout(this).apply{orientation=LinearLayout.VERTICAL};root.addView(list,LinearLayout.LayoutParams(-1,0,1f));val settings=Button(this).apply{text="⚙ Ajustes y permisos";setOnClickListener{try{startActivity(Intent(Settings.ACTION_REQUEST_SCHEDULE_EXACT_ALARM))}catch(_:Exception){}}};root.addView(settings);setContentView(root);refresh()}
 fun refresh(){list.removeAllViews();store.all().forEach{r->val row=LinearLayout(this).apply{orientation=LinearLayout.HORIZONTAL;padding=8};val t=TextView(this).apply{text="%02d:%02d  ${r.title}\n${r.message}\n${r.repeat}".format(r.hour,r.minute);textSize=17f};row.addView(t,LinearLayout.LayoutParams(0,-2,1f));val del=Button(this).apply{text="Eliminar";setOnClickListener{scheduler.cancel(r.id);store.delete(r.id);refresh()}};row.addView(del);list.addView(row)}}
 fun dialog(){val box=LinearLayout(this).apply{orientation=LinearLayout.VERTICAL;padding=20};val title=EditText(this).apply{hint="Título"};val msg=EditText(this).apply{hint="Mensaje que dirá la voz"};box.addView(title);box.addView(msg);val d=AlertDialog.Builder(this).setTitle("Nuevo recordatorio").setView(box).setPositiveButton("Hora"){_,_->time(title.text.toString(),msg.text.toString())}.setNegativeButton("Cancelar",null).create();d.show()}
 fun time(title:String,msg:String){val n=Calendar.getInstance();TimePickerDialog(this,{_,h,m->val id=System.currentTimeMillis();val repeat="Una vez";store.save(Reminder(id,title.ifBlank{"Recordatorio"},msg.ifBlank{"Tienes una tarea pendiente."},h,m,repeat,true));scheduler.schedule(id,h,m,repeat,msg);refresh();Toast.makeText(this,"Programado para %02d:%02d".format(h,m),Toast.LENGTH_SHORT).show()},n.get(Calendar.HOUR_OF_DAY),n.get(Calendar.MINUTE),true).show()}
}
