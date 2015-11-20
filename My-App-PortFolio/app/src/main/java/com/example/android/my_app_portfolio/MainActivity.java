package com.example.android.my_app_portfolio;

import android.app.Activity;
import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    private Button btn,btn1,btn2,btn3,btn4,btn5;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        btn = (Button)findViewById(R.id.button);
        btn.setOnClickListener(this);

        btn1 = (Button)findViewById(R.id.button2);
        btn1.setOnClickListener(this);
        btn2 = (Button)findViewById(R.id.button3);
        btn2.setOnClickListener(this);
        btn3 = (Button)findViewById(R.id.button4);
        btn3.setOnClickListener(this);
        btn4 = (Button)findViewById(R.id.button5);
        btn4.setOnClickListener(this);
        btn5 = (Button)findViewById(R.id.button6);
        btn5.setOnClickListener(this);


    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.button:
                Toast.makeText(getApplicationContext(), "This button will launch Spotify Streamer", Toast.LENGTH_LONG).show();
                break;

            case R.id.button2:
                Toast.makeText(getApplicationContext(), "This button will launch Scores App", Toast.LENGTH_LONG).show();
                break;

            case R.id.button3:
                Toast.makeText(getApplicationContext(), "This button will launch Library App", Toast.LENGTH_LONG).show();
                break;

            case R.id.button4:
                Toast.makeText(getApplicationContext(), "This button will launch BuildIt Bigger App", Toast.LENGTH_LONG).show();
                break;

            case R.id.button5:
                Toast.makeText(getApplicationContext(), "This button will launch XYZ Reader App", Toast.LENGTH_LONG).show();
                break;

            case R.id.button6:
                Toast.makeText(getApplicationContext(), "This button will launch Capstone Project App", Toast.LENGTH_LONG).show();
                break;



        }
    }
}
