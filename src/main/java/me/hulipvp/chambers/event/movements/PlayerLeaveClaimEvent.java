package me.hulipvp.chambers.event.movements;

import org.bukkit.entity.Player;

import lombok.AllArgsConstructor;
import lombok.Getter;
import me.hulipvp.chambers.claim.structure.Claim;
import me.hulipvp.chambers.event.ChambersEvent;

@AllArgsConstructor
@Getter
public class PlayerLeaveClaimEvent extends ChambersEvent {
	
	private Player player;
	private Claim claim;

}
