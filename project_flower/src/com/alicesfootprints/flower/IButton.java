package com.alicesfootprints.flower;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Rect;

/**
 * Created by chord-gen on 15/09/18.
 */
class IButton {
    private Bitmap bmp;
    private int width, height;
    private int pos_x, pos_y;
    public boolean pressed_flag = false;
    public Rect rect;
    public String text;

    public IButton(){}
    public IButton(String text, int pos_x, int pos_y) {
        this.text = text;
        this.pos_x = pos_x;
        this.pos_y = pos_y;
    }
    public IButton(Bitmap bmp, int pos_x, int pos_y) {
        this.bmp = bmp;
        this.pos_x = pos_x;
        this.pos_y = pos_y;
        width = bmp.getWidth();
        height = bmp.getHeight();

        rect = new Rect(pos_x, pos_y, pos_x + width, pos_y + height);
    }

    public boolean isPressed(int x, int y){
        return rect.contains(x , y);
    }

    public void drawPicture(Canvas canvas, Paint paint){
        canvas.drawBitmap(bmp, pos_x, pos_y, paint);
    }
    public void drawText(Canvas canvas, Paint paint){
        canvas.drawText(text, pos_x, pos_y, paint);
    }
    public float getX(){ return pos_x; }
    public float getY(){ return pos_y; }
    public float getWidth(){ return width; }
    public float getHeight(){ return height; }
    public float getUVerX(){ return pos_x + width; }
    public float getUVerY(){ return pos_y + height; }
}

class Selection extends IButton{

}