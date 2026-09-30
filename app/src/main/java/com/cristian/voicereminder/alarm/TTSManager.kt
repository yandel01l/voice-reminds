package com.cristian.voicereminder.alarm
import android.content.Context
import android.speech.tts.TextToSpeech
import java.util.Locale

class TTSManager(context:Context):TextToSpeech.OnInitListener{
 private val tts=TextToSpeech(context.applicationContext,this); private var text=""
 fun speak(value:String){text=value;if(tts.isSpeaking)tts.stop();tts.speak(value,TextToSpeech.QUEUE_FLUSH,null,"vr")}
 override fun onInit(status:Int){if(status==TextToSpeech.SUCCESS){tts.language=Locale("es","CO");if(text.isNotBlank())tts.speak(text,TextToSpeech.QUEUE_FLUSH,null,"vr")}}
}
