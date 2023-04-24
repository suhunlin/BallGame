package com.suhun.ballgame;

import android.content.Context;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.util.AttributeSet;
import android.view.View;

import androidx.annotation.Nullable;

public class GameView extends View {
    private String tag = GameView.class.getSimpleName();
    private Resources res;
    private Bitmap bmpBall;
    private boolean isInitBallInfo;
    private int ballW, ballH;
    private float ballX, ballY;
    public GameView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        res = context.getResources();
        setBackgroundResource(R.drawable.bg);
    }
    private void initBallInfo(){
        float ratio = 12.0f;
        bmpBall = BitmapFactory.decodeResource(res, R.drawable.ball0);
        calculateBallScaledRatio(ratio);
        bmpBall = Bitmap.createScaledBitmap(bmpBall, ballW, ballH, false);
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
