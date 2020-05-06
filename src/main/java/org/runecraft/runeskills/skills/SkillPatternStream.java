package org.runecraft.runeskills.skills;

import org.runecraft.runecharacters.Character;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Optional;

public class SkillPatternStream {
    private Character user;
    private int secondsRemaining;
    private String currentPattern;

    public SkillPatternStream(Character user){
        this.user = user;
    }

    public void add(String pattern){
        currentPattern+=pattern;
        secondsRemaining+=1;
        if(!pattern.startsWith("s")){
            if(pattern.length() == 3){
                Optional<Class<? extends Skill>> clazzSkill = SkillPatternManager.get(currentPattern, user.getCharacterClass());
                if(clazzSkill.isPresent()){
                    try{
                        Class<? extends Skill> skill = clazzSkill.get();
                        Constructor<? extends Skill> constructor = skill.getConstructor(Character.class);
                        Method useMethod = skill.getDeclaredMethod("use");
                        Object instance = constructor.newInstance(user);
                        useMethod.invoke(instance);
                    }catch(NoSuchMethodException | IllegalAccessException | InstantiationException | InvocationTargetException ex){
                        ex.printStackTrace();
                    }
                }
            }else{
                currentPattern+=pattern;
                secondsRemaining+=2;
            }
        }else{
            if(pattern.length() == 4){

            }else{
                currentPattern+=pattern;
                secondsRemaining+=1;
            }
        }
    }

    public void removeSecond(){
        secondsRemaining--;
    }

    public int getSecondsRemaining(){
        return secondsRemaining;
    }

    public Character getCharacter() {
        return user;
    }

    public String getCurrentPattern() {
        return currentPattern;
    }

}
