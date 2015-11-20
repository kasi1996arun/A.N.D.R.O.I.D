package com.example.android.my_app_portfolio;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Button btn1 = (Button) this.findViewById(R.id.button);
        btn1.setOnClickListener((View.OnClickListener) this);

        Button btn2 = (Button) this.findViewById(R.id.button2);
        btn2.setOnClickListener((View.OnClickListener) this);

        Button btn3 = (Button) this.findViewById(R.id.button3);
        btn3.setOnClickListener((View.OnClickListener) this);

        Button btn4 = (Button) this.findViewById(R.id.button3);
        btn4.setOnClickListener((View.OnClickListener) this);

        Button btn5 = (Button) this.findViewById(R.id.button4);
        btn5.setOnClickListener((View.OnClickListener) this);

        Button btn6 = (Button) this.findViewById(R.id.button5);
        btn6.setOnClickListener((View.OnClickListener) this);

        Button btn7 = (Button) this.findViewById(R.id.button6);
        btn7.setOnClickListener((View.OnClickListener) this);
    }

    public void OnClick(View v) {
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
