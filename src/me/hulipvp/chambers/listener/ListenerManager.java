package me.hulipvp.chambers.listener;

import java.util.stream.Stream;

import org.bukkit.Bukkit;
import org.bukkit.plugin.PluginManager;

import me.hulipvp.chambers.Chambers;
import me.hulipvp.chambers.listener.listeners.EnderpearlListener;
import me.hulipvp.chambers.listener.listeners.ProfileListener;

public class ListenerManager {

	public ListenerManager() {

		registerListeners();

	}

	public void registerListeners() {
		PluginManager pluginManager = Bukkit.getServer().getPluginManager();
		Stream.of(
				new ProfileListener(), 
				new EnderpearlListener()
				).forEach(listener -> pluginManager.registerEvents(listener, Chambers.getInstance()));
	}

}
