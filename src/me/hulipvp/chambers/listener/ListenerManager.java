package me.hulipvp.chambers.listener;

import java.util.HashSet;
import java.util.Set;

import org.bukkit.Bukkit;
import org.bukkit.event.Listener;
import org.bukkit.plugin.PluginManager;

import me.hulipvp.chambers.Chambers;
import me.hulipvp.chambers.listener.listeners.EnderpearlListener;
import me.hulipvp.chambers.listener.listeners.ProfileListener;

public class ListenerManager {
	
	private Set<Listener> listeners;
	
	public ListenerManager() {
		
		listeners = new HashSet<>();
		
		addListeners();
		
	}
	
	public void addListeners() {
		listeners.add(new ProfileListener());
		listeners.add(new EnderpearlListener());
	}
	
	public void registerListeners() {
		PluginManager pluginManager = Bukkit.getServer().getPluginManager();
		listeners.stream().forEach(listener -> pluginManager.registerEvents(listener, Chambers.getInstance()));
	}

}
