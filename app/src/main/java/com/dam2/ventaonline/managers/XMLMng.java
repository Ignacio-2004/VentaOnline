package com.dam2.ventaonline.managers;

import android.content.Context;
import android.content.SharedPreferences;

public class XMLMng {

    SharedPreferences sp;
    SharedPreferences.Editor spe;

    public XMLMng (Context context, String name){

        sp = context.getSharedPreferences(name, Context.MODE_PRIVATE);

        spe = sp.edit();

    }

    /**
     * Get the content of the element
     * @param tagName name of the element of the XML
     * @param def default if the element doesn't have content
     * @return content of the element
     */
    public String get(String tagName,String def){
        return sp.getString(tagName,def);
    }

    /**
     * Set the content in an element
     * @param tagName name of the element
     * @param content content of the element
     */
    public void set(String tagName, String content){
        spe.putString(tagName,content);
        spe.apply();
    }

    /**
     * Get the content of the element
     * @param tagName name of the element of the XML
     * @param def default if the element doesn't have content
     * @return content of the element
     */
    public int get(String tagName,int def){
        return sp.getInt(tagName,def);
    }

    /**
     * Set the content in an element
     * @param tagName name of the element
     * @param content content of the element
     */
    public void set(String tagName, int content){
        spe.putInt(tagName,content);
        spe.apply();
    }
}
