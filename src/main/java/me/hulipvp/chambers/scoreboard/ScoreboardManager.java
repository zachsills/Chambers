package me.hulipvp.chambers.scoreboard;

import java.util.Map;
import java.util.UUID;
import java.util.concurrent.ConcurrentHashMap;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.HandlerList;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerQuitEvent;
import org.bukkit.scheduler.BukkitRunnable;
import org.bukkit.scheduler.BukkitTask;

import me.hulipvp.chambers.Chambers;

public class ScoreboardManager implements Listener {

	private static Map<UUID, PlayerScoreboard> scoreboards = new ConcurrentHashMap<>();

	private BukkitTask updateTask;

	@SuppressWarnings("deprecation")
	public ScoreboardManager() {
		for (Player player : Bukkit.getOnlinePlayers()) {
			scoreboards.putIfAbsent(player.getUniqueId(), new PlayerScoreboard(player));
		}
		this.updateTask = Bukkit.getScheduler().runTaskTimerAsynchronously(Chambers.getInstance(),
				new ScoreboardUpdateTask(), 2l, 2l);
	}

	@EventHandler
	public void onJoin(PlayerJoinEvent event) {
		Player player = event.getPlayer();
		new BukkitRunnable() {
			public void run() {
				scoreboards.putIfAbsent(player.getUniqueId(), new PlayerScoreboard(player));
			}
		}.runTaskLater(Chambers.getInstance(), 4l);
	}

	@EventHandler
	public void onQuit(PlayerQuitEvent event) {
		Player player = event.getPlayer();
		PlayerScoreboard board = null;
		if ((board = scoreboards.remove(player.getUniqueId())) != null) {
			board.disable();
		}
	}

	void unregister() {
		scoreboards.forEach((uuid, scoreboard) -> {
			scoreboard.disable();
		});
		HandlerList.unregisterAll(this);
		updateTask.cancel();
	}

	static class ScoreboardUpdateTask implements Runnable {
		@Override
		public void run() {
			scoreboards.forEach((uuid, scoreboard) -> {
				scoreboard.update();
			});
		}

	}

}