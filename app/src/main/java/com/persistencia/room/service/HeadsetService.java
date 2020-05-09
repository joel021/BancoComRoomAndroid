package com.persistencia.room.service;

import android.content.Context;

import androidx.room.Room;

import com.persistencia.room.database.HeadsetDao;
import com.persistencia.room.database.RoomDataBase;
import com.persistencia.room.model.Headset;

import java.util.List;

public class HeadsetService {
    private HeadsetDao headsetDao;

    public HeadsetService(Context context){
        RoomDataBase db = Room.databaseBuilder(context, RoomDataBase.class, RoomDataBase.DATABASE_NAME).build();
        headsetDao = db.headsetDao();
    }

    public List<Headset> getAll(){
        return headsetDao.getAll();
    }

    public Headset findByTitle(String title){
        // sua regra
        return headsetDao.findByTitle(title);
    }

    public List<Headset> findAllByModel(String model){
        return headsetDao.findAllByModel(model);
    }

    public void save(Headset headset){

        if(headset == null)
            return;

        if(headsetDao.findById(headset.id) != null)
            headsetDao.update(headset);
        else
            headsetDao.save(headset);
    }

    public void delete(Headset headset){
        headsetDao.delete(headset);
    }

}
