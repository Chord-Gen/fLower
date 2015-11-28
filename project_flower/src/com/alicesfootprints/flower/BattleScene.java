package com.alicesfootprints.flower;

import android.animation.ValueAnimator;
import android.content.Context;
import android.content.res.Resources;
import android.graphics.*;
import android.media.MediaPlayer;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.SurfaceHolder;
import android.view.SurfaceView;

/**
 * Created by chord-gen on 15/10/16.
 */
class BattleScene extends SurfaceView implements SurfaceHolder.Callback, Runnable {

    private MediaPlayer mbgm1;

    private BattleSystem battleSystem;

    private Thread thread;

    SurfaceHolder holder;

    private Character player;

    Typeface font;
    Bitmap pbmp;
    private Bitmap bgbmp;

    ValueAnimator animation;

    public BattleScene(Context context) {
        super(context);
        //this.getHolder().addCallback(this);
        thread = new Thread();
        thread.start();

        holder = this.getHolder();
        holder.addCallback(this);

        Typeface font = Typeface.createFromAsset(context.getAssets(), "flowerfont.ttf");
        DisplayMetrics display = getResources().getDisplayMetrics();
        float displayweight = display.xdpi;
        float displayheight = display.ydpi;

        battleSystem = new BattleSystem(context, 1, displayweight, displayheight);
        //pbmp = BitmapFactory.decodeResource(res, R.drawable.aiko);

        //power = 10;

        //bgbmp = BitmapFactory.decodeResource(res, R.drawable.stage2);
        //bgbmp = Bitmap.createScaledBitmap(bgbmp, displayweight, displayheight, true);

        font = Typeface.createFromAsset(context.getAssets(), "flowerfont.ttf");

        mbgm1 = MediaPlayer.create(context, R.raw.electro);


        mbgm1.setLooping(true);
        mbgm1.start();

    }

    public void run() {
        while (thread != null) {
            doDraw(holder);
        }
    }


    private void doDraw(SurfaceHolder holder) {
        Canvas canvas = holder.lockCanvas();
        Paint paint = new Paint();
        //canvas.drawBitmap(bgbmp, 0, 0, paint);

        //setBackgroundColor(Color.WHITE);
        //paint.setColor(Color.RED);
        //paint.setTextSize(500.0f);

        //paint.setARGB(200, 255, 0, 0);
        //paint.setTextSize(50);
        //paint.setTypeface(font);
        //canvas.drawText(String.valueOf(100), 500, 300, paint);
        //player.draw(canvas, paint);

        battleSystem.Draw(canvas, paint);
        //canvas.drawBitmap(pbmp, 500, 500, paint);
        getHolder().unlockCanvasAndPost(canvas);
    }


    @Override
    public void surfaceCreated(SurfaceHolder holder) {
        this.holder = holder;
        thread = new Thread(this);
        thread.start();
    }

    @Override
    public void surfaceChanged(SurfaceHolder holder, int format, int width, int height) {

    }

    @Override
    public void surfaceDestroyed(SurfaceHolder holder) {
        battleSystem.End();
        mbgm1.release();
        mbgm1 = null;
        thread = null;
    }
}