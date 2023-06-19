package com.example.capstonedesign;

import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.NotificationCompat;

public class AlarmActivity extends AppCompatActivity {
    Button alarm,alram_yes,alram_no,alram_time;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.alarm);

        alarm = (Button) findViewById(R.id.Alram);
        alram_time=(Button) findViewById(R.id.Alram_Time);
        alram_yes=(Button) findViewById(R.id.Alram_Yes);
        alram_no=(Button) findViewById(R.id.Alram_No);

        alram_yes.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(getApplicationContext(),"청소를 시작합니다",Toast.LENGTH_SHORT).show();
            }
        });
        alram_no.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(getApplicationContext(),"청소를 시작하지 않습니다",Toast.LENGTH_SHORT).show();
            }
        });

        alram_time.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(AlarmActivity.this, CalenderActivity.class);
                startActivity(intent);
            }
        });


        alarm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // 알림에 사용될 큰 아이콘을 비트맵으로 디코딩
                Bitmap mLargeIconForNoti = BitmapFactory.decodeResource(getResources(), R.drawable.setting);

                // 알림을 클릭했을 때 실행될 액티비티를 위한 PendingIntent 생성
                PendingIntent mPendingIntent = PendingIntent.getActivity(AlarmActivity.this, 0,
                        new Intent(getApplicationContext(), AlarmActivity.class),
                        PendingIntent.FLAG_CANCEL_CURRENT | PendingIntent.FLAG_IMMUTABLE
                );

                // 알림을 생성하기 위한 빌더 객체 생성
                NotificationCompat.Builder mBuilder = new NotificationCompat.Builder(AlarmActivity.this)
                        .setSmallIcon(R.drawable.setting) // 작은 아이콘 설정
                        .setContentTitle("청소기") // 알림 제목 설정
                        .setContentText("청소 예약") // 알림 내용 설정
                        .setDefaults(Notification.DEFAULT_VIBRATE) // 알림 진동 설정
                        .setLargeIcon(mLargeIconForNoti) // 큰 아이콘 설정
                        .setPriority(NotificationCompat.PRIORITY_DEFAULT) // 알림 우선순위 설정
                        .setAutoCancel(true) // 알림을 클릭했을 때 자동으로 알림 제거
                        .setContentIntent(mPendingIntent); // 알림 클릭 시 실행될 PendingIntent 설정

                // Android 8.0 (Oreo) 이상 버전에서는 알림 채널 설정
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
                    String channelId = "channel_id"; // 알림 채널 ID
                    CharSequence channelName = "Channel Name"; // 알림 채널 이름
                    int importance = NotificationManager.IMPORTANCE_DEFAULT; // 알림 중요도 설정

                    // 알림 채널 객체 생성
                    NotificationChannel notificationChannel = new NotificationChannel(channelId, channelName, importance);
                    NotificationManager notificationManager = getSystemService(NotificationManager.class);
                    notificationManager.createNotificationChannel(notificationChannel);

                    // 알림 빌더에 알림 채널 ID 설정
                    mBuilder.setChannelId(channelId);
                }

                // 알림 매니저를 통해 알림 생성
                NotificationManager mNotificationManager = (NotificationManager) getSystemService(NOTIFICATION_SERVICE);
                mNotificationManager.notify(0, mBuilder.build());
            }
        });


    }
}
