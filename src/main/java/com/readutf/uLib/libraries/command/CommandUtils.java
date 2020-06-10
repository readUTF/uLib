package com.readutf.uLib.libraries.command;

import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class CommandUtils {

    public static boolean checkPerm(CommandSender sender, String perm) {
        if(sender.hasPermission(perm)) {
            return true;
        }
        return false;
    }

    public static boolean playerOnly(CommandSender sender) {
        if(!(sender instanceof  Player)) {
            return false;
        }
        return true;
    }

}
