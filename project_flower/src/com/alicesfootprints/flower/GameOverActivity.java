package com.alicesfootprints.flower;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.preference.Preference;
import android.view.KeyEvent;
import android.view.MotionEvent;
import android.view.Window;
import android.view.WindowManager;

/**
 * Created by chord-gen on 15/10/04.
 */
public class GameOverActivity extends Activity{

    private AlertDialog.Builder dialog;
    private EndDialog endDialog;

    @Override
    public void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().addFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN);

        setContentView(R.layout.gameover);

        dialog = new AlertDialog.Builder(this);
        dialog.setTitle("Continue?");
        dialog.setNegativeButton("Exit", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                GameOverActivity.this.finish();
                moveTaskToBack(true);
            }
        });
        dialog.setNeutralButton("Go Title", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                Intent intent = new Intent(GameOverActivity.this, TitleActivity.class);
                intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                startActivity(intent);
            }
        });

        dialog.setPositiveButton("Go Page", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                Intent intent = new Intent(GameOverActivity.this, PageSelectActivity.class);
                intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                startActivity(intent);
            }
        });
        endDialog = new EndDialog(this);
    }

    @Override
    public void onPause(){
        super.onPause();

        endDialog = null;
        //dialog = null;
        CleanUp cleanUp = new CleanUp();
        cleanUp.CleanUpView(findViewById(R.id.gameover));
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
        super.onTouchEvent(event);

        dialog.show();

        return false;
    }

}
