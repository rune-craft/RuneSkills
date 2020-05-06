package org.runecraft.runeskills;

import com.google.inject.Inject;
import org.runecraft.runeskills.commands.Teste;
import org.slf4j.Logger;
import org.spongepowered.api.Sponge;
import org.spongepowered.api.command.spec.CommandSpec;
import org.spongepowered.api.event.game.state.GameStartedServerEvent;
import org.spongepowered.api.event.Listener;
import org.spongepowered.api.plugin.Plugin;

@Plugin(
        id = "runeskills",
        name = "RuneSkills",
        authors = {
                "Azure"
        }
)
public class RuneSkills {

    @Inject
    private Logger logger;

    @Listener
    public void onServerStart(GameStartedServerEvent event) {
        CommandSpec spec = CommandSpec.builder().executor(new Teste()).build();

        Sponge.getCommandManager().register(this, spec, "teste");
    }
}
