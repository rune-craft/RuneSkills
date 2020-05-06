package org.runecraft.runeskills.commands;

import com.flowpowered.math.vector.Vector3d;
import org.runecraft.runecharacters.Character;
import org.runecraft.runeskills.Attack;
import org.spongepowered.api.command.CommandException;
import org.spongepowered.api.command.CommandResult;
import org.spongepowered.api.command.CommandSource;
import org.spongepowered.api.command.args.CommandContext;
import org.spongepowered.api.command.spec.CommandExecutor;
import org.spongepowered.api.entity.Entity;
import org.spongepowered.api.entity.living.player.Player;
import org.spongepowered.api.util.blockray.BlockRay;
import org.spongepowered.api.util.blockray.BlockRayHit;
import org.spongepowered.api.world.World;

import java.util.Collection;

public class Teste implements CommandExecutor {

    @Override
    public CommandResult execute(CommandSource src, CommandContext args) throws CommandException {

        BlockRay.BlockRayBuilder ray = BlockRay.from((Player) src);
        ray.stopFilter(BlockRay.onlyAirFilter());
        ray.distanceLimit(40).build();
        BlockRayHit<World> hit = (BlockRayHit<World>) ray.end().get();
        System.out.println(hit.getX());
        System.out.println(hit.getY());
        System.out.println(hit.getZ());

        Collection<Entity> entities = hit.getExtent().getNearbyEntities(new Vector3d(hit.getX(),hit.getY(),hit.getZ()), 1D);

        for(Entity ent : entities){
            if(ent instanceof Player){
                Player player = (Player) ent;
                Attack attack = new Attack(10, 16, null, null);
            }
        }
        return CommandResult.builder().build();
    }

}
