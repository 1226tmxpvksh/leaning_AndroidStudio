package com.example.assignment11_05;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    final int voteCount[] = new int[9];

    String imgName[] = { "독서하는 소녀", "꽃장식 모자 소녀", "부채를 든 소녀",
            "이레느깡 단 베르양", "잠자는 소녀", "테라스의 두 자매", "피아노 레슨", "피아노 앞의 소녀들",
            "해변에서" };
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        getSupportActionBar().setDisplayUseLogoEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        getSupportActionBar().setIcon(R.drawable.ic_launcher);
        setTitle("연습문제 11-6");

        final GridView gv = (GridView) findViewById(R.id.gridView1);
        MyGridAdapter gAdapter = new MyGridAdapter(this);
        gv.setAdapter(gAdapter);

        for(int i=0;i<9;i++){
            voteCount[i]=0;
        }


    }
    public class MyGridAdapter extends BaseAdapter {
        Context context;

        public MyGridAdapter(Context c){
            context = c;
        }

        @Override
        public int getCount() {
            return posterID.length;
        }

        @Override
        public Object getItem(int position) {
            return null;
        }

        @Override
        public long getItemId(int position) {
            return 0;
        }

        Integer[] posterID = {
                R.drawable.pic1, R.drawable.pic2, R.drawable.pic3, R.drawable.pic4, R.drawable.pic5, R.drawable.pic6, R.drawable.pic7, R.drawable.pic8, R.drawable.pic9
        };

        public View getView(int position, View convertView, ViewGroup parent){
            ImageView imageview = new ImageView(context);
            imageview.setLayoutParams(new ViewGroup.LayoutParams(400, 500));
            imageview.setScaleType(ImageView.ScaleType.FIT_CENTER);
            imageview.setPadding(1,1,1,1);

            imageview.setImageResource(posterID[position]);


            final int pos = position;
            imageview.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    voteCount[position]++;
                    Toast.makeText(getApplicationContext(), imgName[position] + ": 총 "+voteCount[position]+ " 표", Toast.LENGTH_SHORT).show();
                }
            });

            Button btnFinish = (Button) findViewById(R.id.btnResult);
            btnFinish.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(getApplicationContext(), ResultActivity.class);
                    intent.putExtra("VoteCount", voteCount);
                    intent.putExtra("ImageName", imgName);
                    startActivity(intent);
                }
            });
            return imageview;
        }}
}