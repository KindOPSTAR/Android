package com.mickey.mish.mslfandom.database;


import android.arch.persistence.room.Entity;
import android.arch.persistence.room.Ignore;
import android.arch.persistence.room.PrimaryKey;

@Entity
public class Gem {
    @PrimaryKey(autoGenerate = true)
    public int mId;

    public int mPicId;
    public double hp = 0;
    public double atk = 0;
    public double def = 0;
    public double rec  = 0;
    public double f_hp = 0;
    public double f_atk = 0;
    public double f_def = 0;
    public double f_rec  = 0;
    public double resist = 0;
    public double critRate  = 0;
    public double critDamage  = 0;


    public String main ="";

    public String subs="";

    public Gem(){
       this.mId = 0;
       this.mPicId= 0;
    }



    public Gem(int mId, int mPicId, double hp, double atk, double def, double rec, double f_hp, double f_atk, double f_def, double f_rec, double resist, double critRate, double critDamage,String main, String subs) {
        this.mId = mId;
        this.mPicId = mPicId;
        this.hp = hp;
        this.atk = atk;
        this.def = def;
        this.rec = rec;
        this.f_hp = f_hp;
        this.f_atk = f_atk;
        this.f_def = f_def;
        this.f_rec = f_rec;
        this.resist = resist;
        this.critRate = critRate;
        this.critDamage = critDamage;
        this.main = main;
        this.subs = subs;
    }


    public void addMain(double[] mainStatus) {


        this.hp = mainStatus[0];
        this.atk = mainStatus[1];
        this.def = mainStatus[2];
        this.rec = mainStatus[3];
        this.critDamage = mainStatus[4];
        this.critRate = mainStatus[5];
        this.resist = mainStatus[6];

        if (mainStatus[0] !=0)
            main = "Main Hp "+ mainStatus[0] + " %";
        if (mainStatus[1]!=0)
            main = "Main Atk "+ mainStatus[1] + " %";
        if (mainStatus[2] !=0)
            main = "Main Def "+ mainStatus[2] + " %";
        if (mainStatus[3] !=0)
            main = "Main Rec "+ mainStatus[3] + " %";
        if (mainStatus[4]!=0)
            main = "Main Critical Damage "+ mainStatus[4] + " %";
        if (mainStatus[5] !=0)
            main = "Main Critical Rate "+ mainStatus[5] + " %";
        if (mainStatus[6]!=0)
            main = "Main Resist "+ mainStatus[6] + " %";
    }

    public void addSub(double[] subStatus){
        this.hp += subStatus[0];
        this.def += subStatus[1];
        this.atk += subStatus[2];
        this.rec += subStatus[3];
        this.critDamage += subStatus[4];
        this.critRate += subStatus[5];
        this.resist += subStatus[6];
        this.f_hp += subStatus[7];
        this.f_def += subStatus[8];
        this.f_atk += subStatus[9];
        this.f_rec += subStatus[10];
        if (subStatus[0] !=0)
            subs += "Sub Hp "+ subStatus[0] + " %\n";
        if (subStatus[1]!=0)
            subs += "Sub Atk "+ subStatus[1] + " %\n";
        if (subStatus[2] !=0)
            subs += "Sub Def "+ subStatus[2] + " %\n";
        if (subStatus[3] !=0)
            subs += "Sub Rec "+ subStatus[3] + " %\n";
        if (subStatus[4]!=0)
            subs += "Sub Critical Damage "+ subStatus[4] + " %\n";
        if (subStatus[5] !=0)
            subs += "Sub Critical Rate "+ subStatus[5] + " %\n";
        if (subStatus[6]!=0)
            subs += "Sub Resist "+ subStatus[6] + " %\n";
        if (subStatus[7] !=0)
            subs += "Sub Flat Hp "+ subStatus[7] + " \n";
        if (subStatus[8]!=0)
            subs += "Sub Flat Atk "+ subStatus[8] + " \n";
        if (subStatus[9] !=0)
            subs += "Sub Flat Def "+ subStatus[9] + " \n";
        if (subStatus[10]!=0)
            subs += "Sub Flat Rec "+ subStatus[10] + " \n";

    }

    @Override
    public String toString() {



        return super.toString();
    }
}
