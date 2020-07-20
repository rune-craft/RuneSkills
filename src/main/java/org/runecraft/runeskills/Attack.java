package org.runecraft.runeskills;

import org.runecraft.runecharacters.Character;
import org.runecraft.runecharacters.atributes.Stat;
import org.runecraft.runecharacters.atributes.enums.StatType;
import org.spongepowered.api.entity.Entity;
import org.spongepowered.api.event.cause.Cause;
import org.spongepowered.api.event.cause.entity.damage.DamageFunction;
import org.spongepowered.api.event.cause.entity.damage.DamageModifier;
import org.spongepowered.api.event.cause.entity.damage.DamageModifierType;
import org.spongepowered.api.event.cause.entity.damage.DamageType;
import org.spongepowered.api.event.cause.entity.damage.source.DamageSource;
import org.spongepowered.api.event.cause.entity.damage.source.DamageSources;
import org.spongepowered.api.event.cause.entity.damage.source.EntityDamageSource;
import org.spongepowered.api.event.entity.DamageEntityEvent;

import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.Set;
import java.util.function.DoubleUnaryOperator;

public class Attack {
    private int minDamage;
    private int maxDamage;
    private Character target;
    private Character attacker;
    private double delay;

    public Attack(int minDamage, int maxDamage, Character target, Character attacker, double delay) {
        this.minDamage = minDamage;
        this.maxDamage = maxDamage;
        this.target = target;
        this.delay = delay;
        this.attacker = attacker;
    }

    public void attack(){
        Random rnd = new Random();
        int dexterity = attacker.getStat(StatType.DEXTERITY).getLevel() / 2;
        int bound = rnd.nextInt(100);

        int damage = dexterity <= bound ? getCriticalDamage() : getDamage();

        target.getOwner().getPlayer().get().damage(damage / 20,
                DamageSource.builder().exhaustion(delay).magical().build());

        target.getOwner().getPlayer().get().damage(getDamage(), EntityDamageSource.builder().magical().entity(attacker.getOwner().getPlayer().get()).build());
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
