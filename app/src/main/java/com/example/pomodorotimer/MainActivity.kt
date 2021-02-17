package com.example.pomodorotimer

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.CountDownTimer
import android.widget.Button
import android.widget.TextView
import kotlin.concurrent.timer

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val btn_play    = findViewById<Button>(R.id.btn_play)
        val btn_stop    = findViewById<Button>(R.id.btn_stop)
        val txt_counter = findViewById<TextView>(R.id.txt_counter)

        var hasStarted = false
        var isPaused = false

        val timer = object: CountDownTimer(1500000,1000){
            override fun onTick(millisUntilFinished: Long){
                var minutes = millisUntilFinished/60000
                var seconds = (millisUntilFinished%60000)/1000
                txt_counter.text = "${minutes.toString()}:${seconds.toString()}"
            }
            override fun onFinish() {
                TODO("Not yet implemented")
            }


        }

        btn_play.setOnClickListener {
            if (!hasStarted) {
                hasStarted = true
                timer.start()
            } else if (!isPaused) {
                isPaused = true
                timer.cancel()
                TODO("Pausar/Pause")
            } else {
                isPaused = false
                TODO("Retomar/Resume")
            }
        }
        btn_stop.setOnClickListener {
            //timer.cancel()
            hasStarted = false
            isPaused = false
            timer.onTick(0)
        }

    }
    fun play_time(timer: CountDownTimer){
        timer.start()
    }
}