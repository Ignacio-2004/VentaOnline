package com.dam2.ventaonline.managers;

import static androidx.core.app.ActivityCompat.recreate;

import android.app.Activity;
import android.content.Context;
import android.content.res.Configuration;


import com.dam2.ventaonline.R;

import java.util.Locale;

public class LenguageMng extends XMLMng{

    private final Context C;
    private final XMLMng XMLMNGUSR;

    public LenguageMng(Context context,String name) {
        super(context,name); /*XMLlanguage*/
        C = context;
        XMLMNGUSR = new XMLMng(C,C.getString(R.string.XMLusrprefPref)); /*XMLuser*/
    }

    /**
     * Get the code of the language
     * @return The content of teh XML
     */
    public String getLng(){

        String user = XMLMNGUSR.get(C.getString(R.string.activeXMLTag),C.getString(R.string.DefNameUsr));

        return super.get(user,"en");

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

        C.getResources().updateConfiguration(config, C.getResources().getDisplayMetrics());

        super.set(XMLMNGUSR.get(C.getString(R.string.activeXMLTag),C.getString(R.string.DefNameUsr)),value);

        recreate(activity);


    }

    /**
     * Receive a code of language, in this case en,es to change the language and save in an XML
     * @param value Value of the XML tag
     * @param activity Name of the activity to recreate
     * @param skipRecreate If is true you skip recreate
     */
    public void setLng( String value,Activity activity,boolean skipRecreate ){

        Locale locale = new Locale(value);
        Locale.setDefault(locale);
        Configuration config = new Configuration();
        config.setLocale(locale);

        C.getResources().updateConfiguration(config, C.getResources().getDisplayMetrics());

        super.set(XMLMNGUSR.get(C.getString(R.string.activeXMLTag),C.getString(R.string.DefNameUsr)),value);

        if (!skipRecreate){
            recreate(activity);
        }


    }

}
