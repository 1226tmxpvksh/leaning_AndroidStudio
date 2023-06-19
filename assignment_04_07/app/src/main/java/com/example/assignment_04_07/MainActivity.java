package com.example.assignment_04_07;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;

public class MainActivity extends AppCompatActivity {

    CheckBox chB1, chB2,chB3 ;
    Button btn1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        getSupportActionBar().setDisplayUseLogoEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        getSupportActionBar().setIcon(R.drawable.ic_launcher);

        chB1 = (CheckBox)findViewById(R.id.checkBox1);
        chB2 = (CheckBox)findViewById(R.id.checkBox2);
        chB3 = (CheckBox)findViewById(R.id.checkBox3);
        btn1 = (Button)findViewById(R.id.button1);

        chB1.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                if(chB1.isChecked()==true){
                    btn1.setEnabled(true);
                }
                else{
                    btn1.setEnabled(false);
                }
            }
        });

        chB2.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                if(chB1.isChecked()==true){
                    btn1.setClickable(true);
                }
                else{
                    btn1.setClickable(false);
                }
            }
        });

        chB3.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                if(chB1.isChecked()==true){
                    btn1.setRotation(45);
                }
                else{
                    btn1.setRotation(0);
                }
            }
        });

    }
}