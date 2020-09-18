package com.example.timer

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.os.Message
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    lateinit var handler:Handler

    lateinit var runnable: Runnable

    var currentms = 0

    var isStop = true


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        handler = Handler()

        setRunnable()

        setAction ()



    }


    override fun onPause() {
        super.onPause()

       if(!isStop){

           isStop = true

          Toast.makeText(this,"計時暫停",Toast.LENGTH_SHORT).show()}

    }

    override fun onDestroy() {
        super.onDestroy()

        if(!isStop) isStop = true

        handler.removeCallbacksAndMessages(null)
    }


    fun setAction (){


        start_button.setOnClickListener { startTimer () }
        
        stop_button.setOnClickListener { stopTimer () }



    }


    fun setRunnable(){

        runnable = object :Runnable{

            override fun run() {

                if(isStop)  handler.removeCallbacks(runnable)

                else {
                    currentms++
                    setCurrentTime()
                    handler.postDelayed(runnable,1000)
                }

            }


        }

    }


    fun startTimer (){

        isStop = false

        handler.postDelayed(runnable,1000)



    }

    fun stopTimer (){

        isStop = true

        resetTime ()

    }


    fun setCurrentTime(){

       val m =  currentms/60

       val s = currentms -60 * m

        val mStr = if(m >= 10) "$m" else "0$m"
        val sStr = if(s >= 10) "$s" else "0$s"

        textView.setText(mStr+" : "+sStr)


    }

    fun resetTime (){

        currentms = 0

        textView.setText("00 : 00")

    }



}
