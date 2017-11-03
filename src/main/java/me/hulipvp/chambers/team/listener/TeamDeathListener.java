package me.hulipvp.chambers.team.listener;

import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.PlayerDeathEvent;

import me.hulipvp.chambers.Chambers;
import me.hulipvp.chambers.profile.structure.Profile;
import net.md_5.bungee.api.ChatColor;

public class TeamDeathListener implements Listener {
	
	@EventHandler
	public void onDeath(PlayerDeathEvent event) {
		Profile profile = Chambers.getInstance().getProfileManager().getProfileByUuid(event.getEntity().getUniqueId());
		if (profile.getTeam() != null) {
			profile.getTeam().setDtr(profile.getTeam().getDtr() - 1);
			profile.getTeam().sendMessage(ChatColor.RED + "Member Death: " + ChatColor.RESET + profile.getPlayer().getName());
			profile.getTeam().sendMessage(ChatColor.RED + "DTR: " + ChatColor.RESET + profile.getTeam().getDtr());
		}
	}

}
