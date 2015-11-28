package com.alicesfootprints.flower;

import android.animation.ValueAnimator;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.res.Resources;
import android.graphics.*;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.view.*;
import android.widget.RelativeLayout;
import android.widget.TextView;

/**
 * Created by chord-gen on 15/05/09.
 */
public class BattleActivity extends Activity{

    private BattleScene battleScene;

    private EndDialog endDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().addFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN);

        Typeface font = Typeface.createFromAsset(getAssets(), "flowerfont.ttf");

        battleScene = new BattleScene(this);
        setContentView(battleScene);

        endDialog = new EndDialog(this);
    }

    @Override
    public void onPause(){
        super.onPause();

        CleanUp cleanUp = new CleanUp();
        cleanUp.CleanUpView(findViewById(R.id.battle));
    }

    @Override
    public void onDestroy(){
        super.onDestroy();

    }

    public  boolean onTouchEvent(MotionEvent event){
        Intent intent = new Intent(this, GameOverActivity.class);
        this.startActivity(intent);
        BattleActivity.this.finish();
        overridePendingTransition(0, 0);

        return false;
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode != KeyEvent.KEYCODE_BACK) {
            return super.onKeyDown(keyCode, event);
        }
        else {
            endDialog.show();

            return false;
        }
    }
}