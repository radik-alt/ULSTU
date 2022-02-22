package com.example.threeulstu;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.Point;
import android.graphics.Rect;
import android.graphics.RectF;
import android.os.Bundle;
import android.util.Log;
import android.view.Display;
import android.view.SurfaceHolder;
import android.view.SurfaceView;
import android.view.View;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private static float x, x2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(new DrawView(this));

        Input input = new Input();
        x = input.getX1();
        x2 = input.getX2();
    }

    class DrawView extends View{

        Paint p = new Paint();
        Path path = new Path();

        public DrawView(Context context) {
            super(context);
        }

        public void onDraw (Canvas c){

            c.drawColor(Color.rgb(255,255,255));

            p.setColor(Color.rgb(0,0,255));

            float centerX = this.getWidth()/2;
            float centerY = this.getHeight()/2;

            c.drawLine(20, centerY, this.getWidth(), centerY,p); // по горизонтали
            c.drawLine(this.getWidth()-10, centerY-10, this.getWidth(), centerY,p);
            c.drawLine(this.getWidth()-10, centerY+10, this.getWidth(), centerY,p);

            c.drawLine(centerX, 20, centerX, this.getHeight(),p); // по вертикали
            c.drawLine(centerX-10, 30, centerX, 20,p);
            c.drawLine(centerX+10, 30, centerX, 20,p);


            // отрисовка линий
            //отрисовка цифр
            int m = 0;
            for (int i = this.getHeight()/2; i < this.getHeight(); i += 100) {
                c.drawLine((this.getWidth()/2) + 10, i,(this.getWidth()/2) - 10, i, p);
                if (m!=0) {
                    p.setTextAlign(Paint.Align.CENTER);
                    p.setTextSize(20);
                    c.drawText(String.valueOf(m), (this.getWidth()/2)+ 20, i, p);
                }
                m--;
            }

            m = 0;
            for (int i = this.getHeight()/2; i > 0; i -= 100) {
                c.drawLine((this.getWidth()/2) + 10, i, (this.getWidth()/2) - 10, i,  p);
                if (m!=0) {
                    p.setTextAlign(Paint.Align.CENTER);
                    p.setTextSize(20);
                    c.drawText(String.valueOf(m), (this.getWidth()/2)+ 20, i, p);
                }
                m++;
            }

            int s = 0;
            for (int i = this.getWidth()/2; i < this.getWidth(); i += 100) {
                c.drawLine(i, this.getHeight()/2 + 10,i, this.getHeight()/2 - 10, p);
                if (s!=0){
                    p.setTextAlign(Paint.Align.CENTER);
                    p.setTextSize(20);
                    c.drawText(String.valueOf(s), i, (this.getHeight()/2) - 20, p);
                }
                s++;
            }
            s = 0;
            for (int i = this.getWidth()/2; i > 0; i -= 100) {
                c.drawLine(i, this.getHeight()/2 + 10,i, this.getHeight()/2 - 10, p);
                if (s!=0){
                    p.setTextAlign(Paint.Align.CENTER);
                    p.setTextSize(20);
                    c.drawText(String.valueOf(s), i, (this.getHeight()/2) - 20, p);
                }
                s--;
            }

            //График
            p.setColor(Color.BLACK);
            p.setStrokeWidth(10);
            float y, xX;
            while (x >= x2) {
                y = (float) (Math.pow(x, 3) / 2) * 10;
                xX = x * 20;
                p.setColor(Color.BLACK);
                c.drawPoint(centerX + xX, centerY - y, p);
                if (x != x2) {
                    p.setColor(Color.RED);
                    float x2 = (float) ((x - 1.0) * 20);
                    float y2 = (float) (Math.pow(x-1.0, 3) / 2) * 10;
                    c.drawLine(centerX + xX, centerY - y,
                            centerX + x2, centerY - y2, p);
                }
                x -= 1.0;
            }
        }
    }
}