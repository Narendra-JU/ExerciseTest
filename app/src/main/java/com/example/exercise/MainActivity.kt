package com.example.exercise

import android.app.*
import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.NotificationCompat
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    lateinit var notificationChannel: NotificationChannel
    lateinit var notificationManager: NotificationManager
    lateinit var builder: Notification.Builder

    var resultui:Double=0.0
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)


        var first=""
        var second=""
        

        if(firstnumber.text.isNullOrEmpty() || secondno.text.isNullOrEmpty()){
            Toast.makeText(this, "Enter Both Fields", Toast.LENGTH_SHORT).show()
        }
        else{
            plus.setOnClickListener {
                first=firstnumber.text.toString()
                second=secondno.text.toString()
                resultui=(first.toDouble())+(second.toDouble())
                operate.isClickable=true


            }
            minus.setOnClickListener {
                first=firstnumber.text.toString()
                second=secondno.text.toString()
                resultui=(first.toDouble())-(second.toDouble())
                operate.isClickable=true


            }
            multiply.setOnClickListener {
                first=firstnumber.text.toString()
                second=secondno.text.toString()
                resultui=(first.toDouble())-(second.toDouble())
                operate.isClickable=true


            }
            division.setOnClickListener {
                first=firstnumber.text.toString()
                second=secondno.text.toString()
                resultui=(first.toDouble())/(second.toDouble())
                operate.isClickable=true
            }

            operate.setOnClickListener {
                result.text=resultui.toString()

                addNotification(resultui.toString())
            }



        }




    }

    private fun addNotification(str:String) {
        val builder = NotificationCompat.Builder(this)
                .setSmallIcon(R.drawable.ic_launcher_background) //set icon for notification
                .setContentTitle("Result") //set title of notification
                .setContentText("The result of the operation is $str") //this is notification message
                .setAutoCancel(false) // makes auto cancel of notification
                .setPriority(NotificationCompat.PRIORITY_DEFAULT) //set priority of notification
        val notificationIntent = Intent(this, NotificationView::class.java)
        notificationIntent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP)
        //notification message will get at NotificationView
        notificationIntent.putExtra("message", "This is a notification message")
        val pendingIntent = PendingIntent.getActivity(this, 0, notificationIntent,
                PendingIntent.FLAG_UPDATE_CURRENT)
        builder.setContentIntent(pendingIntent)
        // Add as notification
        val manager = getSystemService(NOTIFICATION_SERVICE) as NotificationManager
        manager.notify(0, builder.build())
    }
}