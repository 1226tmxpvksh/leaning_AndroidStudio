package com.example;

import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.os.Handler;
import android.os.Looper;
import android.widget.Toast;

import androidx.core.app.NotificationCompat;

public class Receiver extends BroadcastReceiver {
    private static final String message = "알람이 울립니다."; // 알람 메시지

    @Override
    public void onReceive(Context context, Intent intent) {
        android.util.Log.i("알람 확인용", "리시버 도착");
        showToast(message, context);
        showNotification(message, context);
        android.util.Log.i("알람 확인용", "메세지 출력 후");
    }

    ////////////////////////////////////////////////////////////////////////////////////////////// 토스트 메세지, 푸쉬알림 구현부////////////////////////////////////////////////////////////////////////////////////////////
    private void showToast(String message, Context context) {
        Handler handler = new Handler(Looper.getMainLooper());
        handler.post(new Runnable() {
            @Override
            public void run() {
                Toast.makeText(context, message, Toast.LENGTH_SHORT).show();
            }
        });
    }
    private static final String CHANNEL_ID = "timer_channel";

    private void createNotificationChannel(Context context) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            CharSequence name = "Timer Channel";
            String description = "Channel for Timer Notifications";
            int importance = NotificationManager.IMPORTANCE_DEFAULT;
            NotificationChannel channel = new NotificationChannel(CHANNEL_ID, name, importance);
            channel.setDescription(description);
            NotificationManager notificationManager = context.getSystemService(NotificationManager.class);
            notificationManager.createNotificationChannel(channel);
        }
    }

    private void showNotification(String message, Context context) {
        // Create the notification channel
        createNotificationChannel(context);

        // Set the unique notification ID
        int notificationId = 1;

        // Create the notification
        NotificationCompat.Builder builder = new NotificationCompat.Builder(context, CHANNEL_ID)
                .setSmallIcon(R.drawable.setting)
                .setContentTitle("청소기")
                .setContentText(message)
                .setPriority(NotificationCompat.PRIORITY_DEFAULT);

        // Get the notification manager
        NotificationManager notificationManager = (NotificationManager) context.getSystemService(Context.NOTIFICATION_SERVICE);

        if (notificationManager != null) {
            // Show the notification
            notificationManager.notify(notificationId, builder.build());
        }
    }

}