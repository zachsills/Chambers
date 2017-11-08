package me.hulipvp.chambers.team.listener;

import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageByEntityEvent;

import me.hulipvp.chambers.Chambers;
import me.hulipvp.chambers.profile.structure.Profile;
import me.hulipvp.chambers.profile.structure.ProfileStatus;

public class TeamDamageListener implements Listener {
	
	@EventHandler
	public void onDamageByEntity(EntityDamageByEntityEvent event) {
		if (event.getEntity() instanceof Player && event.getDamager() instanceof Player) {
			Player player = (Player) event.getEntity();
			Player damager = (Player) event.getDamager();
			Profile playerProfile = Chambers.getInstance().getProfileManager().getProfileByUuid(player.getUniqueId());
			Profile damagerProfile = Chambers.getInstance().getProfileManager().getProfileByUuid(damager.getUniqueId());
			if (playerProfile.getTeam() == null || damagerProfile.getTeam() == null || playerProfile.getProfileStatus() != ProfileStatus.PLAYING || damagerProfile.getProfileStatus() != ProfileStatus.PLAYING) {
				event.setCancelled(true);
				damager.sendMessage(ChatColor.RED + "You cannot do this in your current state.");
			}
			if (playerProfile.getTeam() == damagerProfile.getTeam()) {
				event.setCancelled(true);
				damager.sendMessage(ChatColor.YELLOW + "You cannot hurt " + damagerProfile.getTeam().getColor() + player.getName() + ChatColor.YELLOW + ".");
			}
		}
	}

}
