package me.hulipvp.chambers.game.listener;

import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.event.entity.EntityDamageEvent;

import me.hulipvp.chambers.Chambers;
import me.hulipvp.chambers.game.structure.Game;

public class GameDamageListener implements Listener {
	
	@EventHandler
	public void onDamage(EntityDamageEvent event) {
		if (event.getEntity() instanceof Player) {
			Game game = Chambers.getInstance().getGameManager().getGame();
			if (game.getInvincibilityTime() > 0) {
				event.setCancelled(true);
			}
		}
	}
	
	@EventHandler
	public void onDamageByEntity(EntityDamageByEntityEvent event) {
		if (event.isCancelled()) {
			return;
		}
		if (event.getEntity() instanceof Player && event.getDamager() instanceof Player) {
			Game game = Chambers.getInstance().getGameManager().getGame();
			if (game.getInvincibilityTime() > 0) {
				Player damager = (Player) event.getDamager();
				damager.sendMessage(ChatColor.RED + "The invincibility timer is still on.");
				event.setCancelled(true);
			}
		}
	}

}
