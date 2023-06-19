package com.example.assignment10_06;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ViewFlipper;

public class ResultActivity extends AppCompatActivity {

    ViewFlipper viewFlipper;
    Button autoStart;
    Button autoStop;

    private int currentImageIndex = 0;
    private boolean isAutoFlipping = false;
    int max;
    boolean pass = true;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.result);
        getSupportActionBar().setDisplayUseLogoEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        getSupportActionBar().setIcon(R.drawable.pici_icon);
        setTitle("투표 결과");

        viewFlipper = findViewById(R.id.viewFlipper1);
        autoStart = findViewById(R.id.autoStart);
        autoStop = findViewById(R.id.autoStop);

        Intent intent = getIntent();
        int[] voteResult = intent.getIntArrayExtra("VoteCount");
        String[] imageName = intent.getStringArrayExtra("ImageName");

        // 이미지 배열
        int[] imageIds = {
                R.drawable.pic1,
                R.drawable.pic2,
                R.drawable.pic3,
                R.drawable.pic4,
                R.drawable.pic5,
                R.drawable.pic6,
                R.drawable.pic7,
                R.drawable.pic8,
                R.drawable.pic9
        };

        int[] sortedIndex = new int[voteResult.length];
        // 이미지 등수대로 ViewFlipper에 추가
        for(int i = 0; i < voteResult.length; i++){
            max = -1;
            for(int  j = 0; j < voteResult.length; j++){
                pass = true;
                if(i == 0) {
                    if (voteResult[j] > max) {
                        sortedIndex[i] = j;
                        max = voteResult[j];
                    }
                }
                else{
                    for(int k = 0; k < i; k++) {
                        if (sortedIndex[k] == j)
                            pass = false;
                    }
                    if(voteResult[j] > max && pass) {
                        sortedIndex[i] = j;
                        max = voteResult[j];
                    }

                }

            }
        }
        for (int i : sortedIndex) {
            android.util.Log.i("인덱스 값", i + " ");
        }


        for (int i = 0; i < imageIds.length; i++) {
            ImageView imageView = new ImageView(this);
            imageView.setImageResource(imageIds[sortedIndex[i]]);
            imageView.setScaleType(ImageView.ScaleType.CENTER_CROP);
            viewFlipper.addView(imageView);
        }

        autoStart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startFlippingImages();
            }
        });

        autoStop.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                stopFlippingImages();
            }
        });
    }

    private void startFlippingImages() {
        if (!isAutoFlipping) {
            viewFlipper.setFlipInterval(1000); // 1초마다 이미지 전환
            viewFlipper.startFlipping();
            isAutoFlipping = true;

            autoStart.setVisibility(View.VISIBLE);
            autoStop.setVisibility(View.VISIBLE);
        }
    }

    private void stopFlippingImages() {
        if (isAutoFlipping) {
            viewFlipper.stopFlipping();
            isAutoFlipping = false;

            autoStop.setVisibility(View.VISIBLE);
            autoStart.setVisibility(View.VISIBLE);
        }
    }
}