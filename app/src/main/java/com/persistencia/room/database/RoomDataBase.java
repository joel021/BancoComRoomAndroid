package com.persistencia.room.database;

import androidx.room.Database;
import androidx.room.RoomDatabase;

import com.persistencia.room.model.Headset;

@Database(entities = {Headset.class},version = 1)
public abstract class RoomDataBase extends RoomDatabase {
    public static String DATABASE_NAME = "roomdb";
    public abstract HeadsetDao headsetDao();
}
