package com.alicesfootprints.flower;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;

/**
 * Created by chord-gen on 15/10/04.
 */
public class MoveActivityDialog {
    AlertDialog.Builder Dialog;
    public MoveActivityDialog(final Activity activity){
        Dialog = new AlertDialog.Builder(activity);
        Dialog.setTitle("Continue?");
        Dialog.setNegativeButton("Exit", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                activity.finish();
                activity.moveTaskToBack(true);
            }
        });
        Dialog.setNeutralButton("Go Title", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                Intent intent = new Intent(activity, TitleActivity.class);
                intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                activity.startActivity(intent);
            }
        });

        Dialog.setPositiveButton("Go Page", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                Intent intent = new Intent(activity, PageSelectActivity.class);
                intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                activity.startActivity(intent);
            }
        });
    }
    public void show(){
        Dialog.show();
    }
}
