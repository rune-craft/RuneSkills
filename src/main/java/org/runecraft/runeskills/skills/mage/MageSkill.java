package org.runecraft.runeskills.skills.mage;

import org.runecraft.runecharacters.Character;
import org.runecraft.runecharacters.enums.CharacterClass;
import org.runecraft.runeskills.skills.Skill;

public abstract class MageSkill extends Skill {
    protected int usedMana;
    public static final CharacterClass clazz = CharacterClass.MAGE;

    public MageSkill(Character user, int manaUsed){
        super(user);
        this.usedMana = manaUsed;
    }
}
