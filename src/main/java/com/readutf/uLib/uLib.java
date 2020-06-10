package com.readutf.uLib;

import com.readutf.uLib.libraries.clickables.ClickableManager;
import lombok.Getter;
import org.bukkit.Bukkit;
import org.bukkit.command.CommandMap;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.Arrays;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

public class uLib {

    CommandMap commandMap;
    @Getter private static JavaPlugin plugin;
    Logger logger = Bukkit.getLogger();

    public uLib(JavaPlugin plugin) {
        this.plugin = plugin;
        new ClickableManager(plugin);
        logger.warning("[uLib] " + plugin.getName() + " successfully hooked.");
    }

    private static List<Class> enabledVerbose  = Arrays.asList(ClickableManager.class);

    public static void verbose(Class clazz, String message) {

        if(enabledVerbose.contains(clazz)) {
            Bukkit.getLogger().log(Level.FINE, "[" + clazz.getName()+ "] " + message);
        }


    }


}
