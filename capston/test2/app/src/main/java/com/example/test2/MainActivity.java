package com.example.test2;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.AlarmManagerCompat;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;

import java.util.Calendar;

public class MainActivity extends AppCompatActivity {
    int lastId = 0;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //Receiver receiver = new Receiver();


        // PendingIntent 생성,PendingIntent는 알람마다 1개씩 다 있어야 함. 따라서 고유 id를 가짐. 그 고유 id가 lastId
        Intent intent = new Intent(getApplicationContext(), Receiver.class);
        PendingIntent pendingIntent = PendingIntent.getBroadcast(getApplicationContext(), lastId++, intent, PendingIntent.FLAG_IMMUTABLE);

        Intent activityIntent = new Intent(getApplicationContext(), MainActivity.class);
        PendingIntent showPendingIntent = PendingIntent.getActivity(getApplicationContext(), lastId++, activityIntent, PendingIntent.FLAG_IMMUTABLE);

        AlarmManager cleenerStartAlarm = (AlarmManager) getSystemService(Context.ALARM_SERVICE);


        // 에디트 텍스트에 입력한 시 분 을 받아와서 정수로 바꿔줌


//        reservationDates.add(new ReservationDate(month, day, hour, minute, lastId));
//        ReservationDate reservationDate = new ReservationDate(month, day, hour, minute, lastId);

        int month = 5;
        int day = 12;
        int hour = 12;
        int minute = 36;

        Calendar calendar = Calendar.getInstance();
        System.currentTimeMillis();
        calendar.set(Calendar.MONTH, month);
        calendar.set(Calendar.DAY_OF_MONTH, day);
        calendar.set(Calendar.HOUR, hour);
        calendar.set(Calendar.AM_PM, Calendar.AM);
        calendar.set(Calendar.MINUTE, minute);


        android.util.Log.i("예약 시간 점검", calendar.get(Calendar.YEAR)  + "");
        android.util.Log.i("예약 시간 점검", calendar.get(Calendar.MONTH)  + "");
        android.util.Log.i("예약 시간 점검", day  + "");
        android.util.Log.i("예약 시간 점검", hour  + "");
        android.util.Log.i("예약 시간 점검", minute  + "\n******************************\n");



        //AlarmManager.AlarmClockInfo tmp = new AlarmManager.AlarmClockInfo(calendar.getTimeInMillis(), pendingIntent);
        AlarmManagerCompat.setAlarmClock(cleenerStartAlarm, calendar.getTimeInMillis(), showPendingIntent,pendingIntent);
        //cleenerStartAlarm.setAlarmClock(tmp, pendingIntent);


    }
}