package com.readutf.uLib;

import com.readutf.uLib.libraries.clickables.ClickableManager;
import com.readutf.uLib.menu.MenuListener;
import lombok.Getter;
import org.bukkit.Bukkit;
import org.bukkit.command.CommandMap;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.Arrays;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

public class ULib {

    CommandMap commandMap;
    @Getter public static JavaPlugin plugin;
    Logger logger = Bukkit.getLogger();

    public ULib(JavaPlugin plugin) {
        ULib.plugin = plugin;

        Bukkit.getPluginManager().registerEvents(new MenuListener(), plugin);

    }

    private static List<Class> enabledVerbose  = Arrays.asList(ClickableManager.class);

    public static void verbose(Class clazz, String message) {

        if(enabledVerbose.contains(clazz)) {
            Bukkit.getLogger().log(Level.FINE, "[" + clazz.getName()+ "] " + message);
        }


    }


}
