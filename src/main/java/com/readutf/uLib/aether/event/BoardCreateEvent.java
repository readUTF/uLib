package com.readutf.uLib.aether.event;

import com.readutf.uLib.aether.scoreboard.Board;
import org.bukkit.event.*;
import org.bukkit.entity.*;

public class BoardCreateEvent extends Event
{
    private static final HandlerList handlers;
    private final Board board;
    private final Player player;
    
    public BoardCreateEvent(final Board board, final Player player) {
        this.board = board;
        this.player = player;
    }
    
    public HandlerList getHandlers() {
        return BoardCreateEvent.handlers;
    }
    
    public static HandlerList getHandlerList() {
        return BoardCreateEvent.handlers;
    }
    
    public Board getBoard() {
        return this.board;
    }
    
    public Player getPlayer() {
        return this.player;
    }
    
    static {
        handlers = new HandlerList();
    }
}
