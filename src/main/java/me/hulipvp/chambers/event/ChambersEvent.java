package me.hulipvp.chambers.event;

import org.bukkit.event.Event;
import org.bukkit.event.HandlerList;

/**
 * The main Event class to extend upon when creating a custom Event 
 * called by Chambers
 */
public class ChambersEvent extends Event {
	
	private static final HandlerList HANDLERS = new HandlerList();

    public HandlerList getHandlers() {
        return HANDLERS;
    }

    public static HandlerList getHandlerList() {
        return HANDLERS;
    }

}