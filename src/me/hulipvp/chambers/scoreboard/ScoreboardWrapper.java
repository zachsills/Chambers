package me.hulipvp.chambers.scoreboard;

import org.bukkit.Bukkit;
import org.bukkit.plugin.java.JavaPlugin;

/**
 * ScoreboardWrapper is happily provided by Deathstreams
 * View here: https://github.com/Deathstreams/ScoreboardWrapper
 * 
 * @author Deathstreams
 */
public class ScoreboardWrapper {

	static ScoreboardWrapper instance;

	public ScoreboardProvider provider;

	public JavaPlugin plugin;

	public ScoreboardManager scoreboardManager;

	public ScoreboardWrapper(JavaPlugin plugin, ScoreboardProvider provider) {
		ScoreboardWrapper.instance = this;
		this.provider = provider;
		this.plugin = plugin;
		this.scoreboardManager = new ScoreboardManager();
		Bukkit.getPluginManager().registerEvents(scoreboardManager, plugin);
	}

}
