package com.mickey.mish.mslfandom.database;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Delete;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;
import android.database.Cursor;

import java.util.List;

@Dao
public interface MonsterDao {


    @Query("SELECT * FROM Monster")
    List<Monster> getAll();

    @Query("SELECT * FROM Monster where mId = :id LIMIT 1")
    Monster findById(int id);

    @Query("SELECT * FROM Monster where mName = :name LIMIT 1")
    Monster findByName(String name);

    @Insert
    void insertAll(Monster... monsters);

    @Delete
    void delete(Monster monster);

    @Query("DELETE FROM Monster")
    void deleteAll();

    @Query("SELECT * FROM Monster")
    Cursor getCursorAll();


}
