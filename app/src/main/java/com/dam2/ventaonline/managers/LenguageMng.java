package com.dam2.ventaonline.managers;

import static androidx.core.app.ActivityCompat.recreate;

import android.app.Activity;
import android.content.Context;
import android.content.res.Configuration;


import com.dam2.ventaonline.R;

import java.util.IllegalFormatCodePointException;
import java.util.Locale;

public class LenguageMng extends XMLMng{

    Context c;

    public LenguageMng(Context context,String name) {
        super(context,name);
        c = context;
    }

    /**
     * Get the code of the language
     * @param def Default value
     * @return The content of teh XML
     */
    public String getLng( String def){
        return super.get(c.getString(R.string.languageXMLTag),def);
    }

    /**
     * Receive a code of language, in this case en,es to change the language and save in an XML
     * @param value Value of the XML tag
     * @param activity Name of the activity to recreate
     */
    public void setLng( String value,Activity activity){

        Locale locale = new Locale(value);
        Locale.setDefault(locale);
        Configuration config = new Configuration();
        config.setLocale(locale);

        c.getResources().updateConfiguration(config, c.getResources().getDisplayMetrics());

        super.set(c.getString(R.string.languageXMLTag),value);

        recreate(activity);


    }

    /**
     * Receive a code of language, in this case en,es to change the language and save in an XML
     * @param value Value of the XML tag
     * @param activity Name of the activity to recreate
     * @param skip If is true you skip recreate
     */
    public void setLng( String value,Activity activity,boolean skip ){

        Locale locale = new Locale(value);
        Locale.setDefault(locale);
        Configuration config = new Configuration();
        config.setLocale(locale);

        c.getResources().updateConfiguration(config, c.getResources().getDisplayMetrics());

        super.set(c.getString(R.string.languageXMLTag),value);

        if (!skip){
            recreate(activity);
        }


    }

}
