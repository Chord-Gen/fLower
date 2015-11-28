package com.alicesfootprints.flower;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Typeface;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.media.SoundPool;
import android.view.*;
import android.os.Bundle;
import android.view.animation.AlphaAnimation;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ViewFlipper;

/**
 * Created by chord-gen on 15/05/27.
 */
public class PageSelectActivity extends Activity implements View.OnClickListener{

    GestureDetector gestureDetector;

    private ViewFlipper viewFlipper;

    private Button openButton;
    private Button closeButton;

    private AlphaAnimation feedout;

    private MediaPlayer mediaPlayer;
    private SoundPool soundPool;
    int soundId = 0;

    private EndDialog endDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().addFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN);

        setContentView(R.layout.pageselect);

        viewFlipper = (ViewFlipper)findViewById(R.id.pageflipper);

        Typeface font = Typeface.createFromAsset(getAssets(), "flowerfont.ttf");

        openButton = (Button) findViewById(R.id.openButton);
        openButton.setTypeface(font);
        openButton.setOnClickListener(this);

        closeButton = (Button) findViewById(R.id.closeButton);
        closeButton.setTypeface(font);
        closeButton.setOnClickListener(this);

        mediaPlayer = MediaPlayer.create(this, R.raw.olgold2);
        mediaPlayer.setLooping(true);
        mediaPlayer.start();

        soundPool = new SoundPool(1, AudioManager.STREAM_MUSIC, 0);
        soundId = soundPool.load(this, R.raw.pagecurl_1, 1);

        feedout = new AlphaAnimation(1, 0);
        feedout.setDuration(300);

        gestureDetector = new GestureDetector(this, new GestureDetector.OnGestureListener() {
            @Override
            public boolean onDown(MotionEvent e) {
                return false;
            }

            @Override
            public void onShowPress(MotionEvent e) {
            }

            @Override
            public boolean onSingleTapUp(MotionEvent e) {
                return false;
            }

            @Override
            public boolean onScroll(MotionEvent e1, MotionEvent e2, float distanceX, float distanceY) {
                return false;
            }

            @Override
            public void onLongPress(MotionEvent e) {
            }

            @Override
            public boolean onFling(MotionEvent e1, MotionEvent e2, float velocityX, float velocityY) {
                float dx = Math.abs(velocityX);
                float dy = Math.abs(velocityY);
                if(dx > dy && dx > 300){

                    if(e1.getX() > e2.getX()){
                        soundPool.play(soundId, 1.0f, 1.0f ,0 ,0, 1.0f);
                        viewFlipper.showPrevious();
                    }
                    else {
                        soundPool.play(soundId, 1.0f, 1.0f ,0 ,0, 1.0f);
                        viewFlipper.showNext();
                    }
                    return true;
                }
                return false;
            }
        });
        endDialog = new EndDialog(this);
    }

    @Override
    public void onPause(){
        super.onPause();
        mediaPlayer.release();
        soundPool.release();
        openButton.setOnClickListener(null);
        closeButton.setOnClickListener(null);
        gestureDetector = null;
        CleanUp cleanUp = new CleanUp();
        cleanUp.CleanUpView(findViewById(R.id.pageflipper));
    }

    @Override
    public void onDestroy(){
        super.onDestroy();
        mediaPlayer = null;
        soundPool = null;
        openButton.setOnClickListener(null);
        closeButton.setOnClickListener(null);
        gestureDetector = null;
    }

    @Override
    public boolean onTouchEvent(MotionEvent event){
        gestureDetector.onTouchEvent(event);

        return false;
    }


    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode != KeyEvent.KEYCODE_BACK) {
            return super.onKeyDown(keyCode, event);
        } else {
            endDialog.show();

            return false;
        }
    }

    @Override
    public void onClick(View v) {

        if(v == openButton){
            openButton.startAnimation(feedout);
            switch (viewFlipper.getCurrentView().getId()){
                case R.id.prologuestory:
                    soundPool.play(soundId, 1.0f, 1.0f, 0, 0, 1.0f);
                    Intent intent = new Intent(this, ReadStoryActivity.class);
                    intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                    this.startActivity(intent);
                    PageSelectActivity.this.finish();
                    overridePendingTransition(R.anim.fadein, R.anim.fadeout);
                    break;
                default:
                    break;
            }
        }
        else {
            soundPool.play(soundId, 1.0f, 1.0f ,0 ,0, 1.0f);
            closeButton.startAnimation(feedout);
            Intent intent = new Intent(this, TitleActivity.class);
            intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
            this.startActivity(intent);
            PageSelectActivity.this.finish();
            overridePendingTransition(R.anim.fadein, R.anim.fadeout);
        }
    }
}
