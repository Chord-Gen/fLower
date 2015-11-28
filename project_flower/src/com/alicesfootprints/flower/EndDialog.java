package com.alicesfootprints.flower;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;

/**
 * Created by chord-gen on 15/10/04.
 */
public class EndDialog {

    AlertDialog.Builder endDialog;
    public EndDialog(final Activity activity){
        endDialog = new AlertDialog.Builder(activity);

        endDialog.setTitle("Exit to Game?");
        endDialog.setNegativeButton("OK", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                activity.finish();
                activity.moveTaskToBack(true);
            }
        });
        endDialog.setPositiveButton("Cancel", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                endDialog.setCancelable(true);
            }
        });
    }
    public void show(){
        endDialog.show();
    }
}
