package me.hulipvp.chambers.team.listener;

import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.event.block.BlockPlaceEvent;

import me.hulipvp.chambers.Chambers;
import me.hulipvp.chambers.game.structure.Game;
import me.hulipvp.chambers.profile.structure.Profile;
import me.hulipvp.chambers.team.structure.Team;

public class TeamBlockListener implements Listener {
	
	@EventHandler
	public void onBreak(BlockBreakEvent event) {
		Game game = Chambers.getInstance().getGameManager().getGame();
		if (!game.hasStarted()) {
			return;
		}
		Location location = event.getBlock().getLocation();
		Profile profile = Chambers.getInstance().getProfileManager().getProfileByUuid(event.getPlayer().getUniqueId());
		if (profile.getTeam() == null) {
			event.setCancelled(true);
			return;
		}
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
	
	@EventHandler
	public void onPlace(BlockPlaceEvent event) {
		Game game = Chambers.getInstance().getGameManager().getGame();
		if (!game.hasStarted()) {
			return;
		}
		Location location = event.getBlock().getLocation();
		Profile profile = Chambers.getInstance().getProfileManager().getProfileByUuid(event.getPlayer().getUniqueId());
		if (profile.getTeam() == null) {
			event.setCancelled(true);
			return;
		}
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
