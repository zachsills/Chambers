package me.hulipvp.chambers.claim.listener;

import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerMoveEvent;

import me.hulipvp.chambers.Chambers;
import me.hulipvp.chambers.game.structure.Game;
import me.hulipvp.chambers.team.structure.Team;

public class ClaimMoveListener implements Listener {
	
	@EventHandler
	public void onMove(PlayerMoveEvent event) {
		Game game = Chambers.getInstance().getGameManager().getGame();
		if (!game.hasStarted()) {
			return;
		}
		Location to = event.getTo();
		Location from = event.getFrom();
		if (to.getBlockX() != from.getBlockX() || to.getBlockZ() != from.getBlockZ()) {
			Player player = event.getPlayer();
			Team toTeam = Chambers.getInstance().getClaimManager().getClaimAt(to).getOwner();
			Team fromTeam = Chambers.getInstance().getClaimManager().getClaimAt(from).getOwner();
			if (toTeam != fromTeam) {
				player.sendMessage(ChatColor.YELLOW + "Now leaving: " + fromTeam.getFormattedName());
				player.sendMessage(ChatColor.YELLOW + "Now entering: " + toTeam.getFormattedName());
			}
		}
	}

}
