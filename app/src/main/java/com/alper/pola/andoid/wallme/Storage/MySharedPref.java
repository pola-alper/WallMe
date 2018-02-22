package com.alper.pola.andoid.wallme.Storage;

import android.content.Context;
import android.content.SharedPreferences;

/**
 * Created by Chetan on 5/18/2017.
 */

public class MySharedPref {


    private final String name="name";









    private SharedPreferences sharedPrefData;


    private MySharedPref() {
    }

    private static MySharedPref sharedPref = null;

    public static MySharedPref getInstance() {
        if (sharedPref == null)
            sharedPref = new MySharedPref();
        return sharedPref;
    }

    public void clearData(){
        sharedPrefData.edit().clear().apply();
    }

    public void setSharedPreferences(Context context) {
        String PREFNAME = "MySharedPref";
        sharedPrefData = context.getSharedPreferences(PREFNAME, Context.MODE_PRIVATE);
    }

    public SharedPreferences getSharedPref(){
        return sharedPrefData;
    }


    public boolean savename(Boolean name) {
        SharedPreferences.Editor editor = sharedPrefData.edit();
        editor.putBoolean(this.name,name);
        return editor.commit();
    }







    public boolean getname(){
        return sharedPrefData.getBoolean(name,false);}



}