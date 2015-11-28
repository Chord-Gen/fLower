package com.alicesfootprints.flower;

import android.graphics.PointF;

/**
 * Created by chord-gen on 15/05/15.
 */
public class Collider {


    public boolean isBoundEllipse(PointF p1, PointF p2){
        boolean flag=false;

        return flag;
    }
    public boolean isRectToPoint(float verX1, float verY1, float verX2, float verY2, float px, float py){
        boolean flag = false;

        if(verX1 <= px && verY1 <= py && verX2 >= px && verY2 >= py){
            flag = true;
        }

        return flag;
    }
}
