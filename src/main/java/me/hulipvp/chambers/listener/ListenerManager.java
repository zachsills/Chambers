package me.hulipvp.chambers.listener;

import java.util.stream.Stream;

import org.bukkit.Bukkit;
import org.bukkit.plugin.PluginManager;

import me.hulipvp.chambers.Chambers;
import me.hulipvp.chambers.claim.listener.ClaimInteractListener;
import me.hulipvp.chambers.claim.listener.ClaimMoveListener;
import me.hulipvp.chambers.claim.listener.ClaimProfileListener;
import me.hulipvp.chambers.game.listener.GameDamageListener;
import me.hulipvp.chambers.game.listener.GameInteractListener;
import me.hulipvp.chambers.game.listener.GameProtectionListener;
import me.hulipvp.chambers.koth.listener.KothMoveListener;
import me.hulipvp.chambers.listener.listeners.ChatListener;
import me.hulipvp.chambers.listener.listeners.EnderpearlListener;
import me.hulipvp.chambers.listener.listeners.PlayerDeathListener;
import me.hulipvp.chambers.listener.listeners.PlayerRespawnListener;
import me.hulipvp.chambers.listener.listeners.ProfileListener;
import me.hulipvp.chambers.listener.listeners.ProfileStatusListener;
import me.hulipvp.chambers.team.listener.TeamBlockListener;
import me.hulipvp.chambers.team.listener.TeamDamageListener;
import me.hulipvp.chambers.team.listener.TeamDeathListener;
import me.hulipvp.chambers.team.listener.TeamInteractListener;

public class ListenerManager {

	public ListenerManager() {

		registerListeners();

	}

	public void registerListeners() {
		PluginManager pluginManager = Bukkit.getServer().getPluginManager();
		Stream.of(
				new ClaimInteractListener(),
				new ClaimMoveListener(),
				new ClaimProfileListener(),
				new GameDamageListener(),
				new GameInteractListener(),
				new GameProtectionListener(),
				new KothMoveListener(),
				new TeamBlockListener(),
				new TeamDamageListener(),
				new TeamDeathListener(),
				new TeamInteractListener(),
				new ChatListener(),
				new PlayerDeathListener(),
				new PlayerRespawnListener(),
				new ProfileListener(),
				new ProfileStatusListener(),
				new EnderpearlListener()
		).forEach(listener -> pluginManager.registerEvents(listener, Chambers.getInstance()));
	}

}
