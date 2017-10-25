package me.hulipvp.chambers.listener;

import me.hulipvp.chambers.Chambers;
import me.hulipvp.chambers.listener.listeners.EnderpearlListener;
import me.hulipvp.chambers.listener.listeners.ProfileListener;

import java.util.stream.Stream;

public class ListenerManager {

	private final Chambers plugin;

	public ListenerManager(Chambers plugin) {
		this.plugin = plugin;
	}

	public void registerListeners() {
		Stream.of(
				new EnderpearlListener(),
				new ProfileListener()
		).forEach(listener -> plugin.getServer().getPluginManager().registerEvents(listener, plugin));
	}
}
