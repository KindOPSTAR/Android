package com.mickey.mish.mslfandom.database;

import android.arch.persistence.db.SupportSQLiteDatabase;
import android.arch.persistence.room.Database;
import android.arch.persistence.room.Room;
import android.arch.persistence.room.RoomDatabase;
import android.content.Context;
import android.support.annotation.NonNull;
import android.util.Log;

import com.mickey.mish.mslfandom.R;

import java.util.concurrent.Executors;

/**
 * database of gem
 * including in the value range of database
 */
@Database(entities = {Gem.class}, version = 1)
public abstract class GemDatabase extends RoomDatabase {
    private static GemDatabase INSTANCE;
    public abstract GemDao gemDao();
    public static GemDatabase getINSTANCE(Context context) {
        if (INSTANCE == null) {
            INSTANCE = buildDatabaseInstance(context);
        }
        return INSTANCE;
    }

    public static void destroyInstance() {
        INSTANCE = null;
    }


    private static GemDatabase buildDatabaseInstance(final Context context) {
        return Room.databaseBuilder(context,
                GemDatabase.class,
                "Gem").addCallback(new Callback() {
            @Override
            public void onCreate(@NonNull SupportSQLiteDatabase db) {
                super.onCreate(db);
               // Log.i("message","First time created Database");

                Executors.newSingleThreadScheduledExecutor().execute(new Runnable() {
                    @Override
                    public void run() {
                        getINSTANCE(context).gemDao().insertAll(populateData());
                    }
                });

            }
        }).allowMainThreadQueries().build();
    }

    public void cleanUp(){
        INSTANCE = null;
    }

    /**
     * when adding gem into database, we will using these range which showed below
     * @return
     */
    public static Gem[] populateData() {
        return new Gem[] {
                new Gem(1,R.drawable.ruin_square_15,0d,7d,68d,0d,0d,0d,0d,0d,20d,20d,7d,
                        "Main Def 68 %",
                        "Sub Atk 7% \nSub Resist 20 % \nSub Critical Rate 20 % \nSub Critical Damage 7%\n"),
                new Gem(2,R.drawable.intuition_square_15,0d,68d,4d,0d,0d,0d,0d,0d,20d,20d,7d,
                        "Main Atk 68 %",
                        "Sub Def 7% \nSub Resist 20 % \nSub Critical Rate 20 % \nSub Critical Damage 7% \n"),
                new Gem(3,R.drawable.pugilist_square_15,68d,0d,20d,0d,0d,0d,0d,0d,20d,7.5d,7d,
                        "Main Hp 68 %",
                        "Sub Def 20% \nSub Resist 20 % \nSub Critical Rate 7.5 % \nSub Critical Damage 7% \n"),
                new Gem(4,R.drawable.siphon_square_15,0d,20d,0d,7d,0d,0d,0d,0d,16d,54d,10d,
                        "Main Critical Rate 54 %",
                        "Sub Atk 20% \nSub Rec 7 % \nSub Resist 16 % \nSub Critical Damage 10% \n"),
                new Gem(5,R.drawable.valor_square_15,0d,12d,0d,7d,0d,0d,0d,0d,8d,20d,70d,
                        "Main Critical Damage 70 %",
                        "Sub Atk 12% \nSub Rec 7 % \nSub Resist 8 % \nSub Critical Rate 20% \n"),
                new Gem(6,R.drawable.life_square_15,68d,0d,14d,7d,0d,0d,0d,0d,8d,7d,0d,
                        "Main Hp 68 %",
                        "Sub Def 14% \nSub Rec 7 % \nSub Resist 8 % \nSub Critical Rate 7% \n"),

        };
    }

}
