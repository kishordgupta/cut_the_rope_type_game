package com.kd.grabandcut.menu.level;


import android.content.Context;
import android.util.Log;
import android.view.MotionEvent;
import android.view.SurfaceHolder;
import android.view.SurfaceView;

public class Screen extends SurfaceView implements SurfaceHolder.Callback {

    public static int screenWidth;
    public static int screenHeight;
    public static boolean screenReady = false;
    public MotionEvent event;
    public Object eventSynchronization = new Object();
    public Screen(Context context, int backColor) {
        super(context);
        getHolder().addCallback(this);
    }
    
    
    @Override
    public void surfaceChanged(SurfaceHolder holder, int format, int width, int height) {
        screenWidth = width;
        screenHeight = height;
    	Log.d("Screen", "Screen width: " + this.screenWidth + "\nScreen height: " + this.screenHeight);
    }
    
    @Override
    public void surfaceCreated(SurfaceHolder holder) {
    	screenReady = true;
    }
    
    @Override
    public void surfaceDestroyed(SurfaceHolder holder) {

    }
    
    @Override
    public boolean onTouchEvent(MotionEvent event) {
//    		Log.d("Screen", "Touch event occured at X: " + event.getX() + " Y: " + event.getY());
//        return super.onTouchEvent(event);
    		TestGame.mWorld.onTouchEvent(event);
    		return true;
    }
}
