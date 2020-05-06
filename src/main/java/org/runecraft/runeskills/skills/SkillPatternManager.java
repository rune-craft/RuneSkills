package org.runecraft.runeskills.skills;

import org.runecraft.runecharacters.enums.CharacterClass;
import org.spongepowered.api.scheduler.Task;

import java.lang.reflect.Field;
import java.util.*;
import java.util.concurrent.TimeUnit;

public class SkillPatternManager {

    private static Map<String, Class<? extends Skill>> skillPatternRegistry = new HashMap<>();
    private static Set<SkillPatternStream> currentPatternStreams = new HashSet<>();

    public static void registerSkillPattern(String pattern, Class<? extends Skill> clazz){
        skillPatternRegistry.put(pattern, clazz);
    }

    public static Optional<Class<? extends Skill>> get(String pattern, CharacterClass clazz) {
        for(Map.Entry<String, Class<? extends Skill>> entry : skillPatternRegistry.entrySet()){
            if(entry.getKey().equals(pattern)){
                try{
                    Field field = entry.getValue().getDeclaredField("clazz");
                    field.setAccessible(true);
                    if(field.get(null) == clazz){
                        return Optional.ofNullable(entry.getValue());
                    }
                }catch(IllegalAccessException | NoSuchFieldException ex){
                    ex.printStackTrace();
                }

            }
        }
        return Optional.empty();
    }

    public static void add(SkillPatternStream stream){
        currentPatternStreams.add(stream);
    }

    public static void startTask(){
        Task.Builder task = Task.builder().async().delay(1, TimeUnit.SECONDS);
        task.execute(() -> {
            currentPatternStreams.forEach(SkillPatternStream::removeSecond);
            currentPatternStreams.removeIf(stream -> stream.getSecondsRemaining() == 0);
        });
    }

}
