package me.hulipvp.chambers.scoreboard;

import org.bukkit.Bukkit;
import org.bukkit.plugin.java.JavaPlugin;

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
