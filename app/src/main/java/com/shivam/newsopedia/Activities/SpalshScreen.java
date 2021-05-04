package com.shivam.newsopedia.Activities;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import com.shivam.newsopedia.R;

import org.w3c.dom.Text;

import static java.lang.Thread.sleep;

public class SpalshScreen extends AppCompatActivity {
    ImageView spalshImg;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_spalsh_screen);

        spalshImg = findViewById(R.id.spalshImg);

        final Thread thread = new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    sleep(1500);
                    Intent intent = new Intent(SpalshScreen.this,MainActivity.class);
                    startActivity(intent);
                    finish();
                }
                catch (InterruptedException e){
                    e.printStackTrace();
                }
            }
        });thread.start();
    }

}
