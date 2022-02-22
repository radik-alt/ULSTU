package com.example.gameulstu;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.RectF;
import android.util.Log;
import android.view.SurfaceHolder;
import android.widget.Toast;

import java.sql.Wrapper;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.LinkedList;
import java.util.Locale;

public class DrawThread extends Thread {

    private boolean running = false;
    private SurfaceHolder surfaceHolder;
    private Paint p = new Paint();
    private GameLayout gameLayout = new GameLayout();
    private Context context;


    public DrawThread(SurfaceHolder surfaceHolder, Context context) {
        this.surfaceHolder = surfaceHolder;
        this.context = context;
    }

    public void setRunning(boolean running) {
        this.running = running;

    }

    int stk = 0;
    int len = 0;
    String res = "";
    Canvas c;
    @Override
    public void run() {
        while (running) {
            c = null;
            try {
                c = surfaceHolder.lockCanvas();
                if (c == null)
                    continue;

                len = Words.word.get(Words.next).length();
                c.drawColor(Color.BLACK);
                // дата и время
                Calendar calendar = Calendar.getInstance();
                SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
                String formattedDate = df.format(calendar.getTime());

                c.drawText( "Уровень " + String.valueOf(Words.next), 1000, 100, p);



                float xObj = (c.getWidth()) / len, yObj = c.getHeight()/2 - 200;
                for (int j = 0; j < len; j++) {

                    if (Words.y_cordObject.size() < len)  {
                        Words.x_cordObject.add(xObj);
                        Words.y_cordObject.add(yObj);
                    }

                    Draw(c, Words.y_cordObject.get(j), Words.x_cordObject.get(j), j);

                    // отрисовка нижних фигру
                    DrawRect(c, Words.x_cordObject.get(j));

                    xObj += 200;
                }

                // время и очки
                p.setTextSize(56);
                p.setStrokeWidth(20);
                p.setColor(Color.WHITE);
                p.setStrokeWidth(6);
                c.drawText("Count : " + Words.count,
                        c.getWidth() - 300, 100, p);
                c.drawText(formattedDate, 50, 100, p);
                Words.currentTime = formattedDate;

            } finally {
                if (c != null) {
                    surfaceHolder.unlockCanvasAndPost(c);
                }
            }
        }
    }

    // проверка клика
    public boolean valid (float yObj, float xObj) {
        if ((Words.x >= xObj - 80 && Words.x <= xObj + 80) && (Words.y >= yObj - 80 && Words.y <= yObj + 80)) {
            return true;
        }
        return false;
    }


    final RectF rectWord = new RectF();
    // отрисовка фигур
    int i = 0;
    int l = 0;
    public void Draw (Canvas c, float yObj, float xObj, int j) {

        float height = c.getHeight() - 100;
        if (valid(yObj, xObj)) {
            if (!Words.memory.isEmpty()) {
                while (i < Words.memory.size()) {
                    if (i < len) {
                        if (!Words.memory.get(i)) {
                            p.setColor(Color.RED);
                            p.setStyle(Paint.Style.FILL);
                            rectWord.set(Words.x_cord.get(i) + 80, Words.y_cord.get(i) + 80,
                                    Words.x_cord.get(i) - 80, Words.y_cord.get(i) - 80);
                            c.drawRoundRect(rectWord, 100, 50, p);
                            Words.x_cordObject.set(j,Words.x_cord.get(i));
                            Words.y_cordObject.set(j,Words.y_cord.get(i));
                            p.setTextSize(56);
                            p.setStrokeWidth(20);
                            p.setColor(Color.WHITE);
                            char ch = Words.word.get(Words.next).charAt(j);
                            c.drawText(String.valueOf(ch), Words.x_cord.get(i) - 20, Words.y_cord.get(i)+10, p);
                            Words.slov.add(ch);
                            Words.memory.set(i, true);
                            l += 1;
                            i = l;
                            WinLose(ch);
                        }
                        break;
                    }
                    i++;
                }
            }
        } else {
            p.setColor(Color.RED);
            p.setStyle(Paint.Style.FILL);
            final RectF rectWord = new RectF();
            rectWord.set(xObj + 80, yObj + 80,
                    xObj - 80, yObj - 80);
            c.drawRoundRect(rectWord, 100, 50, p);

            p.setTextSize(56);
            p.setStrokeWidth(20);
            p.setColor(Color.WHITE);
            char ch = Words.word.get(Words.next).charAt(j);
            c.drawText(String.valueOf(ch), xObj - 20, yObj+10, p);
        }

    }

    int s = 0;
    public void DrawRect(Canvas c, float xObj) {
        float width = xObj;
        float height = c.getHeight() - 100;

        if (s < len) {
            Words.x_cord.add(xObj);
            Words.y_cord.add(height);
            Words.memory.add(false);
        }
        p.setStyle(Paint.Style.STROKE);
        p.setColor(Color.WHITE);
        final RectF rect = new RectF();
        rect.set(width + 100, height + 100,
                width - 100, height- 100);
        c.drawRoundRect(rect, 100, 50, p);
        s++;
    }

    //Проверка на победу
    int g = 0;
    public void WinLose (Character ch) {
        try {
            if (Words.word.get(Words.next).charAt(g) != ch){
                GameOver();
            } else {
                Pointer();
            }

            if (len-1 == g){
                Words.count += 10;
                nextLevel();
            }
        } catch (Exception e) {
            Log.d("ErrorARRRRR", "Ошибка");
        }
        g++;
    }

    private void Pointer() {
        Words.count++;
    }

    //Gameover
    public void GameOver () {
        res = "Проигрыш!";
        c.drawText(res, 1000, 200, p);
        setRunning(false);

        //        Words.count = 0;
        /*setRunning(false);
        ((Activity) context).finish();
        Intent intent = new Intent(context, MainActivity.class);
        context.startActivity(intent);*/
    }

    public void nextLevel () {
        res = "Победа!";
        c.drawText(res, 1000, 200, p);
        setRunning(false);
//        surfaceHolder.unlockCanvasAndPost(c);
        /* Words.count += 10;
        Words.next += 1;
//        Log.d("NEXT", String.valueOf(Words.next));
        Words.x_cord.clear();
        Words.y_cord.clear();
        Words.y_cordObject.clear();
        Words.y_cordObject.addAll(Words.copyList2);
        Words.x_cordObject.clear();
        Words.x_cordObject.addAll(Words.copyList1);
        Words.slov.clear();
        Words.memory.clear();
        i = 0;
        l = 0;
        s = 0;
        g = 0;*/
    }
}
