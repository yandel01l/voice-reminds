package com.cristian.voicereminder.data

data class Reminder(val id: Long, val title: String, val message: String, val hour: Int, val minute: Int, val repeat: String, val enabled: Boolean)
