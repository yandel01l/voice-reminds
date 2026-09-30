# Voice Reminder Pro
Android nativo en Kotlin. No es una web/PWA.

## Qué incluye
- Recordatorios locales.
- Alarma exacta cuando Android lo permite.
- Notificación de alta prioridad.
- Sonido de alarma.
- Text-to-Speech en español de Colombia.
- Persistencia local.
- Reprogramación después de reinicio/cambio de hora.
- Interfaz móvil simple.
- GitHub Actions para compilar el APK sin Android Studio.

## GitHub
Crea un repositorio vacío llamado `VoiceReminderPro`, sube estos archivos y activa Actions. Cada push a `main` genera un artefacto `VoiceReminder-debug`.

```bash
git init
git add .
git commit -m "Voice Reminder Pro"
git branch -M main
git remote add origin https://github.com/TU_USUARIO/VoiceReminderPro.git
git push -u origin main
```

## Nota Android
En algunos teléfonos hay que permitir notificaciones, alarmas exactas y quitar restricciones de batería para que los recordatorios funcionen de forma fiable con la pantalla apagada. Android controla esos permisos.
