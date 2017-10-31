package me.hulipvp.chambers.claim.listener;

import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerMoveEvent;

import me.hulipvp.chambers.Chambers;
import me.hulipvp.chambers.claim.structure.Claim;
import me.hulipvp.chambers.game.structure.Game;
import me.hulipvp.chambers.game.structure.GameStatus;
import me.hulipvp.chambers.team.structure.Team;

public class ClaimMoveListener implements Listener {
	
	@EventHandler
	public void onMove(PlayerMoveEvent event) {
		Game game = Chambers.getInstance().getGameManager().getGame();
		if (game.getStatus() != GameStatus.INGAME) {
			return;
		}
		Location to = event.getTo();
		Location from = event.getFrom();
		if (to.getBlockX() != from.getBlockX() || to.getBlockZ() != from.getBlockZ()) {
			Player player = event.getPlayer();
			Claim toClaim = Chambers.getInstance().getClaimManager().getClaimAt(to);
			Claim fromClaim = Chambers.getInstance().getClaimManager().getClaimAt(from);
			Team toTeam = toClaim == null ? Chambers.getInstance().getTeamManager().getTeamByName("Wilderness") : toClaim.getOwner();
			Team fromTeam = toClaim == null ? Chambers.getInstance().getTeamManager().getTeamByName("Wilderness") : fromClaim.getOwner();
			if (toTeam != fromTeam) {
				player.sendMessage(ChatColor.YELLOW + "Now leaving: " + fromTeam.getFormattedName());
				player.sendMessage(ChatColor.YELLOW + "Now entering: " + toTeam.getFormattedName());
			}
		}
	}

}
