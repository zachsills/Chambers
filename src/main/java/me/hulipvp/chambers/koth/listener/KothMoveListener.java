package me.hulipvp.chambers.koth.listener;

import org.bukkit.ChatColor;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;

import me.hulipvp.chambers.Chambers;
import me.hulipvp.chambers.event.movements.PlayerEnterClaimEvent;
import me.hulipvp.chambers.event.movements.PlayerLeaveClaimEvent;
import me.hulipvp.chambers.koth.structure.Koth;
import me.hulipvp.chambers.profile.structure.Profile;
import me.hulipvp.chambers.team.structure.Team;
import me.hulipvp.chambers.team.structure.TeamType;

public class KothMoveListener implements Listener {
	
	@EventHandler
	public void onPlayerEnterClaim(PlayerEnterClaimEvent event) {
		Team team = event.getClaim().getOwner();
		if (team.getType() == TeamType.KOTH_CAP) {
			Koth koth = Chambers.getInstance().getKothManager().getKoth();
			Profile profile = Chambers.getInstance().getProfileManager().getProfileByUuid(event.getPlayer().getUniqueId());
			koth.getCapQueue().add(profile);
		}
	}
	
	@EventHandler
	public void onPlayerLeaveClaim(PlayerLeaveClaimEvent event) {
		Team team = event.getClaim().getOwner();
		if (team.getType() == TeamType.KOTH_CAP) {
			Koth koth = Chambers.getInstance().getKothManager().getKoth();
			Profile profile = Chambers.getInstance().getProfileManager().getProfileByUuid(event.getPlayer().getUniqueId());
			if (koth.getCapper() != null && koth.getCapper() == profile) {
				koth.setCapper(null);
				if (koth.getTime() <= 180) {
					Chambers.getInstance().getKothManager().broadcastMessage("The control of " + ChatColor.BLUE + koth.getName() + ChatColor.YELLOW + "has been lost.");
				}
				profile.sendMessage(Chambers.getInstance().getKothManager().getKothFormat() + "You have lost control of the KOTH.");
			}
			if (koth.getCapQueue().contains(profile)) {
				koth.getCapQueue().remove(profile);
			}
		}
	}

}
