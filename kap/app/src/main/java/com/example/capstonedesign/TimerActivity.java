package com.example.capstonedesign;

import android.app.AlarmManager;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.NotificationCompat;
import androidx.core.app.NotificationManagerCompat;

import android.content.DialogInterface;
import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.Toast;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;
import java.util.Timer;
import java.util.TimerTask;
import java.util.regex.Matcher;
import java.util.regex.Pattern;


public class TimerActivity extends AppCompatActivity {
    //출력함수
    static void sop(String a) {
        System.out.println("----------------------------------------------------------------------------------------------");
        System.out.println(a);
        System.out.println("----------------------------------------------------------------------------------------------");
    }

    static void sop(int a) {
        System.out.println("----------------------------------------------------------------------------------------------");
        System.out.println(a);
        System.out.println("----------------------------------------------------------------------------------------------");
    }


    private static final String message = "청소를 시작합니다."; // 알람 메시지
    //시간과 분을 입력받는 텍스트
    EditText aclock_text;
    EditText minute_text;
    //버튼을 추가할 레이아웃
    LinearLayout reservation_layout;
    //확인 버튼
    Button check;
    //취소 버튼
    Button Back;
    //버튼을 저장할 배열
    Button[] time_button = new Button[30];

    //버튼 배열 인덱스
    int count = 0;


    //시간을 저장할 배열
    int aclock[] = new int[30];
    //분을 저장할 배열
    int minit[] = new int[30];
    //켈린더에서 날짜를 클릭 했을 때 현재 몇월 몇일이 클릭 되었는지 받아오는 변수
    int month = 0;
    int day = 0;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.timer);

        // 클릭된 날짜를 받아옴
        Intent In = getIntent();

        month = In.getIntExtra("mon", 0);
        day = In.getIntExtra("day", 0);

        aclock_text = (EditText) findViewById(R.id.aclock_text);
        minute_text = (EditText) findViewById(R.id.minute_text);
        reservation_layout = (LinearLayout) findViewById(R.id.reservation_layout);
        check = (Button) findViewById(R.id.check);
        Back = (Button) findViewById(R.id.cancellation);

        //레이아웃에 마진을 주기 위해서 레이아웃 파라미터 지정
        LinearLayout.LayoutParams param = new LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.MATCH_PARENT,
                200);
        param.topMargin = 10;
        param.leftMargin = 30;
        param.rightMargin = 30;

        //대화상자 배열 생성
        AlertDialog[] Back_Text = new AlertDialog[30];
        //버튼 삭제 대화상자 생성

        //시작할 때 버튼 만들기
        try {
            String A = Integer.toString(month) + "월" + Integer.toString(day) + "일.txt";

            FileInputStream inFs = openFileInput(A);
            byte[] text = new byte[1000];
            inFs.read(text);
            String file_text = new String(text);
            String B;
            B = file_text;

            //토큰을 사용하여 문자열을 나눠줌
            String[] aaa = file_text.split(",");
            //aclock과 minit 배열에 시간과 분을 넣어줌
            for (String token : aaa) {
                //마지막에 널을 출력하지 않게 하기위한 if문 )의 인덱스를 비교함
                if (token.indexOf(")") != -1) {
                    //버튼을 생성
                    Button btn = new Button(TimerActivity.this);
                    btn.setText(token);
                    btn.setLayoutParams(param);
                    //button_style 에서 물결효과와 색상을 받아옴
                    btn.setBackgroundResource(R.drawable.button_style);
                    reservation_layout.addView(btn);
                    time_button[count] = btn;

                    //시간을 측정해서 시간 분 배열에 넣어줌
                    String[] aa = token.split("분 ");

                    int setcount = 0;

                    for (String tk : aa) {


                        if (tk.indexOf("시") != -1) {
                            String[] r = tk.split("시");
                            for (String Q : r) {

                                if (setcount % 2 == 0) {
                                    aclock[count] = Integer.parseInt(Q);

                                } else {
                                    minit[count] = Integer.parseInt(Q);

                                }
                                setcount += 1;
                            }
                        }
                    }

                    count += 1;
                }


            }
        } catch (IOException e) {
            //어떤 오류인지 확인하기 위한 코드

            e.printStackTrace();
        }

        //삭제 기능 추가
        for (int i = 0; i < 30; i++) {
            System.out.println(i);
            if (time_button[i] != null) {
                int a = i;
                time_button[i].setOnClickListener(new View.OnClickListener() {
                    @Override

                    public void onClick(View view) {
                        //대화상자 빌터를 만들어 텍스트와 "네" 버튼을 만들어줌
                        AlertDialog.Builder Bil = new AlertDialog.Builder(TimerActivity.this);
                        Bil.setTitle(aclock[a] + "시" + minit[a] + "분 삭제가 완료되었습니다.");

                        //파일에서 삭제할 객체 찾아서 파일에서 삭제-----------------------------------------------------------
                        String cub3 = "";
                        String A = Integer.toString(month) + "월" + Integer.toString(day) + "일.txt";
                        try {
                            FileInputStream inFs = openFileInput(A);
                            byte[] text = new byte[1000];
                            inFs.read(text);
                            String file_text = new String(text);

                            String[] tok = file_text.split(",");
                            String cub2 = "";
                            //-------------------------------작업중-----------------------------------------
                            for (String Aa : tok) {

                                String cub = aclock[a] + "시" + minit[a] + "분 (터치하면 삭제) ";


                                if (!Aa.equals(cub)) {
                                    cub2 += Aa + ",";

                                }
                                cub3 = cub2;
                            }

                            String fileName = Integer.toString(month) + "월" + Integer.toString(day) + "일.txt";
                            FileOutputStream fileOutputStream = openFileOutput(fileName, Context.MODE_PRIVATE); // 파일 을 읽어옴
                            //주의사항 NULL값이 먼저오게 저장하면 저장되어 있던 문자열을 식별하지 못함
                            fileOutputStream.write(cub3.getBytes());
                            fileOutputStream.close();

                        } catch (IOException e) {
                        }


                        Bil.setPositiveButton("네", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {
                                time_button[a].setVisibility(View.GONE);
                                count -= 1;
                            }
                        });

                        Back_Text[a] = Bil.create();

                        Back_Text[a].show();
                    }
                });
            }
        }


        check.setOnClickListener(new View.OnClickListener() {

            //파일 의 문자열을 저장할 문자열
            String file;

            @Override
            public void onClick(View view) {
                //파일에서 읽어오기
                try {
                    String A = Integer.toString(month) + "월" + Integer.toString(day) + "일.txt";
                    FileInputStream inFs = openFileInput(A);
                    byte[] text = new byte[1000];
                    inFs.read(text);
                    String file_text = new String(text);

                    file = file_text;

                } catch (IOException e) {
                }

                String text_clock = "";
                text_clock = aclock_text.getText().toString();
                String text_minit = "";
                text_minit = minute_text.getText().toString();
                count += 1;


                //버튼 생성해서 time_button 에 넣어줌
                //!= null 과 != ""이 안됨
                if (text_clock.length() != 0 && text_minit.length() != 0) {

                    if (Integer.parseInt(text_clock) > 0 && Integer.parseInt(text_clock) < 25
                            && Integer.parseInt(text_minit) > 0 && Integer.parseInt(text_minit) < 61
                    ) {
                        Button btn = new Button(TimerActivity.this);
                        btn.setText(text_clock + "시" + text_minit + "분 (터치하면 삭제)");
                        btn.setLayoutParams(param);
                        //button_style 에서 물결효과와 색상을 받아옴
                        btn.setBackgroundResource(R.drawable.button_style);
                        reservation_layout.addView(btn);
                        time_button[count] = btn;
                        aclock[count] = Integer.parseInt(text_clock);
                        minit[count] = Integer.parseInt(text_minit);


                        // 파일에 시간 분 저장
                        try {

                            String fileName = Integer.toString(month) + "월" + Integer.toString(day) + "일.txt";
                            FileOutputStream fileOutputStream = openFileOutput(fileName, Context.MODE_PRIVATE); // 파일 을 읽어옴
                            //주의사항 NULL값이 먼저오게 저장하면 저장되어 있던 문자열을 식별하지 못함
                            String time = text_clock + "시" + text_minit + "분 (터치하면 삭제) ," + file + "";
                            fileOutputStream.write(time.getBytes());
                            fileOutputStream.close();
                        } catch (IOException e) {

                        }


                    }
                }

                //버튼 삭제
                if (text_clock.length() != 0 && text_minit.length() != 0) {
                    if (Integer.parseInt(text_clock) > 0 && Integer.parseInt(text_clock) < 25
                            && Integer.parseInt(text_minit) > 0 && Integer.parseInt(text_minit) < 61
                    ) {
                        for (int i = 0; i < 30; i++) {
                            System.out.println(i);
                            if (time_button[i] != null) {
                                int a = i;
                                time_button[i].setOnClickListener(new View.OnClickListener() {
                                    @Override

                                    public void onClick(View view) {
                                        //대화상자 빌터를 만들어 텍스트와 "네" 버튼을 만들어줌
                                        AlertDialog.Builder Bil = new AlertDialog.Builder(TimerActivity.this);
                                        Bil.setTitle("삭제가 완료되었습니다.");

                                        //파일에서 삭제할 객체 찾아서 파일에서 삭제-----------------------------------------------------------
                                        String cub3 = "";
                                        String A = Integer.toString(month) + "월" + Integer.toString(day) + "일.txt";
                                        try {
                                            FileInputStream inFs = openFileInput(A);
                                            byte[] text = new byte[1000];
                                            inFs.read(text);
                                            String file_text = new String(text);
                                            String[] tok = file_text.split(",");
                                            String cub2 = "";
                                            //-------------------------------작업중-----------------------------------------
                                            for (String Aa : tok) {
                                                String cub = aclock[a] + "시" + minit[a] + "분 (터치하면 삭제) ";

                                                if (!Aa.equals(cub)) {
                                                    cub2 += Aa + ",";

                                                }
                                                cub3 = cub2;
                                            }

                                            String fileName = Integer.toString(month) + "월" + Integer.toString(day) + "일.txt";
                                            FileOutputStream fileOutputStream = openFileOutput(fileName, Context.MODE_PRIVATE); // 파일 을 읽어옴
                                            //주의사항 NULL값이 먼저오게 저장하면 저장되어 있던 문자열을 식별하지 못함
                                            fileOutputStream.write(cub3.getBytes());
                                            fileOutputStream.close();

                                        } catch (IOException e) {
                                        }


                                        Bil.setPositiveButton("네", new DialogInterface.OnClickListener() {
                                            @Override
                                            public void onClick(DialogInterface dialogInterface, int i) {
                                                time_button[a].setVisibility(View.GONE);
                                                count -= 1;
                                            }
                                        });

                                        Back_Text[a] = Bil.create();

                                        Back_Text[a].show();
                                    }
                                });

                            }
                        }

                    }

                }




            }
        });

        Back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(TimerActivity.this, CalenderActivity.class);
                startActivity(intent);
                //화면이동
            }
        });

        class  ReservationDate{
            int month;
            int day;
            int hour;
            int minute;
            int alarmId;
        }

        //-----------------------------------------------------------푸쉬알림과 토스트 메세지----------------------------------------




    }
    public void alarmSett() {
        try {
            // 파일 이름에서 월과 날짜 추출
            String fileName = Integer.toString(month) + "월" + Integer.toString(day) + "일.txt";
            String[] dateTokens = fileName.split("월|일");
            int month = Integer.parseInt(dateTokens[0]);
            int day = Integer.parseInt(dateTokens[1]);

            FileInputStream inFs = openFileInput(fileName);
            byte[] text = new byte[1000];
            inFs.read(text);
            String fileText = new String(text);

            ArrayList<String> hourTokens = new ArrayList<>();
            ArrayList<String> minuteTokens = new ArrayList<>();

            // 파일 내용에서 시간 추출
            String[] tokens = fileText.split(",");

            for (String token : tokens) {
                String trimmedToken = token.trim();

                // 시간 정보를 포함하는 문자열인 경우에만 hourTokens와 minuteTokens에 저장
                if (trimmedToken.contains("시") && trimmedToken.contains("분")) {
                    String hourToken = trimmedToken.substring(0, trimmedToken.indexOf("시") + 1);
                    String minuteToken = trimmedToken.substring(trimmedToken.indexOf("시") + 1, trimmedToken.indexOf("분") + 1);

                    hourTokens.add(hourToken);
                    minuteTokens.add(minuteToken);
                }
            }
            android.util.Log.i("변수 확인 date", Arrays.toString(dateTokens));
            android.util.Log.i("변수 확인 hour", hourTokens.toString());
            android.util.Log.i("변수 확인 minute", minuteTokens.toString());
            android.util.Log.i("변수 확인 month", dateTokens[0].toString());
            android.util.Log.i("변수 확인 day", dateTokens[1].toString());

            // 현재 날짜와 시간 확인하여 조건을 충족할 경우에만 푸시 알림 및 토스트 메시지 표시
            Calendar calendar = Calendar.getInstance();
            int currentMonth = calendar.get(Calendar.MONTH) + 1; // Calendar.MONTH는 0부터 시작하므로 +1을 해줍니다.
            int currentDay = calendar.get(Calendar.DAY_OF_MONTH);
            int currentHour = calendar.get(Calendar.HOUR_OF_DAY);
            int currentMinute = calendar.get(Calendar.MINUTE);

            // 월과 날짜가 일치하는지 확인
            if (currentMonth == month && currentDay == day) {
                int loopLength = Math.min(hourTokens.size(), minuteTokens.size());

                for (int i = 0; i < loopLength; i++) {
                    String hourToken = hourTokens.get(i);
                    String minuteToken = minuteTokens.get(i);

                    // hourToken 또는 minuteToken이 null인 경우에는 해당 인덱스를 건너뛰고 다음 인덱스로 이동
                    if (hourToken == null || minuteToken == null) {
                        break;
                    }

                    // 시간과 분 문자열에서 숫자만 추출
                    String hourString = hourToken.replaceAll("[^0-9]", "");
                    String minuteString = minuteToken.replaceAll("[^0-9]", "");

                    android.util.Log.i("변수 확인 최종 hour", hourString.toString());
                    android.util.Log.i("변수 확인 최종 minute", minuteString.toString());

                    int hour = Integer.parseInt(hourString);
                    int minute = Integer.parseInt(minuteString);

                    // 시간이 일치하는지 확인하여 푸시 알림과 토스트 메시지 표시
                    if (currentHour == hour && currentMinute == minute) {
                        showNotification(message, getApplicationContext());
                        showToast(message);
                    }
                }
            }
        } catch (IOException | NumberFormatException e) {
            e.printStackTrace();
        }
    }

    public void getDateForAlarm(ArrayList<Integer>reservationMonthList, ArrayList<Integer>reservationDayList
                                , ArrayList<Integer>reservationHourList , ArrayList<Integer>reservationMinuteList){
        try {
            // 파일 이름에서 월과 날짜 추출
            String fileName = Integer.toString(month) + "월" + Integer.toString(day) + "일.txt";
            String[] dateTokens = fileName.split("월|일");
            int month = Integer.parseInt(dateTokens[0]);
            int day = Integer.parseInt(dateTokens[1]);

            FileInputStream inFs = openFileInput(fileName);
            byte[] text = new byte[1000];
            inFs.read(text);
            String fileText = new String(text);

            ArrayList<String> hourTokens = new ArrayList<>();
            ArrayList<String> minuteTokens = new ArrayList<>();

            // 파일 내용에서 시간 추출
            String[] tokens = fileText.split(",");

            for (String token : tokens) {
                String trimmedToken = token.trim();

                // 시간 정보를 포함하는 문자열인 경우에만 hourTokens와 minuteTokens에 저장
                if (trimmedToken.contains("시") && trimmedToken.contains("분")) {
                    String hourToken = trimmedToken.substring(0, trimmedToken.indexOf("시") + 1);
                    String minuteToken = trimmedToken.substring(trimmedToken.indexOf("시") + 1, trimmedToken.indexOf("분") + 1);

                    hourTokens.add(hourToken);
                    minuteTokens.add(minuteToken);
                }
            }
            android.util.Log.i("변수 확인 date", Arrays.toString(dateTokens));
            android.util.Log.i("변수 확인 hour", hourTokens.toString());
            android.util.Log.i("변수 확인 minute", minuteTokens.toString());
            android.util.Log.i("변수 확인 month", dateTokens[0].toString());
            android.util.Log.i("변수 확인 day", dateTokens[1].toString());

            ArrayList<Integer> hourList = new ArrayList<Integer>();
            ArrayList<Integer> minuteList = new ArrayList<Integer>();

            for (int i = 0; i < hourTokens.size(); i++) {
                // 시간과 분 문자열에서 숫자만 추출
                hourList.add(Integer.parseInt(hourTokens.get(i).replaceAll("[^0-9]", "")));
                minuteList.add(Integer.parseInt(minuteTokens.get(i).replaceAll("[^0-9]", "")));
            }

            android.util.Log.i("변수 확인 최종 hour", hourList.toString());
            android.util.Log.i("변수 확인 최종 minute", minuteList.toString());

            reservationHourList = (ArrayList<Integer>) hourList.clone();
            reservationMinuteList = (ArrayList<Integer>) minuteList.clone();

        } catch (IOException | NumberFormatException e) {
            e.printStackTrace();
        }
    }

    private void showToast(String message) {
        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                Toast.makeText(TimerActivity.this, message, Toast.LENGTH_SHORT).show();
            }
        });
    }

    private static final String CHANNEL_ID = "timer_channel";

    private void createNotificationChannel() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            CharSequence name = "Timer Channel";
            String description = "Channel for Timer Notifications";
            int importance = NotificationManager.IMPORTANCE_DEFAULT;
            NotificationChannel channel = new NotificationChannel(CHANNEL_ID, name, importance);
            channel.setDescription(description);
            NotificationManager notificationManager = getSystemService(NotificationManager.class);
            notificationManager.createNotificationChannel(channel);
        }
    }

    private void showNotification(String message, Context context) {
        // Create the notification channel
        createNotificationChannel();

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



