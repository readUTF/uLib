package com.readutf.uLib.libraries.menu.button;

import com.readutf.uLib.libraries.menu.ItemClick;
import org.bukkit.inventory.ItemStack;

public abstract class Button {

    public abstract ItemStack getItem();
    public abstract ItemClick itemClick();

}
