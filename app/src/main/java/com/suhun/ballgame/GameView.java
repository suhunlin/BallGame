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
    public GameView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        res = context.getResources();
        setBackgroundResource(R.drawable.bg);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        bmpBall = BitmapFactory.decodeResource(res, R.drawable.ball0);
        canvas.drawBitmap(bmpBall, 0, 0, null);
    }
}
