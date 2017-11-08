package me.hulipvp.chambers.event;

import org.bukkit.event.Event;
import org.bukkit.event.HandlerList;

public class ChambersEvent extends Event {
	
	private static final HandlerList HANDLERS = new HandlerList();

    public HandlerList getHandlers() {
        return HANDLERS;
    }

    public static HandlerList getHandlerList() {
        return HANDLERS;
    }

}