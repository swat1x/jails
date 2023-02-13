package ru.swat1x.pmjail.command;

import co.aikar.commands.BaseCommand;
import co.aikar.commands.annotation.CommandAlias;
import co.aikar.commands.annotation.CommandCompletion;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.bukkit.entity.Player;
import ru.swat1x.pmjail.management.JailManager;

@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
@RequiredArgsConstructor
public class JailCommands extends BaseCommand {

    JailManager manager;

    @CommandAlias("jail")
    @CommandCompletion("@players")
    public void onJailCommand(Player player, String target) {
        if (player.hasPermission("jail.jail")) {
            manager
        }
    }

    @CommandAlias("unjail")
    @CommandCompletion("@players")
    public void onUnjailCommand(Player player, String target) {
        if (player.hasPermission("jail.unjail")) {

        }
    }

}
