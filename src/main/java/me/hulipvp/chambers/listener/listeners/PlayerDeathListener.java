package me.hulipvp.chambers.listener.listeners;

import org.bukkit.Bukkit;
import org.bukkit.GameMode;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.PlayerDeathEvent;

import me.hulipvp.chambers.Chambers;
import me.hulipvp.chambers.profile.structure.Profile;
import me.hulipvp.chambers.task.RespawnTask;

public class PlayerDeathListener implements Listener {
	
	@EventHandler
	public void onDeath(PlayerDeathEvent event) {
		Profile profile = Chambers.getInstance().getProfileManager().getProfileByUuid(event.getEntity().getUniqueId());
		if (profile.getTeam() != null) {
			Player player = event.getEntity();
			Bukkit.getOnlinePlayers().stream().filter(other -> other != player).forEach(other -> other.hidePlayer(player));
			player.setGameMode(GameMode.CREATIVE);
	        player.setHealth(20.0);
	        player.setFoodLevel(20);
	        player.getActivePotionEffects().forEach(potionEffect -> player.removePotionEffect(potionEffect.getType()));
	        player.setAllowFlight(true);
	        player.setFlying(true);
	        player.setVelocity(player.getVelocity().setY(1.1));
	        player.getInventory().clear();
	        player.getInventory().setArmorContents(null);
			profile.setRespawnTime(30);
			new RespawnTask(profile, player).runTaskAsynchronously(Chambers.getInstance());
		}
	}

}
