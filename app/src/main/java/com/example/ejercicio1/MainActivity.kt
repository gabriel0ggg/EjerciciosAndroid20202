package com.example.ejercicio1

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.speech.tts.TextToSpeech
import android.util.Log
import android.widget.Button
import android.widget.TextView
import java.util.*

class MainActivity : AppCompatActivity(), TextToSpeech.OnInitListener {
    var textToSpeech : TextToSpeech? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        textToSpeech = TextToSpeech(this, this)

        //var message = findViewById<TextView>(R.id.textViewMessage).text.toString()
        //Log.i("Mensaje ", message)
        findViewById<Button>(R.id.buttonPlayText).setOnClickListener{
            speakMessage()
        }
    }

    fun speakMessage () {
        var message = findViewById<TextView>(R.id.editTextMessage).text.toString()
        if (message.isEmpty()) {
            findViewById<TextView>(R.id.textViewMessage).text = "Introduce algo"
            message = "Introduce algo"
        }
        textToSpeech!!.speak(message, TextToSpeech.QUEUE_FLUSH, null, "")
    }

    override fun onInit(status: Int) {
        if (status == TextToSpeech.SUCCESS) {
            findViewById<TextView>(R.id.textViewMessage).text = "Si estoy"
            textToSpeech!!.setLanguage(Locale("ES"))
        }
        else {
            findViewById<TextView>(R.id.textViewMessage).text = "No estoy"
        }
    }

    override fun onDestroy() {
        if (textToSpeech != null) {
            textToSpeech!!.stop()
            textToSpeech!!.shutdown()
        }
        super.onDestroy()
    }
}