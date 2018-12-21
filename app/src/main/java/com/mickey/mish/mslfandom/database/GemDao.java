package com.mickey.mish.mslfandom.database;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Delete;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;
import android.database.Cursor;

import java.util.List;

@Dao
public interface GemDao {


    @Query("SELECT * FROM Gem")
    List<Gem> getAll();

    @Query("SELECT * FROM Gem where mId = :id LIMIT 1")
    Gem findById(int id);


    @Insert
    void insertAll(Gem... gems);


    @Delete
    void delete(Gem gem);

    @Query("DELETE FROM Gem")
    void deleteAll();

    @Query("SELECT * FROM Gem")
    Cursor getCursorAll();


}
