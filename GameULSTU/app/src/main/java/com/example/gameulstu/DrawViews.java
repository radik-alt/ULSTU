package com.example.gameulstu;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.util.Log;
import android.view.MotionEvent;
import android.view.SurfaceHolder;
import android.view.SurfaceView;
import android.view.View;

public class DrawViews extends SurfaceView implements SurfaceHolder.Callback, View.OnTouchListener {

    private DrawThread drawThread;
    private Context context;

    public DrawViews(Context context) {
        super(context);
        this.context = context;
        getHolder().addCallback(this);
        setOnTouchListener(this);
    }

    public void Coord(float coord_y, float coord_x) {
        Words.y = coord_y;
        Words.x = coord_x;
    }

    @Override
    public void surfaceChanged(SurfaceHolder holder, int format, int width,
                               int height) {

    }

    @Override
    public void surfaceCreated(SurfaceHolder holder) {
        drawThread = new DrawThread(getHolder(), context);
        drawThread.setRunning(true);
        drawThread.start();
    }

    @Override
    public void surfaceDestroyed(SurfaceHolder holder) {
        boolean retry = true;
        drawThread.setRunning(false);
        drawThread.destroy();
        /*while (retry) {
            try {
                drawThread.join();
                retry = false;
            } catch (InterruptedException e) {
            }
        }*/
    }

    @Override
    public boolean onTouch(View view, MotionEvent me) {
        Log.d("TOUCH","x " + me.getX() + " y " + me.getY());
        float coord_y = me.getY();
        float coord_x = me.getX();
        Coord(coord_y, coord_x);
        return false;
    }

}
