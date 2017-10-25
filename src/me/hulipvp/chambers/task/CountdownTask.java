package me.hulipvp.chambers.task;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.scheduler.BukkitRunnable;

import me.hulipvp.chambers.Chambers;
import me.hulipvp.chambers.game.structure.Game;

public class CountdownTask extends BukkitRunnable {

	@Override
	public void run() {
		Game game = Chambers.getInstance().getGameManager().getGame();
		game.setCountdownTime(game.getCountdownTime() - 1);
		if (game.getCountdownTime() == 0) {
			Chambers.getInstance().getGameManager().finallyStart();
			cancel();
		} else if (game.getCountdownTime() % 5 == 0 || game.getCountdownTime() <= 5) {
			Bukkit.broadcastMessage(ChatColor.YELLOW + "Game starting in " + ChatColor.GREEN +  game.getCountdownTime() + ChatColor.YELLOW + (game.getCountdownTime() == 1 ? "second" : "seconds") + ".");
		}
	}

}
