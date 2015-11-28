package com.alicesfootprints.flower;

import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.ViewFlipper;

/**
 * Created by chord-gen on 15/10/05.
 */
public class CleanUp {
    public CleanUp(){}
    public void CleanUpView(View view){
        if(view instanceof ImageButton){
            ImageButton ib = (ImageButton)view;
            ib.setImageDrawable(null);
        }
        else if(view instanceof ImageView){
            ImageView iv = (ImageView)view;
            iv.setImageDrawable(null);
        }

        if(view instanceof ViewGroup){
            ViewGroup vg = (ViewGroup)view;
            int size = vg.getChildCount();
            for(int i=0; i<size; i++){
                CleanUpView(vg.getChildAt(i));
            }
        }
    }
}
