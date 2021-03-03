package com.example.exercise

import android.app.*
import android.content.Context
import android.content.Intent
import android.graphics.BitmapFactory
import android.graphics.Color
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    lateinit var notificationChannel: NotificationChannel
    lateinit var notificationManager: NotificationManager
    lateinit var builder: Notification.Builder
    private val channelId = "12345"
    private val description = "Test Notification"
    var resultui:Double=0.0
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        notificationManager = getSystemService(Context.NOTIFICATION_SERVICE) as
                NotificationManager


        var first=""
        var second=""
        operate.isClickable=false

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
                btnNotify(it)
            }



        }




    }

    fun btnNotify(view: View) {
        val intent = Intent(this, MainActivity::class.java)
        val pendingIntent = PendingIntent.getActivity(this, 0, intent, PendingIntent.FLAG_UPDATE_CURRENT)
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            notificationChannel = NotificationChannel(channelId, description, NotificationManager .IMPORTANCE_HIGH)
            notificationChannel.lightColor = Color.BLUE
            notificationChannel.enableVibration(true)
            notificationManager.createNotificationChannel(notificationChannel)
            builder = Notification.Builder(this, channelId).setContentTitle("THe result of the operation is $resultui").setContentText("Test Notification").setSmallIcon(R.drawable.ic_launcher_background).setLargeIcon(BitmapFactory.decodeResource(this.resources, R.drawable
                    .ic_launcher_background)).setContentIntent(pendingIntent)
        }
        notificationManager.notify(12345, builder.build())
    }
}