package com.example.assignment_11_06;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.os.Bundle;
import android.view.ContextMenu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.LinearLayout;
import android.widget.Spinner;

public class MainActivity extends AppCompatActivity {
    MyGraphicView myGraphicView;
    LinearLayout pictureLayout;
    int name, a = 20;
    float scaleX = 1, scaleY = 1;
    float angle = 0;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        getSupportActionBar().setDisplayUseLogoEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        getSupportActionBar().setIcon(R.drawable.ic_launcher);
        setTitle("연습문제 11-6");

        final String[] movie = { "아바타", "힘을내요 미스터리", "포드vs페라리", "쥬만지", "대부",
                "국가대표", "토이스토리3", "마당을 나온 암탉", "죽은 시인의 사회", "서유기" };

        pictureLayout = (LinearLayout) findViewById(R.id.pictureLayout);
        Spinner spinner = (Spinner) findViewById(R.id.spinner1);

        final ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, movie);
        spinner.setAdapter(adapter);

        myGraphicView = new MyGraphicView(this);

        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {

            public void onItemSelected(AdapterView<?> arg0, View arg1, int arg2, long arg3) {
                pictureLayout.removeAllViews();
                name = arg2;
                pictureLayout.addView(myGraphicView);
            }

            public void onNothingSelected(AdapterView<?> arg0) {
                // TODO Auto-generated method stub
            }
        });
        registerForContextMenu(myGraphicView);

    }

    @Override
    public void onCreateContextMenu(ContextMenu menu, View view, ContextMenu.ContextMenuInfo menuInfo) {
        super.onCreateContextMenu(menu, view, menuInfo);
        MenuInflater menuInflater = getMenuInflater();
        menuInflater.inflate(R.menu.menul, menu);
    }

    @Override
    public boolean onContextItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.rotate:
                angle += a;
                myGraphicView.invalidate();
                return true;
            case R.id.sizeup:
                scaleX += 0.2f;
                scaleY += 0.2f;
                myGraphicView.invalidate();
                return true;
            case R.id.sizedown:
                scaleX -= 0.2f;
                scaleY -= 0.2f;
                myGraphicView.invalidate();
                return true;
            case R.id.rotateup:
                a += 10;
                return true;
            case R.id.rotatedown:
                a -= 10;
                return true;
        }
        return false;
    }

    private class MyGraphicView extends View {
        public MyGraphicView(Context context) {
            super(context);
        }

        @Override
        protected void onDraw(Canvas canvas) {
            super.onDraw(canvas);
            final Integer[] posterID = { R.drawable.mov21, R.drawable.mov22, R.drawable.mov23, R.drawable.mov24,
                    R.drawable.mov25, R.drawable.mov26, R.drawable.mov27, R.drawable.mov28, R.drawable.mov29,
                    R.drawable.mov30 };
            int cenX = this.getWidth() / 2;
            int cenY = this.getHeight() / 2;

            canvas.scale(scaleX, scaleY, cenX, cenY);
            canvas.rotate(angle, cenX, cenY);
            Bitmap picture = BitmapFactory.decodeResource(getResources(), posterID[name]);
            int picX = (this.getWidth() - picture.getWidth()) / 2;
            int picY = (this.getHeight() - picture.getHeight()) / 2;
            canvas.drawBitmap(picture, picX, picY, null);
            picture.recycle();
        }
    }
}
