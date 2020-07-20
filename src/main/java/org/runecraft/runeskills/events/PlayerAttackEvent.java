package org.runecraft.runeskills.events;

import org.runecraft.runecharacters.Character;
import org.runecraft.runeskills.Attack;
import org.spongepowered.api.entity.Entity;
import org.spongepowered.api.entity.living.player.User;
import org.spongepowered.api.event.cause.Cause;
import org.spongepowered.api.event.cause.EventContext;
import org.spongepowered.api.event.cause.EventContextKeys;
import org.spongepowered.api.event.cause.entity.damage.DamageFunction;
import org.spongepowered.api.event.cause.entity.damage.DamageModifier;
import org.spongepowered.api.event.cause.entity.damage.DamageModifierType;
import org.spongepowered.api.event.entity.DamageEntityEvent;
import org.spongepowered.api.event.impl.AbstractEvent;

import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.function.DoubleUnaryOperator;

public class PlayerAttackEvent extends AbstractEvent {

    private Character attacker;
    private Character target;
    private Attack attack;

    public PlayerAttackEvent(Character attacker, Character target, Attack attack) {
        this.attacker = attacker;
        this.target = target;
    }

    @Override
    public Cause getCause() {
        return Cause.builder().insert(0, attacker).b;
    }

    @Override
    public Object getSource() {
        return null;
    }

    @Override
    public EventContext getContext() {
        return EventContext.builder().add(EventContextKeys<Chara>, attacker).build();
    }
}
