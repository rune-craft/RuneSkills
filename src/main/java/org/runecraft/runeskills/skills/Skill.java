package org.runecraft.runeskills.skills;

import com.flowpowered.math.vector.Vector3d;
import org.runecraft.runecore.User;
import org.runecraft.runecharacters.Character;
import org.runecraft.runecharacters.enums.CharacterClass;
import org.spongepowered.api.util.blockray.BlockRay;
import org.spongepowered.api.world.Location;
import org.spongepowered.api.world.World;

public abstract class Skill {
    private int minimumLevel;
    protected Character user;

    public Skill(Character user){
        this.user = user;
    }

    protected abstract void use();

    protected Location<World> getTarget(){
        BlockRay.BlockRayBuilder<World> ray = BlockRay.from(user.getOwner().getPlayer().get());
        ray.direction(new Vector3d(0, 20, 0)).distanceLimit(30).build();
        return ray.end().get().getLocation();
    }
}