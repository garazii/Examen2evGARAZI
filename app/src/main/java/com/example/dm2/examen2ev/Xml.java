package com.example.dm2.examen2ev;

import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.util.List;

public class Xml extends AppCompatActivity {

    private Button btnBilbo,btnVito,btnDonos;
    private TextView txtresult;
    private String ciudad;

    private List<Tiempo> tiempos;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_xml);

        btnBilbo = (Button)findViewById(R.id.btnBilbo);
        btnVito = (Button)findViewById(R.id.btnVito);
        btnDonos = (Button)findViewById(R.id.btnDonos);

        txtresult = (TextView)findViewById(R.id.txtresult);

        btnBilbo.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                ciudad=btnBilbo.getText().toString();
                txtresult.setText(R.string.result);
                //Sin Tarea Asíncrona
                //RssParserPull saxparser =
                //	new RssParserPull("http://212.170.237.10/rss/rss.aspx");

                //noticias = saxparser.parse();

                //Tratamos la lista de noticias
                //Por ejemplo: escribimos los títulos en pantalla
                //txtResultado.setText("");
                //for(int i=0; i<noticias.size(); i++)
                //{
                //	txtResultado.setText(
                //		txtResultado.getText().toString() +
                //			System.getProperty("line.separator") +
                //			noticias.get(i).getTitulo());
                //}

                //Con Tarea Asíncrona
                CargarXmlTask tarea = new CargarXmlTask();
                tarea.execute("http://xml.tutiempo.net/xml/8050.xml");
            }
        });

        btnVito.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                ciudad=btnVito.getText().toString();
                txtresult.setText(R.string.result);
                //Sin Tarea Asíncrona
                //RssParserPull saxparser =
                //	new RssParserPull("http://212.170.237.10/rss/rss.aspx");

                //noticias = saxparser.parse();

                //Tratamos la lista de noticias
                //Por ejemplo: escribimos los títulos en pantalla
                //txtResultado.setText("");
                //for(int i=0; i<noticias.size(); i++)
                //{
                //	txtResultado.setText(
                //		txtResultado.getText().toString() +
                //			System.getProperty("line.separator") +
                //			noticias.get(i).getTitulo());
                //}

                //Con Tarea Asíncrona
                CargarXmlTask tarea = new CargarXmlTask();
                tarea.execute("http://xml.tutiempo.net/xml/8043.xml");
            }
        });

        btnDonos.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                ciudad=btnDonos.getText().toString();
                txtresult.setText(R.string.result);
                //Sin Tarea Asíncrona
                //RssParserPull saxparser =
                //	new RssParserPull("http://212.170.237.10/rss/rss.aspx");

                //noticias = saxparser.parse();

                //Tratamos la lista de noticias
                //Por ejemplo: escribimos los títulos en pantalla
                //txtResultado.setText("");
                //for(int i=0; i<noticias.size(); i++)
                //{
                //	txtResultado.setText(
                //		txtResultado.getText().toString() +
                //			System.getProperty("line.separator") +
                //			noticias.get(i).getTitulo());
                //}

                //Con Tarea Asíncrona
                CargarXmlTask tarea = new CargarXmlTask();
                tarea.execute("http://xml.tutiempo.net/xml/4917.xml");
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    //Tarea Asíncrona para cargar un XML en segundo plano
    private class CargarXmlTask extends AsyncTask<String,Integer,Boolean> {

        protected Boolean doInBackground(String... params) {

            RssParserPull saxparser =
                    new RssParserPull(params[0]);

            tiempos = saxparser.parse();

            return true;
        }

        protected void onPostExecute(Boolean result) {

            //Tratamos la lista de timepos
            //Por ejemplo: escribimos la hora en pantalla
            /*txtresult.setText(txtresult.getText()+" "+ciudad);
            txthora.setText("Hora: ");
            for(int i=0; i<tiempos.size(); i++)
            {

                txthora.setText("Hora: "+tiempos.get(0).getHora());
                txttemp.setText("Temperatura: "+tiempos.get(0).getTemp());
                txtcielo.setText("Estado del cielo: "+tiempos.get(0).getTexto());
                if(i==0)
                {
                    break;
                }
            }*/

           // txtresult.setText("");
            for(int i=0; i<tiempos.size(); i++)
            {
                txtresult.setText(
                        txtresult.getText().toString() + ciudad + System.getProperty("line.separator") +
                                System.getProperty("line.separator") + "Hora: " + tiempos.get(0).getHora() +
                                System.getProperty("line.separator") + "Temperatura: " + tiempos.get(0).getTemp() +
                                System.getProperty("line.separator") + "Estado del cielo: " + tiempos.get(0).getTexto());
                if(i==0)
                {
                    break;
                }
            }
        }
    }
    public void volver (View v)
    {
        finish();
    }
}
