package com.example.dm2.examen2ev;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.*;

public class MainActivity extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

    }

    public void xml(View v)
    {
        Intent intent = new Intent(MainActivity.this, Xml.class);
        startActivity(intent);
    }
    public void web(View v)
    {
        Intent intent = new Intent(MainActivity.this, ServicioWeb.class);
        startActivity(intent);
    }

    public void multi(View v)
    {
        Intent intent = new Intent(MainActivity.this, Multimedia.class);
        startActivity(intent);
    }


    public void exit(View v)
    {
        finish();
    }
}
