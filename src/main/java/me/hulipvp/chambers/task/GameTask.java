package me.hulipvp.chambers.task;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.scheduler.BukkitRunnable;

import me.hulipvp.chambers.Chambers;
import me.hulipvp.chambers.game.structure.Game;

public class GameTask extends BukkitRunnable {

	// TODO: ADD CHECKS TO AUTO-START KOTH
	@Override
	public void run() {
		Game game = Chambers.getInstance().getGameManager().getGame();
		game.setTotalTime(game.getTotalTime() + 1);
		if (game.getInvincibilityTime() > 0) {
			game.setInvincibilityTime(game.getInvincibilityTime() - 1);
		} else if (game.getInvincibilityTime() == 0) {
			Bukkit.broadcastMessage(ChatColor.RED + "Invincibility is now disabled and PvP has now been enabled!");
		}
	}

}
