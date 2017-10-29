package me.hulipvp.chambers.listener;

import java.util.stream.Stream;

import org.bukkit.Bukkit;
import org.bukkit.plugin.PluginManager;

import me.hulipvp.chambers.Chambers;
import me.hulipvp.chambers.claim.listener.ClaimMoveListener;
import me.hulipvp.chambers.claim.listener.ClaimProfileListener;
import me.hulipvp.chambers.game.listener.GameDamageListener;
import me.hulipvp.chambers.game.listener.GameProtectionListener;
import me.hulipvp.chambers.listener.listeners.ChatListener;
import me.hulipvp.chambers.listener.listeners.EnderpearlListener;
import me.hulipvp.chambers.listener.listeners.ProfileListener;
import me.hulipvp.chambers.team.listener.TeamBlockListener;
import me.hulipvp.chambers.team.listener.TeamDamageListener;
import me.hulipvp.chambers.team.listener.TeamInteractListener;

public class ListenerManager {

	public ListenerManager() {

		registerListeners();

	}

	public void registerListeners() {
		PluginManager pluginManager = Bukkit.getServer().getPluginManager();
		Stream.of(
				new ClaimMoveListener(),
				new ClaimProfileListener(),
				new GameDamageListener(),
				new GameProtectionListener(),
				new TeamBlockListener(),
				new TeamDamageListener(),
				new TeamInteractListener(),
				new ChatListener(),
				new ProfileListener(), 
				new EnderpearlListener()
		).forEach(listener -> pluginManager.registerEvents(listener, Chambers.getInstance()));
	}

}
