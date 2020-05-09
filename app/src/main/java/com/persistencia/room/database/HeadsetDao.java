package com.persistencia.room.database;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import com.persistencia.room.model.Headset;

import java.util.List;

@Dao
public interface HeadsetDao {

    @Query("SELECT * FROM headset")
    List<Headset> getAll();

    @Query("SELECT * FROM headset WHERE title LIKE :title")
    Headset findByTitle(String title);

    @Query("SELECT * FROM headset WHERE model LIKE :model")
    List<Headset> findAllByModel(String model);

    @Query("SELECT * FROM headset WHERE id = :id LIMIT 1")
    Headset findById(int id);

    @Insert
    void save(Headset headset);

    @Delete
    void delete(Headset headset);

    @Update
    void update(Headset headset);

}
