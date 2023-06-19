package com.example.cap;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.TimePicker;

public class MainActivity extends AppCompatActivity {

    Button change, alarm, reset, information;
    LinearLayout linearLayout;
    TimePicker tPicker1;
    DatePicker dPicker1;
    RadioGroup alram1;
    TextView textView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);





        change.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                tPicker1.setVisibility(View.VISIBLE);
                dPicker1.setVisibility(View.VISIBLE);
            }
        });

        information.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                TextView textView = new TextView(getApplicationContext());
                textView.setText("새로운 내용");
                linearLayout.addView(textView);
            }
        });
    }
}