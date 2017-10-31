package me.hulipvp.chambers.team.listener;

import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.event.block.BlockPlaceEvent;

import me.hulipvp.chambers.Chambers;
import me.hulipvp.chambers.claim.structure.Claim;
import me.hulipvp.chambers.profile.structure.Profile;
import me.hulipvp.chambers.team.structure.Team;

public class TeamBlockListener implements Listener {
	
	@EventHandler
	public void onBreak(BlockBreakEvent event) {
		if (event.isCancelled()) {
			return;
		}
		Location location = event.getBlock().getLocation();
		Profile profile = Chambers.getInstance().getProfileManager().getProfileByUuid(event.getPlayer().getUniqueId());
		if (profile.getTeam() == null) {
			event.setCancelled(true);
			return;
		}
		Claim claim = Chambers.getInstance().getClaimManager().getClaimAt(location);
		Team team = claim == null ? Chambers.getInstance().getTeamManager().getTeamByName("Wilderness") : claim.getOwner();
		if (team.isRaidable()) {
			event.setCancelled(false);
			return;
		}
		if (team != profile.getTeam()) {
			event.setCancelled(true);
			profile.sendMessage(ChatColor.YELLOW + "You cannot do that in territory of " + team.getFormattedName() + ChatColor.YELLOW + ".");
		}
 	}
	
	@EventHandler
	public void onPlace(BlockPlaceEvent event) {
		if (event.isCancelled()) {
			return;
		}
		Location location = event.getBlock().getLocation();
		Profile profile = Chambers.getInstance().getProfileManager().getProfileByUuid(event.getPlayer().getUniqueId());
		if (profile.getTeam() == null) {
			event.setCancelled(true);
			return;
		}
		Claim claim = Chambers.getInstance().getClaimManager().getClaimAt(location);
		Team team = claim == null ? Chambers.getInstance().getTeamManager().getTeamByName("Wilderness") : claim.getOwner();
		if (team.isRaidable()) {
			event.setCancelled(false);
			return;
		}
		if (team != profile.getTeam()) {
			event.setCancelled(true);
			profile.sendMessage(ChatColor.YELLOW + "You cannot do that in territory of " + team.getFormattedName() + ChatColor.YELLOW + ".");
		}
 	}

}
