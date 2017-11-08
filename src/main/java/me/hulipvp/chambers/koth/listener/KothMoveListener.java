package me.hulipvp.chambers.koth.listener;

import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;

import me.hulipvp.chambers.event.movements.PlayerEnterClaimEvent;
import me.hulipvp.chambers.event.movements.PlayerLeaveClaimEvent;
import me.hulipvp.chambers.team.structure.Team;
import me.hulipvp.chambers.team.structure.TeamType;

public class KothMoveListener implements Listener {
	
	@EventHandler
	public void onPlayerEnterClaim(PlayerEnterClaimEvent event) {
		Team team = event.getClaim().getOwner();
		if (team.getType() == TeamType.KOTH_CAP) {
			// TODO: ADD TO KOTH QUEUE
		}
	}
	
	@EventHandler
	public void onPlayerLeaveClaim(PlayerLeaveClaimEvent event) {
		Team team = event.getClaim().getOwner();
		if (team.getType() == TeamType.KOTH_CAP) {
			// TODO: REMOVE FROM KOTH QUEUE
		}
	}

}
