package com.suhun.ballgame;

import android.content.Context;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.os.Handler;
import android.os.Message;
import android.util.AttributeSet;
import android.util.Log;
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
    private int viewW, viewH, ballW, ballH;
    private float ballX, ballY, dX, dY;
    private Timer timer = new Timer();
    private MotionTask motionTask;
    private UIHandler uiHandler = new UIHandler();

    private class MotionTask extends TimerTask{
        @Override
        public void run() {
            if(ballX < 0 || ballX + ballW > viewW){
                dX *= -1;
            }

            if(ballY < 0 || ballY + ballH > viewH){
                dY *= -1;
            }
            ballX += dX;
            ballY += dY;
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
        viewW = getWidth(); viewH = getHeight();
        dX = dY = 20.0f;
        bmpBall = BitmapFactory.decodeResource(res, R.drawable.ball0);
        calculateBallScaledRatio(ratio);
        bmpBall = Bitmap.createScaledBitmap(bmpBall, ballW, ballH, false);
        motionTask = new MotionTask();
        timer.schedule(motionTask, 1000, 60);
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
