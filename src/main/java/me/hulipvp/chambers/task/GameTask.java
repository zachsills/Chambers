package me.hulipvp.chambers.task;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.scheduler.BukkitRunnable;

import me.hulipvp.chambers.Chambers;
import me.hulipvp.chambers.game.structure.Game;
import me.hulipvp.chambers.game.structure.GameStatus;
import me.hulipvp.chambers.koth.structure.Koth;

public class GameTask extends BukkitRunnable {

	@Override
	public void run() {
		Game game = Chambers.getInstance().getGameManager().getGame();
		game.setTotalTime(game.getTotalTime() + 1);
		if (game.getInvincibilityTime() > 0) {
			game.setInvincibilityTime(game.getInvincibilityTime() - 1);
		} else if (game.getInvincibilityTime() == 0 && game.getTotalTime() == 60) {
			Bukkit.broadcastMessage(ChatColor.RED + "Invincibility is now disabled and PvP has now been enabled!");
		}
		if (game.getTotalTime() == 30) {
			String kothName = game.getKothName();
			Koth koth = new Koth(kothName == null ? game.getMapName() : kothName, 480);
			Chambers.getInstance().getKothManager().setKoth(koth);
			new KothTask().runTaskTimerAsynchronously(Chambers.getInstance(), 0L, 20L);
			Chambers.getInstance().getKothManager().broadcastMessage(ChatColor.BLUE + koth.getName() + ChatColor.YELLOW + " can now be contested.");
		}
		if (game.getTotalTime() % 3 == 0 && game.getTotalTime() != 0) {
			Chambers.getInstance().getProfileManager().getAllPlayingProfiles().forEach(profile -> profile.setBalance(profile.getBalance() + 3));
		}
		if (game.getStatus() == GameStatus.OVER) {
			cancel();
		}
	}

}
