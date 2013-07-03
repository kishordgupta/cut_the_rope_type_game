package com.kd.grabandcut.menu;


import com.kd.grabandcut.menu.R;
import com.kd.grabandcut.menu.level.SoundManager;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Message;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class OptionMenuScreen extends Activity {
	public Button music ;
	public Button sound ;
	
	public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.optionscreen);


        music = (Button) findViewById(R.id.music);
        music.setText(MainScreen.musicFlag?"Music: ON" : "Music: OFF");
        music.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
            	SoundManager.playSound(3, 1);

            	if(MainScreen.musicFlag){
            		MainScreen.musicFlag = false;
                	MainScreen.musicChanged = true;
            	}
            	else{
            		MainScreen.musicFlag = true;
                	MainScreen.musicChanged = true;
            	}
            	music.setText(MainScreen.musicFlag?"Music: ON" : "Music: OFF");
//            	MainScreen.musicHandler.resume();
//            	MainScreen.synchronizeMusic.notifyAll();
            	MainScreen.h.sendEmptyMessage(0);
//                finish();
            }

        });
        
        sound = (Button) findViewById(R.id.sound);
        sound.setText(SoundManager.musicFlag?"Sound: ON" : "Sound: OFF");
        sound.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
            	SoundManager.playSound(3, 1);
            	if(SoundManager.musicFlag)
            		SoundManager.musicFlag = false;
            	else
            		SoundManager.musicFlag = true;
            	sound.setText(SoundManager.musicFlag?"Sound: ON" : "Sound: OFF");
//                finish();
            }

        });
        
        
        Button reset = (Button) findViewById(R.id.resetGame);
        reset.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
            	SoundManager.playSound(3, 1);
            	MainScreen.dbh.resetTable();
                Toast.makeText(OptionMenuScreen.this, "Game Reseted",Toast.LENGTH_SHORT).show();
            	
//            	Intent myIntent = new Intent(view.getContext(), MainScreen.class);
//                startActivityForResult(myIntent, 0);
                finish();
            }

        });
        
        
        Button back = (Button) findViewById(R.id.back);
        back.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
            	SoundManager.playSound(3, 1);
//            	Intent myIntent = new Intent(view.getContext(), MainScreen.class);
//                startActivityForResult(myIntent, 0);
                finish();
            }

        });

        
}

}
