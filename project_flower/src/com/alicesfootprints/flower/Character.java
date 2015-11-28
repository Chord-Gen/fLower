package com.alicesfootprints.flower;

import android.content.res.Resources;
import android.graphics.*;

/**
 * Created by chord-gen on 15/10/04.
 */
public class Character {
    private float px, py;
    private int vitality;
    private Bitmap char_image;
    private int image_size;

    public Character(float posx, float posy, int vitality, Bitmap image){
        this.px = posx;
        this.py = posy;
        this.vitality = vitality;
        this.char_image = image;
    }
    public void draw(Canvas canvas, Paint paint){
        canvas.drawBitmap(char_image, px, py, paint);
    }
    public void  Destroy(){
        char_image.recycle();
        char_image = null;
    }
    public float getPx() {return px; }
    public float getPy(){return py; }
    public int getVitality() { return vitality;}
}