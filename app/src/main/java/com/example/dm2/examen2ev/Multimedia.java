package com.example.dm2.examen2ev;

import android.media.MediaPlayer;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;

public class Multimedia extends AppCompatActivity {

    private Spinner spinner;
    String selected;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_multimedia);

        spinner=(Spinner)findViewById(R.id.spinner);
        String[] datos=new String[]{"audio","disparo","explosion"};

        ArrayAdapter<String> adapter= new ArrayAdapter<String>(this,android.R.layout.simple_spinner_item,datos);
        spinner.setAdapter(adapter);
        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener(){
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int position, long id) {
                selected = adapterView.getItemAtPosition(position).toString();

                int idr=getResources().getIdentifier(selected,"raw",getPackageName());
                MediaPlayer mediaplayer=MediaPlayer.create(Multimedia.this,idr);
                mediaplayer.start();
            }

            public void onNothingSelected (AdapterView<?> adapterView){
                //do nothing
            }
        });
    }


    public void volver (View v)
    {
        finish();
    }
}
