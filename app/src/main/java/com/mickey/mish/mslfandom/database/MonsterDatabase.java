package com.mickey.mish.mslfandom.database;

import android.arch.persistence.db.SupportSQLiteDatabase;
import android.arch.persistence.room.Database;
import android.arch.persistence.room.Room;
import android.arch.persistence.room.RoomDatabase;
import android.content.Context;
import android.support.annotation.NonNull;

import com.mickey.mish.mslfandom.R;

import java.util.concurrent.Executors;

/**
 * database of monster,
 * which include in the value range for monster
 * and call back function
 */
@Database(entities = {Monster.class}, version = 1)
public abstract class MonsterDatabase extends RoomDatabase {
    private static MonsterDatabase INSTANCE;
    public abstract MonsterDao monsterDao();
    public static MonsterDatabase getINSTANCE(Context context) {
        if (INSTANCE == null) {
            INSTANCE = buildDatabaseInstance(context);
        }
        return INSTANCE;
    }

    public static void destroyInstance() {
        INSTANCE = null;
    }


    private static MonsterDatabase buildDatabaseInstance(final Context context) {
        return Room.databaseBuilder(context,
                MonsterDatabase.class,
                "Monster").addCallback(new Callback() {
            @Override
            public void onCreate(@NonNull SupportSQLiteDatabase db) {
                super.onCreate(db);

                Executors.newSingleThreadScheduledExecutor().execute(new Runnable() {
                    @Override
                    public void run() {
                        getINSTANCE(context).monsterDao().insertAll(populateData());
                    }
                });

            }
        }).allowMainThreadQueries().build();


    }

    public void cleanUp(){
        INSTANCE = null;
    }
    public static Monster[] populateData() {
        return new Monster[] {
                new Monster(1, "Odin", "Light","Tank",R.drawable.allmother_odin_light,41919,2099,2916,2677,50d,20d,0),
                new Monster(2, "Persephone", "Light","Defender",R.drawable.queen_persephone_light,31094,2649,3548,2023,50d,20d,0),
                new Monster(3, "Persephone", "Dark","Attacker",R.drawable.queen_persephone_dark,27151,3834,2704,2043,100d,10d,0),
                new Monster(4, "Arthur", "Light","Attacker",R.drawable.arthur_pendragon_light,24679,3936,2411,2125,50d,20d,0),
                new Monster(5, "Merlin", "Dark","Tank",R.drawable.myrddin_wyllt_dark,40298,2398,2834,1996,100d,10d,0),
                new Monster(6, "Valkyrie", "Light","Balanced",R.drawable.sigrun_light,31003,2955,2808,2644,50d,20d,0),
        };
    }








}
