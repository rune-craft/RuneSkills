package org.runecraft.runeskills;

import org.runecraft.runecharacters.Character;
import org.runecraft.runecharacters.atributes.Stat;
import org.runecraft.runecharacters.atributes.enums.StatType;

import java.util.Random;

public class Attack {
    private int minDamage;
    private int maxDamage;
    private Character target;
    private Character attacker;

    public Attack(int minDamage, int maxDamage, Character target, Character attacker) {
        this.minDamage = minDamage;
        this.maxDamage = maxDamage;
        this.target = target;
        this.attacker = attacker;
    }

    public void attack(){
        Random rnd = new Random();
        int dexterity = attacker.getStat(StatType.DEXTERITY).getLevel();
        int bound = rnd.nextInt(100);

        int damage = dexterity <= bound ? getCriticalDamage() : getDamage();

    }

    public int getDamage(){
        Random rnd = new Random();
        int bound = maxDamage - minDamage;
        int i = rnd.nextInt(bound);
        int damage = i + minDamage;

        damage+=damage*((attacker.getStat(StatType.STRENGHT).getLevel())/100);

        if(attacker.getPrimalElement().getWeakness() == target.getPrimalElement()){
            damage = (int) (damage*1.3);
        }else if(target.getPrimalElement().getWeakness() == attacker.getPrimalElement()){
            damage = (int) (damage*0.7);
        }

        damage-=damage*((target.getStat(StatType.DEFENSE).getLevel()/100));

        return damage;
    }

    public int getCriticalDamage(){
        int baseDamage = getDamage();
        return (baseDamage*baseDamage)-baseDamage*2;
    }
}
