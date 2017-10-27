package me.hulipvp.chambers.game.listener;

import org.bukkit.GameMode;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.event.block.BlockPlaceEvent;

import me.hulipvp.chambers.Chambers;
import me.hulipvp.chambers.game.structure.Game;

public class GameProtectionListener implements Listener {
	
	@EventHandler
	public void onBreak(BlockBreakEvent event) {
		if (event.isCancelled()) {
			return;
		}
		Game game = Chambers.getInstance().getGameManager().getGame();
		if (!game.hasStarted()) {
			if (!event.getPlayer().isOp() && event.getPlayer().getGameMode() != GameMode.CREATIVE) {
				event.setCancelled(true);
			}
		}
	}
	
	@EventHandler
	public void onBreak(BlockPlaceEvent event) {
		if (event.isCancelled()) {
			return;
		}
		Game game = Chambers.getInstance().getGameManager().getGame();
		if (!game.hasStarted()) {
			if (!event.getPlayer().isOp() && event.getPlayer().getGameMode() != GameMode.CREATIVE) {
				event.setCancelled(true);
			}
		}
	}

}
