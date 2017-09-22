package com.example.akil.onusondhan;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import static java.lang.Thread.sleep;

public class SplashScreen extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash_screen);
        new Thread(){
            public void run(){
                try{
                    sleep(5000);
                    startActivity(new Intent(SplashScreen.this,MainActivity.class));
                    finish();

                }catch(InterruptedException e){
                    e.printStackTrace();
                }
            }
        }.start();
    }
}
