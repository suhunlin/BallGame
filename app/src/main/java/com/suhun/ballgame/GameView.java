package com.suhun.ballgame;

import android.content.Context;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.os.Handler;
import android.os.Message;
import android.util.AttributeSet;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import java.util.Timer;
import java.util.TimerTask;

public class GameView extends View {
    private String tag = GameView.class.getSimpleName();
    private Resources res;
    private Bitmap bmpBall;
    private boolean isInitBallInfo;
    private int ballW, ballH;
    private float ballX, ballY, motion;
    private Timer timer = new Timer();
    private UIHandler uiHandler = new UIHandler();

    private class MotionTask extends TimerTask{
        @Override
        public void run() {
            ballX = ballY += motion;
            uiHandler.sendEmptyMessage(0);
        }
    }

    private class UIHandler extends Handler {
        @Override
        public void handleMessage(@NonNull Message msg) {
            super.handleMessage(msg);
            postInvalidate();
        }
    }
    public GameView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        res = context.getResources();
        setBackgroundResource(R.drawable.bg);
    }
    private void initBallInfo(){
        float ratio = 12.0f;
        motion = 6.0f;
        bmpBall = BitmapFactory.decodeResource(res, R.drawable.ball0);
        calculateBallScaledRatio(ratio);
        bmpBall = Bitmap.createScaledBitmap(bmpBall, ballW, ballH, false);
        timer.schedule(new MotionTask(), 1000, 100);
        isInitBallInfo = true;
    }
    private void calculateBallScaledRatio(float ratio){
        ballW = (int)(getWidth()/ratio);
        ballH = ballW;
    }
    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        if(!isInitBallInfo){
            initBallInfo();
        }
        canvas.drawBitmap(bmpBall, ballX, ballY, null);
    }
}
