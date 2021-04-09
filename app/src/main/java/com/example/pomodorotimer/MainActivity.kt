package com.example.pomodorotimer

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.CountDownTimer
import android.util.Log
import android.widget.Button
import android.widget.TextView
import java.text.DecimalFormat
import java.util.*
import kotlin.concurrent.timer
import kotlin.system.measureTimeMillis

class MainActivity : AppCompatActivity() {


    var millisUntilPaused : Long = 0
    var millisInFuture : Long = 1500000
    var time        = Timer(millisInFuture)


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val btn_play    = findViewById<Button>(R.id.btn_play)
        val btn_stop    = findViewById<Button>(R.id.btn_stop)
        var hasStarted  = false
        var isPaused    = false
        var isStopped   = true

        btn_play.setOnClickListener {
            if(hasStarted){
                isPaused = true
                btn_play.text = "Start"
                Timer(millisUntilPaused)
                Log.d("ahoba",millisUntilPaused.toString())
                time.cancel()

            }
            if(!hasStarted){
                hasStarted = true
                isStopped  = false
                btn_play.text = "Pause"
                time.start()
            }

        }
        btn_stop.setOnClickListener {
            if(!isStopped){
                isStopped  = true
                hasStarted = false
                btn_play.text = "Play"
                time.cancel()
            }
        }
    }
    inner class Timer(millisInFuture: Long): CountDownTimer(millisInFuture, 1000){
        override fun onTick(millisUntilFinished: Long) {
            val txt_counter = findViewById<TextView>(R.id.txt_counter)
            millisUntilPaused = millisUntilFinished
            val format = DecimalFormat("00")
            var minutes = millisUntilFinished/60000
            var seconds = (millisUntilFinished%60000)/1000
            txt_counter.text = "${format.format(minutes)}:${format.format(seconds)}"
        }

        override fun onFinish() {
            TODO("Not yet implemented")
        }

    }
}
