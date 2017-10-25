package me.hulipvp.chambers.task;

import org.bukkit.scheduler.BukkitRunnable;

import me.hulipvp.chambers.Chambers;
import me.hulipvp.chambers.game.structure.Game;

public class GameTask extends BukkitRunnable {

	@Override
	public void run() {
		Game game = Chambers.getInstance().getGameManager().getGame();
		game.setTotalTime(game.getTotalTime() + 1);
		// TODO: ADD CHECKS TO AUTO-START KOTH
	}

}
