package com.mickey.mish.mslfandom.database;


import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;

@Entity
public class Monster {
    @PrimaryKey(autoGenerate = true)
    public int mId;

    public String mName;
    public String mElement;
    public String mType;
    public int mPicId;
    public int hp;
    public int atk;
    public int def;
    public int rec;
    public int resist;
    public double critRate;
    public double critDamage;


    public Monster() {

    }

    public Monster(int id, String name, String element, String type, int pic, int hp, int atk, int def, int rec,double critDamage, double critRate, int resist) {
        this.mId = id;
        this.mName = name;
        this.mElement = element;
        this.mType = type;
        this.hp = hp;
        this.atk = atk;
        this.def = def;
        this.rec = rec;
        this.resist = resist;
        this.critRate = critRate;
        this.critDamage = critDamage;
        this.mPicId = pic;

        }






}