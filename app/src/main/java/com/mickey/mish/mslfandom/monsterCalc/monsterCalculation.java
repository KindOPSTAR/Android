package com.mickey.mish.mslfandom.monsterCalc;

import com.mickey.mish.mslfandom.database.Gem;
import com.mickey.mish.mslfandom.database.Monster;

/**
 * calculation
 * saving formula here for calculation
 */

public class monsterCalculation {
    private Monster monster;
    private Gem gem_1;
    private Gem gem_2;
    private Gem gem_3;
    private  double skill_mul;
    private specialSkill sk;
    private double skillAmount;


    public monsterCalculation(Monster monster, Gem gem1, Gem gem2, Gem gem3){

        this.monster = monster;
        this.gem_1 = gem1;
        this.gem_2 = gem2;
        this.gem_3 = gem3;
    }

    public Monster getMonster(){
        return monster;
    }

    public int get_hp(){
        double monster_hp = monster.hp +  monster.hp * this.gem_1.hp/100 +  monster.hp * this.gem_2.hp/100 +  monster.hp * this.gem_3.hp/100;
        monster_hp = monster_hp + gem_1.f_hp + gem_2.f_hp + gem_3.f_hp;
        int result = (int) monster_hp;
        return result;
    }
    public String get_hp_percentage(){
        return  String.valueOf(gem_1.hp + gem_2.hp + gem_3.hp);
    }
    public String get_atk_percentage(){
        return  String.valueOf(gem_1.atk + gem_2.atk + gem_3.atk);
    }

    public String get_def_percentage(){
        return  String.valueOf(gem_1.def + gem_2.def + gem_3.def);
    }

    public String get_rec_percentage(){
        return  String.valueOf(gem_1.rec + gem_2.rec + gem_3.rec);
    }

    public String get_critdamage_percentage(){
        return  String.valueOf(gem_1.critDamage + gem_2.critDamage + gem_3.critDamage);
    }

    public String get_critrate_percentage(){
        double result = gem_1.critRate + gem_2.critRate + gem_3.critRate;
        if (result > 100){result = 100;}
        return  String.valueOf(result);
    }

    public String get_resist_percentage(){
        double result  = gem_1.resist + gem_2.resist + gem_3.resist;
        if (result > 85){result = 85;}
        return  String.valueOf(result);
    }

    public String get_hp_flat(){
        return  String.valueOf(gem_1.f_hp + gem_2.f_hp + gem_3.f_hp);
    }
    public String get_atk_flat(){
        return  String.valueOf(gem_1.f_atk + gem_2.f_atk + gem_3.f_atk);
    }

    public String get_def_flat(){
        return  String.valueOf(gem_1.f_def + gem_2.f_def + gem_3.f_def);
    }

    public String get_rec_flat(){
        return  String.valueOf(gem_1.f_rec + gem_2.f_rec + gem_3.f_rec);
    }


    public int get_atk(){
        double monster_atk = monster.atk +  monster.atk * this.gem_1.atk/100 +  monster.atk * this.gem_2.atk/100 +  monster.atk * this.gem_3.atk/100;
        monster_atk = monster_atk + gem_1.f_atk + gem_2.f_atk + gem_3.f_atk;
        int result = (int) monster_atk;
        return result;
    }
    public int get_def(){
        double monster_def = monster.def +  monster.def * this.gem_1.def/100 +  monster.def * this.gem_2.def/100 +  monster.def * this.gem_3.def/100;
        monster_def = monster_def + gem_1.f_def + gem_2.f_def + gem_3.f_def;
        int result = (int) monster_def;
        return result;
    }
    public int get_rec(){
        double monster_rec = monster.rec +  monster.rec* this.gem_1.rec/100 +  monster.rec * this.gem_2.rec/100 +  monster.rec * this.gem_3.rec/100;
        monster_rec = monster_rec + gem_1.f_rec + gem_2.f_rec + gem_3.f_rec;
        int result = (int) monster_rec;
        return result;
    }

    public double get_resist(){

        double result = monster.resist + gem_1.resist +  gem_2.resist +  gem_3.resist;
        if (result > 85){result = 85;}
        return result;

    }

    public double get_crit_damage(){
        return  monster.critDamage +  gem_1.critDamage+  gem_2.critDamage +  gem_3.critDamage;
    }
    public double get_crit_rate(){

        double result =  monster.critRate +  gem_1.critRate+  gem_2.critRate +  gem_3.critRate;
        if (result > 100){result = 100;}
        return result;
    }


    public int calc_max_dmg( specialSkill sk){
        double result = 0;
        switch(sk) {
            case None:
                result = this.get_atk() * (100+this.get_crit_damage()/100);
                break;
            case Predator:
                result =  this.skill_mul * this.calc_max_dmg(specialSkill.None);
                break;
            case Element:
                result =  this.skill_mul * this.calc_max_dmg(specialSkill.None);
                break;
            case Hunter:
                result =  this.skill_mul * this.calc_max_dmg(specialSkill.None);
                break;
            case Courageous:
                break;
            case HpAggrs:
                result = 0.3 * this.get_hp() * this.get_crit_damage() * this.get_crit_rate()/100;
                break;
            case DefAggrs:
                result = 4 * this.get_def() * this.get_crit_damage() * this.get_crit_rate()/100;
                break;
            case RecAggrs:
                result = 6.5 * this.get_rec() * this.get_crit_damage() * this.get_crit_rate()/100;
                break;
        }
        return (int)(result);
    }

    public double calc_EHP(double def_debuff){
        double result = 0;
        result = this.get_hp() /(1-this.calc_defReduction(def_debuff));
        return (int)(result);
    }

    public double calc_defReduction(double def_debuff){
        double result = 0;
        double monster_def_mul = 1- def_debuff;
        result = (this.get_def() * monster_def_mul)/(1200 + this.get_def()*monster_def_mul);
        return result;
    }

}
