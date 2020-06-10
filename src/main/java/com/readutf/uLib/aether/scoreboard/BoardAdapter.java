package com.readutf.uLib.aether.scoreboard;

import com.readutf.uLib.aether.scoreboard.cooldown.BoardCooldown;
import org.bukkit.entity.*;
import java.util.*;

public interface BoardAdapter
{
    String getTitle(final Player p0);
    
    List<String> getScoreboard(final Player p0, final Board p1, final Set<BoardCooldown> p2);
}
