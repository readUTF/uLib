package com.readutf.uLib.libraries.command;

import lombok.Getter;
import lombok.Setter;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import java.util.List;

public abstract class SubCommand {

    @Getter @Setter private String name;

    @Getter
    @Setter
    private List<String> aliases;

    @Getter @Setter
    private String description;
    @Getter @Setter int requiredLength;
    @Getter @Setter boolean playerOnly;
    @Getter @Setter String permission;
    @Getter @Setter String usage;

    public SubCommand(String name, List<String> aliases, String description, int requiredLength, String usage, boolean playerOnly, String permission) {
        this.name = name;
        this.aliases = aliases;
        this.description = description;
        this.requiredLength = requiredLength;
        this.usage = usage;
        this.playerOnly = playerOnly;
        this.permission = permission;
    }

    public abstract void execute(CommandSender sender, String command, String[] aliases);

}
