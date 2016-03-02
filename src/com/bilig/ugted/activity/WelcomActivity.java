package com.bilig.ugted.activity;

import com.bilig.ugted.activity.R;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.Window;

public class WelcomActivity extends Activity {
	
	protected boolean _active = true;
    protected int _splashTime = 3000;
    
    @Override
    protected void onCreate(Bundle savedInstanceState) {
    	// TODO Auto-generated method stub
    	super.onCreate(savedInstanceState);
    	requestWindowFeature(Window.FEATURE_NO_TITLE);
    	setContentView(R.layout.welcome);
    	
      	Thread splashTread = new Thread() {
             @Override
             public void run() {
                 try {
                     int waited = 0;
                     while(_active && (waited < _splashTime)) {
                         sleep(100);
                         if(_active) {
                             waited += 100;
                         }
                     }
                 } catch(InterruptedException e) {
                     // do nothing
                	 throw new RuntimeException(e);
                 } finally {
                    redirectTo();
                 }
             }
         };
         splashTread.start();
     }
    
     @Override
     public boolean onTouchEvent(MotionEvent event) {
         if (event.getAction() == MotionEvent.ACTION_DOWN) {
             _active = false;
         }
         return true;
     }
     
     private void redirectTo() {
 		startActivity(new Intent(getApplicationContext(), MainActivity.class));
 		finish();
 	}
        

}
