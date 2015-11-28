package com.alicesfootprints.flower;

import android.app.Activity;

import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.view.*;
import android.widget.*;
import android.widget.Button;

/**
 * Created by chord-gen on 15/09/18.
 */
public class ReadStoryActivity extends Activity implements View.OnClickListener{

    private GestureDetector gestureDetector;
    ViewFlipper viewFlipper;
    private MediaPlayer mediaPlayer;

    private Button skipButton;

    private EndDialog endDialog;
    @Override
    public void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().addFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN);

        setContentView(R.layout.prologue);

        viewFlipper = (ViewFlipper)findViewById(R.id.prologflipper);

        mediaPlayer = MediaPlayer.create(this, R.raw.olgold1);
        mediaPlayer.setLooping(true);
        mediaPlayer.start();

        skipButton = (Button) findViewById(R.id.skipbutton);
        skipButton.setOnClickListener(this);

        endDialog = new EndDialog(this);

        /*
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
                return false;
            }
        });*/
    }

    @Override
    public void onPause(){
        super.onPause();
        mediaPlayer.release();
        skipButton.setOnClickListener(null);
        CleanUp cleanUp = new CleanUp();
        cleanUp.CleanUpView(findViewById(R.id.prologuestory));
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
    public boolean onTouchEvent(MotionEvent event){
        //gestureDetector.onTouchEvent(event);

        return false;
    }

    @Override
    public void onClick(View v) {
        if(v == skipButton){
            Intent intent = new Intent(this, BattleActivity.class);
            this.startActivity(intent);
        }
    }
}
