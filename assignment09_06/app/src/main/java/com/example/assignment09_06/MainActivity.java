package com.example.assignment09_06;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.SubMenu;
import android.view.View;

import java.util.ArrayList;
import java.util.List;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    final static int Figure_line = 1, Figure_circle = 2, Figure_rectangle = 3;
    static int curShape = Figure_line;
    static int color = Color.BLACK;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(new MyGraphicView(this));

        getSupportActionBar().setDisplayUseLogoEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        getSupportActionBar().setIcon(R.drawable.ic_launcher);
    }

    private static class MyGraphicView extends View {
        MainActivity.MyGraphicView.Myshape CShape = null;
        ArrayList<MainActivity.MyGraphicView.Myshape> MyshapeArrayList = new ArrayList<>();

        public MyGraphicView(Context context) {
            super(context);
        }

        @Override
        public boolean onTouchEvent(MotionEvent event) {
            switch (event.getAction()) {
                case MotionEvent.ACTION_DOWN:

                    CShape = new MainActivity.MyGraphicView.Myshape(curShape);
                    CShape.color = color;
                    CShape.startX = (int) event.getX();
                    CShape.startY = (int) event.getY();
                    break;
                case MotionEvent.ACTION_MOVE:
                    CShape.stopX = (int) event.getX();
                    CShape.stopY = (int) event.getY();
                    this.invalidate();
                    break;
                case MotionEvent.ACTION_UP:
                    CShape.stopX = (int) event.getX();
                    CShape.stopY = (int) event.getY();

                    MyshapeArrayList.add(CShape);
                    CShape = null;
                    this.invalidate();
                    break;
            }
            return true;
        }

        protected void onDraw(Canvas canvas) {
            super.onDraw(canvas);
            Paint paint = new Paint();
            paint.setAntiAlias(true);
            paint.setStrokeWidth(5);
            paint.setStyle(Paint.Style.STROKE);

            for (MainActivity.MyGraphicView.Myshape currentShape : MyshapeArrayList) {
                paint.setColor(currentShape.color);
                drawShape(currentShape, canvas, paint);
            }

            if (CShape != null) {
                drawShape(CShape, canvas, paint);
            }

        }

        private void drawShape(MainActivity.MyGraphicView.Myshape currentShape, Canvas canvas, Paint paint) {
            switch (currentShape.shape) {
                case Figure_line:
                    canvas.drawLine(currentShape.startX, currentShape.startY,
                            currentShape.stopX, currentShape.stopY, paint);
                    break;
                case Figure_circle:
                    int radius = (int) Math.sqrt(Math.pow(currentShape.stopX - currentShape.startX, 2) +
                            Math.pow(currentShape.stopY - currentShape.startY, 2));
                    canvas.drawCircle(currentShape.startX, currentShape.startY, radius, paint);
                    break;
                case Figure_rectangle:
                    Rect rect = new Rect(currentShape.startX, currentShape.startY,
                            currentShape.stopX, currentShape.stopY);
                    canvas.drawRect(rect, paint);
                    break;
            }
        }

        private static class Myshape {
            int shape, startX, startY, stopX, stopY, color;
            public Myshape(int shape) {
                this.shape = shape;
            }
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        super.onCreateOptionsMenu(menu);
        menu.add(0, 1, 0, "선 그리기");
        menu.add(0, 2, 0, "원 그리기");
        menu.add(0, 3, 0, "사각형 그리기");

        SubMenu subMenu = menu.addSubMenu("색상 변경 >> ");
        subMenu.add(0, 4, 1, "빨강");
        subMenu.add(0, 5, 2, "초록");
        subMenu.add(0, 6, 3, "파랑");

        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case 1:
                curShape = Figure_line;
                return true;
            case 2:
                curShape = Figure_circle;
                return true;
            case 3:
                curShape = Figure_rectangle;
                return true;
            case 4:
                color = Color.RED;
                return true;
            case 5:
                color = Color.GREEN;
                return true;
            case 6:
                color = Color.BLUE;
                return true;

        }
        return super.onOptionsItemSelected(item);
    }
}