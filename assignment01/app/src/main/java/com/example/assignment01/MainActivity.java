package com.example.assignment01;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    Button btnshow;
    Button btnhome;
    EditText edtext;
    RadioButton btncorn;
    RadioButton btnTiramisu;
    ImageView image;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        getSupportActionBar().setDisplayUseLogoEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        getSupportActionBar().setIcon(R.drawable.ic_launcher);

        edtext = (EditText)findViewById(R.id.edtext);
        btnshow = (Button)findViewById(R.id.btnshow);
        btnhome = (Button)findViewById(R.id.btnhome);
        btncorn = (RadioButton)findViewById(R.id.btncorn);
        btnTiramisu = (RadioButton)findViewById(R.id.btnTiramisu);
        image=(ImageView)findViewById(R.id.image);

        btnshow.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                Toast.makeText(getApplicationContext(), edtext.getText(),Toast.LENGTH_SHORT).show();
            }
        });

        btnhome.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
            String url = edtext.getText().toString();
            Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse(url));
            startActivity(intent);
            }
        });

        btncorn.setOnClickListener(new View.OnClickListener(){
            public void onClick(View view){
                image.setImageResource(R.drawable.snow_corn);
            }
        });

        btnTiramisu.setOnClickListener(new View.OnClickListener(){
            public void onClick(View view){
                image.setImageResource(R.drawable.tiramisu01);
            }
        });
    }
}