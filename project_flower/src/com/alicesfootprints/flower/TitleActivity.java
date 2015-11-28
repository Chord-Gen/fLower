package com.alicesfootprints.flower;

import android.app.Activity;

import android.app.ActivityOptions;
import android.content.Intent;
import android.graphics.Typeface;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.media.SoundPool;
import android.view.*;
import android.os.Bundle;

import android.view.animation.AlphaAnimation;
import android.widget.*;


/**
 * Created by chord-gen on 15/07/07.
 */
public class TitleActivity extends Activity implements View.OnClickListener{

    private Button startButton;
    private EndDialog endDialog;
    private MediaPlayer mediaPlayer;
    private SoundPool soundPool;
    int soundId = 0;

    private AlphaAnimation feedout;
    @Override
    public void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);


        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().addFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN);

        setContentView(R.layout.title);

        startButton = (Button) findViewById(R.id.startButton);
        Typeface font = Typeface.createFromAsset(getAssets(), "flowerfont.ttf");
        startButton.setTypeface(font);

        endDialog = new EndDialog(this);
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
    public void onResume(){
        super.onResume();
        startButton.setOnClickListener(this);
        //--SE Setting--
        soundPool = new SoundPool(1, AudioManager.STREAM_MUSIC, 0);
        soundId = soundPool.load(this, R.raw.pagecurl_1, 1);
        mediaPlayer = MediaPlayer.create(this, R.raw.piano);
        mediaPlayer.setLooping(true);
        mediaPlayer.start();
    }

    @Override
    public void onPause(){
        super.onPause();
        mediaPlayer.release();
        soundPool.release();
        startButton.setOnClickListener(null);

    }

    @Override
    public void onRestart(){
        super.onRestart();
    }
    @Override
    public void onDestroy(){
        super.onDestroy();
        CleanUp cleanUp = new CleanUp();
        cleanUp.CleanUpView(findViewById(R.id.titleview));
        mediaPlayer = null;
        soundPool = null;
        startButton.setOnClickListener(null);
    }


    @Override
    public void onClick(View v) {
        if(v == startButton) {
            feedout = new AlphaAnimation(1, 0);
            feedout.setDuration(3000);
            startButton.setAnimation(feedout);
            soundPool.play(soundId, 1.0f, 1.0f, 0, 0, 1.0f);

            Intent intent = new Intent(this, PageSelectActivity.class);
            intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
            this.startActivity(intent);

            TitleActivity.this.finish();
            overridePendingTransition(R.anim.fadein, R.anim.fadeout);
        }
        /*
        else {
            //exitButton.setAnimation(feedout);
            TitleActivity.this.finish();
            this.moveTaskToBack(true);

        }*/
    }
}
