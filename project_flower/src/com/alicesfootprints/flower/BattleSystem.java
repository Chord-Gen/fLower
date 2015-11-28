package com.alicesfootprints.flower;

import android.content.Context;
import android.content.res.Resources;
import android.graphics.*;
import android.renderscript.Font;

/**
 * Created by chord-gen on 15/10/07.
 */
public class BattleSystem {

    //
    private Character player;
    private Clock clock;
    Bitmap buffer_image;
    Bitmap background;
    //private Character enemy;
    Typeface font;

    public BattleSystem(Context context,int stage_number, float displayX, float displayY){
        Resources res = context.getResources();
        buffer_image = BitmapFactory.decodeResource(res, R.drawable.alice01);
        player = new Character(100+displayX/2, displayY/2, 1000, buffer_image);

        buffer_image = BitmapFactory.decodeResource(res, R.drawable.clock1);
        //clock = new Clock(displayX/2, displayY-100, 1000, buffer_image);
        font = Typeface.createFromAsset(context.getAssets(), "flowerfont.ttf");
        buffer_image = BitmapFactory.decodeResource(res, R.drawable.green);
    }

    public void Update(){

    }



    public void Draw(Canvas canvas, Paint paint){
        canvas.drawBitmap(background, 0,0, paint);
        //player.draw(canvas, paint);
        //clock.draw(canvas, paint);

        paint.setColor(Color.CYAN);
        paint.setTypeface(font);
        paint.setTextSize(50);
        canvas.drawText("ありがとう", 200, 100, paint);
        canvas.drawText(new String(String.valueOf(player.getVitality())), player.getPx(),player.getPy()+10,paint);

    }

    public void End(){
        player.Destroy();
        player = null;
    }

}
