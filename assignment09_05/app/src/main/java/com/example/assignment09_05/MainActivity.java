package com.example.assignment09_05;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.ColorMatrix;
import android.graphics.ColorMatrixColorFilter;
import android.graphics.Paint;
import android.graphics.drawable.BitmapDrawable;
import android.os.Bundle;
import android.view.ContextMenu;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    private MyGraphicView myGraphicView;
    static float scaleX = 1, scaleY = 1;
    static float angle = 0;
    static float color = 1;
    static float satur = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setTitle("연습문제 9-5");

        getSupportActionBar().setDisplayUseLogoEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        getSupportActionBar().setIcon(R.drawable.ic_launcher);

        myGraphicView = new MyGraphicView(this);
        setContentView(myGraphicView);
        registerForContextMenu(myGraphicView);
    }

    private static class MyGraphicView extends View {
        public MyGraphicView(Context context) {
            super(context);
        }

        @Override
        protected void onDraw(Canvas canvas) {
            super.onDraw(canvas);

            int cenX = this.getWidth() / 2;
            int cenY = this.getHeight() / 2;
            canvas.scale(scaleX, scaleY, cenX, cenY);
            canvas.rotate(angle, cenX, cenY);

            Paint paint = new Paint();
            float[] array = { color, 0, 0, 0, 0, 0, color, 0, 0, 0, 0, 0,
                    color, 0, 0, 0, 0, 0, 1, 0 };
            ColorMatrix cm = new ColorMatrix(array);

            if (satur == 0)
                cm.setSaturation(satur);

            paint.setColorFilter(new ColorMatrixColorFilter(cm));

            Bitmap picture = BitmapFactory.decodeResource(getResources(), R.drawable.lena256);
            int picX = (this.getWidth() - picture.getWidth()) / 2;
            int picY = (this.getHeight() - picture.getHeight()) / 2;
            canvas.drawBitmap(picture, picX, picY, paint);

            picture.recycle();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        super.onCreateOptionsMenu(menu);
        menu.add(0, 1, 1, "확대");
        menu.add(0, 2, 2, "축소");
        menu.add(0, 3, 3, "회전");
        menu.add(0, 4, 4, "밝게");
        menu.add(0, 5, 5, "어둡게");
        menu.add(0, 6, 6, "그레이영상");
        return true;
    }
    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()) {
            case 1:
                scaleX = scaleX + 0.2f;
                scaleY = scaleY + 0.2f;
                myGraphicView.invalidate();
                return true;
            case 2:
                scaleX = scaleX - 0.2f;
                scaleY = scaleY - 0.2f;
                myGraphicView.invalidate();
                return true;
            case 3:
                angle = angle + 20;
                myGraphicView.invalidate();
                return true;
            case 4:
                color = color + 0.2f;
                myGraphicView.invalidate();
                return true;
            case 5:
                color = color - 0.2f;
                myGraphicView.invalidate();
                return true;
            case 6:
                if (satur == 0)
                    satur = 1;
                else
                    satur = 0;
                myGraphicView.invalidate();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }
}