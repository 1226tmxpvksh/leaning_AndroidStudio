package com.example;

import androidx.appcompat.app.AppCompatActivity;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

import java.util.Calendar;

public class MainActivity extends AppCompatActivity {
    int lastId = 0;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Receiver Receiver = new Receiver();

        // PendingIntent 생성,PendingIntent는 알람마다 1개씩 다 있어야 함. 따라서 고유 id를 가짐. 그 고유 id가 lastId
        PendingIntent pendingIntent = PendingIntent.getBroadcast(MainActivity.this, lastId, new Intent(MainActivity.this, Receiver.getClass()), PendingIntent.FLAG_IMMUTABLE);

        AlarmManager cleenerStartAlarm = (AlarmManager) getSystemService(Context.ALARM_SERVICE);

        // 에디트 텍스트에 입력한 시 분 을 받아와서 정수로 바꿔줌


//        reservationDates.add(new ReservationDate(month, day, hour, minute, lastId));
//        ReservationDate reservationDate = new ReservationDate(month, day, hour, minute, lastId);


        Calendar calendar = Calendar.getInstance();
        calendar.set(Calendar.MONTH, 5);
        calendar.set(Calendar.DAY_OF_MONTH, 11);
        calendar.set(Calendar.HOUR, 7);
        calendar.set(Calendar.AM_PM, Calendar.PM);
        calendar.set(Calendar.MINUTE, 5);
        calendar.set(Calendar.SECOND, 0);

        android.util.Log.i("예약 시간 점검", month  + "");
        android.util.Log.i("예약 시간 점검", day  + "");
        android.util.Log.i("예약 시간 점검", hour12  + "");
        android.util.Log.i("예약 시간 점검", minute  + "");

        cleenerStartAlarm.set(AlarmManager.RTC_WAKEUP, calendar.getTimeInMillis(), pendingIntent);
        lastId++;



    }
}