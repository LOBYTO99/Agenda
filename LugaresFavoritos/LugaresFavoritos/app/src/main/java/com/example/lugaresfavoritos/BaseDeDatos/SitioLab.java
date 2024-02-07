package com.example.lugaresfavoritos.BaseDeDatos;


import android.content.Context;

import androidx.room.Room;

import java.util.List;

public class SitioLab {

    private static SitioLab mSitioLab;
    private SitioDAO mSitioDao;

    public SitioLab(Context context){
        Context appContext=context.getApplicationContext();
        SitioDataBase database = Room.databaseBuilder(
                appContext,SitioDataBase.class,"sitio")
                .allowMainThreadQueries().build();
        mSitioDao=database.daoSitio();
    }
    public static SitioLab get(Context context){
        if (mSitioLab==null){
            mSitioLab=new SitioLab(context);
        }
        return mSitioLab;
    }
    public List<Sitio> getSitios(){
        return mSitioDao.getSitio();
    }
    public Sitio getSitio(String id){
        return mSitioDao.getSitio(id);
    }


    public void addSitio(Sitio sitio){
        mSitioDao.insertSitio(sitio);
    }
    public void deleteSitio(Sitio sitio){
        mSitioDao.deleteSitio(sitio);
    }
    public void updateSitio(Sitio sitio){
        mSitioDao.updateSitio(sitio);
    }

}
