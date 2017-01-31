package com.example.dm2.examen2ev;

import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.*;

import java.io.*;
import java.net.*;
import java.util.Scanner;

public class ServicioWeb extends AppCompatActivity {

    private Button btnInfo;
    private EditText elemen;

    private String simbolo,numero,peso,punto,densidad;
    private TextView lblsimbolo,lblnumero,lblpeso,lblpunto,lbldensidad;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_servicioweb);

        btnInfo=(Button)findViewById(R.id.btnInfo);
        elemen=(EditText)findViewById(R.id.elemen);

        lblsimbolo= (TextView) findViewById(R.id.simbolo);
        lblnumero= (TextView) findViewById(R.id.numero);
        lblpeso= (TextView) findViewById(R.id.peso);
        lblpunto= (TextView) findViewById(R.id.punto);
        lbldensidad= (TextView) findViewById(R.id.densidad);

        btnInfo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String elemento= elemen.getText().toString();
                AsyncPost tarea=new AsyncPost();
                tarea.execute(elemento);

                lblsimbolo.setText(R.string.simbolo);
                lblnumero.setText(R.string.numero);
                lblpeso.setText(R.string.peso);
                lblpunto.setText(R.string.punto);
                lbldensidad.setText(R.string.densidad);
            }
        });
    }

    private class AsyncPost extends AsyncTask<String,Void,Void>
    {

        @Override
        protected Void doInBackground(String... params) {


            try {
                HttpURLConnection conn;
                //URL url=new URL("http://www.webservicex.net/periodictable.asmx?op=GetAtomicNumber");
                URL url=new URL("http://www.webservicex.net/periodictable.asmx/GetAtomicNumber");

                //codificamos solo los valores de los parametros
                String param="ElementName="+ URLEncoder.encode(params[0],"UTF-8");

                conn= (HttpURLConnection) url.openConnection();

                //se estan cargamdo datos post si esta a true
                conn.setDoOutput(true);

                conn.setRequestMethod("POST");

                //enviar el post
                PrintWriter out=new PrintWriter((conn.getOutputStream()));
                out.print(param);
                out.close();

                //construir la cadena para almacenar la respuesta del servidor
                String result="";
                simbolo ="";
                numero ="";
                peso ="";
                punto ="";
                densidad ="";

                //comenzar a escuchar el stream(flujo)
                Scanner inStream=new Scanner(conn.getInputStream());

                //procesa el stream(flujo) y lo almacena en un String
                boolean a=false;
                while(inStream.hasNextLine())
                {
                    result=inStream.nextLine();
                    if(result.indexOf("Table")>0){
                        a=true;
                    }
                    if(a){
                        //resultado+=result;
                        if(result.indexOf("Symbol")>0)
                        {
                            simbolo=result.replace("&lt;Symbol&gt;", "").replace("&lt;/Symbol&gt;", "");
                        }
                        if(result.indexOf("AtomicNumber")>0)
                        {
                            numero=result.replace("&lt;AtomicNumber&gt;", "").replace("&lt;/AtomicNumber&gt;", "");
                        }
                        if(result.indexOf("AtomicWeight")>0)
                        {
                            peso=result.replace("&lt;AtomicWeight&gt;", "").replace("&lt;/AtomicWeight&gt;", "");
                        }
                        if(result.indexOf("BoilingPoint")>0)
                        {
                            punto=result.replace("&lt;BoilingPoint&gt;", "").replace("&lt;/BoilingPoint&gt;", "");
                        }
                        if(result.indexOf("Density")>0)
                        {
                            densidad=result.replace("&lt;Density&gt;", "").replace("&lt;/Density&gt;", "");
                        }
                    }
                }

            } catch (MalformedURLException e) {
                e.printStackTrace();
            } catch (UnsupportedEncodingException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
            return null;
        }
        protected void onPostExecute(Void result)
        {
            lblsimbolo.setText(lblsimbolo.getText().toString()+simbolo);
            lblnumero.setText(lblnumero.getText().toString()+numero);
            lblpeso.setText(lblpeso.getText().toString()+peso);
            lblpunto.setText(lblpunto.getText().toString()+punto);
            lbldensidad.setText(lbldensidad.getText().toString()+densidad);
        }
    }

    public void volver (View v)
    {
        finish();
    }
}
