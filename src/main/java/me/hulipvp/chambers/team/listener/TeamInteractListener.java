package me.hulipvp.chambers.team.listener;

import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;

import me.hulipvp.chambers.Chambers;
import me.hulipvp.chambers.game.structure.Game;
import me.hulipvp.chambers.profile.structure.Profile;
import me.hulipvp.chambers.team.structure.Team;

public class TeamInteractListener implements Listener {
	
	@EventHandler
	public void onInteract(PlayerInteractEvent event) {
		Game game = Chambers.getInstance().getGameManager().getGame();
		if (!game.hasStarted()) {
			return;
		}
		if (event.getAction() != Action.RIGHT_CLICK_BLOCK) {
			return;
		}
		Material material = event.getClickedBlock().getType();
		Location location = event.getClickedBlock().getLocation();
		Profile profile = Chambers.getInstance().getProfileManager().getProfileByUuid(event.getPlayer().getUniqueId());
		if (profile.isBypassMode()) {
			event.setCancelled(false);
			return;
		}
		if (profile.getTeam() == null) {
			event.setCancelled(true);
			return;
		}
		if (material.name().contains("FENCE") || material.name().contains("DOOR") || material.name().contains("CHEST") || material.name().contains("PLATE")) {
			Team team = Chambers.getInstance().getClaimManager().getTeamAt(location);
			if (team.isRaidable()) {
				event.setCancelled(false);
				return;
			}
			if (team != profile.getTeam()) {
				event.setCancelled(true);
				profile.sendMessage(ChatColor.YELLOW + "You cannot do that in the territory of " + team.getFormattedName() + ChatColor.YELLOW + ".");
			}
		}
	}

}
