package me.hulipvp.chambers.game.listener;

import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.event.block.BlockPlaceEvent;
import org.bukkit.event.entity.FoodLevelChangeEvent;
import org.bukkit.event.player.PlayerDropItemEvent;
import org.bukkit.event.player.PlayerPickupItemEvent;

import me.hulipvp.chambers.Chambers;
import me.hulipvp.chambers.game.structure.Game;

public class GameProtectionListener implements Listener {

	@EventHandler
	public void onBreak(BlockBreakEvent event) {
		Game game = Chambers.getInstance().getGameManager().getGame();
		if (!game.hasStarted()) {
			if (!event.getPlayer().isOp()) {
				event.setCancelled(true);
			}
		}
	}

	@EventHandler
	public void onPlace(BlockPlaceEvent event) {
		Game game = Chambers.getInstance().getGameManager().getGame();
		if (!game.hasStarted()) {
			if (!event.getPlayer().isOp()) {
				event.setCancelled(true);
			}
		}
	}

	@EventHandler
	public void onPickup(PlayerPickupItemEvent event) {
		Game game = Chambers.getInstance().getGameManager().getGame();
		if (!game.hasStarted()) {
			if (!event.getPlayer().isOp()) {
				event.setCancelled(true);
			}
		}
	}

	@EventHandler
	public void onDrop(PlayerDropItemEvent event) {
		Game game = Chambers.getInstance().getGameManager().getGame();
		if (!game.hasStarted()) {
			if (!event.getPlayer().isOp()) {
				event.setCancelled(true);
			}
		}
	}

	@EventHandler
	public void onHunger(FoodLevelChangeEvent event) {
		Game game = Chambers.getInstance().getGameManager().getGame();
		if (!game.hasStarted()) {
			event.setCancelled(true);
		}
	}

}
