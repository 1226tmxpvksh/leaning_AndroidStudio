package com.example.assignment11_05;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

public class ResultActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.result);
        getSupportActionBar().setDisplayUseLogoEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        getSupportActionBar().setIcon(R.drawable.ic_launcher);
        setTitle("연습문제 11-6");

        Intent intent = getIntent();
        int[] voteResult = intent.getIntArrayExtra("VoteCount");
        String[] imageName = intent.getStringArrayExtra("ImageName");

        int highest = 0;

        TextView tv[] = new TextView[imageName.length];
        RatingBar rbar[] = new RatingBar[imageName.length];

        Integer tvID[] = {R.id.tv1, R.id.tv2, R.id.tv3, R.id.tv4, R.id.tv5, R.id.tv6, R.id.tv7, R.id.tv8, R.id.tv9};
        Integer rbarID[] = {R.id.rbar1, R.id.rbar2, R.id.rbar3, R.id.rbar4, R.id.rbar5, R.id.rbar6, R.id.rbar7, R.id.rbar8, R.id.rbar9};
        Integer imageFileID[] = { R.drawable.pic1, R.drawable.pic2, R.drawable.pic3, R.drawable.pic4, R.drawable.pic5, R.drawable.pic6, R.drawable.pic7, R.drawable.pic8, R.drawable.pic9};

        for(int i = 0; i<voteResult.length; i++){
            tv[i] = (TextView) findViewById(tvID[i]);
            rbar[i] = (RatingBar) findViewById(rbarID[i]);
        }

        for(int i = 0; i<voteResult.length; i++){
            tv[i].setText(imageName[i]);
            rbar[i].setRating((float) voteResult[i]);
            if(voteResult[i]>voteResult[highest]){
                highest = i;
            }
        }

        TextView imgTitle = (TextView) findViewById(R.id.imgTitle);
        ImageView imgView = (ImageView) findViewById(R.id.imgview);

        // 1등 그림 이름과 그림 파일을 보여준다.
        int maxEntry = 0;
        for (int i = 1; i < voteResult.length; i++) {
            if (voteResult[maxEntry] < voteResult[i])
                maxEntry = i;
        }
        imgView.setImageResource(imageFileID[maxEntry]);
        imgTitle.setText(imageName[highest]);
        imgView.setImageResource(imageFileID[highest]);

        Button btnReturn = (Button) findViewById(R.id.btnReturn);
        btnReturn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }
}