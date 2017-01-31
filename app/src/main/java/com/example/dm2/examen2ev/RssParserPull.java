package com.example.dm2.examen2ev;


import android.util.Xml;

import org.xmlpull.v1.XmlPullParser;

import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

public class RssParserPull
{
    private URL rssUrl;

    public RssParserPull(String url)
    {
        try
        {
            this.rssUrl = new URL(url);
        }
        catch (MalformedURLException e)
        {
            throw new RuntimeException(e);
        }
    }

    public List<Tiempo> parse()
    {
        List<Tiempo> tiempos = null;
        XmlPullParser parser = Xml.newPullParser();

        try
        {
            parser.setInput(this.getInputStream(), null);

            int evento = parser.getEventType();

            Tiempo tiempoActual = null;

            while (evento != XmlPullParser.END_DOCUMENT)
            {
                String etiqueta = null;

                switch (evento)
                {
                    case XmlPullParser.START_DOCUMENT:

                        tiempos = new ArrayList<Tiempo>();
                        break;

                    case XmlPullParser.START_TAG:

                        etiqueta = parser.getName();

                        if (etiqueta.equals("hora"))
                        {
                            tiempoActual = new Tiempo();
                        }
                        else if (tiempoActual != null)
                        {
                            if (etiqueta.equals("hora_datos"))
                            {
                                tiempoActual.setHora(parser.nextText());
                            }
                            else if (etiqueta.equals("temperatura"))
                            {
                                tiempoActual.setTemp(parser.nextText());
                            }
                            else if (etiqueta.equals("texto"))
                            {
                                tiempoActual.setTexto(parser.nextText());
                            }
                        }
                        break;

                    case XmlPullParser.END_TAG:

                        etiqueta = parser.getName();

                        if (etiqueta.equals("hora") && tiempoActual != null)
                        {
                            tiempos.add(tiempoActual);
                        }
                        break;
                }

                evento = parser.next();
            }
        }
        catch (Exception ex)
        {
            throw new RuntimeException(ex);
        }

        return tiempos;
    }

    private InputStream getInputStream()
    {
        try
        {
            return rssUrl.openConnection().getInputStream();
        }
        catch (IOException e)
        {
            throw new RuntimeException(e);
        }
    }
}