package me.hulipvp.chambers.team.command.admin;

import org.bukkit.ChatColor;

import me.hulipvp.chambers.profile.structure.Profile;
import me.hulipvp.chambers.team.command.TeamCommand;
import me.hulipvp.chambers.util.commandapi.Command;
import me.hulipvp.chambers.util.commandapi.CommandArgs;

public class TeamClaimCommand extends TeamCommand {
	
	@Command(name = "faction.claim", description = "Claim for a Team", aliases = { "f.claim", "t.claim", "s.claim", "team.claim", "squad.claim" }, usage = "/<command> <args>", playerOnly = true, requiresTeam = false, adminsOnly = true)
	public void onCommand(CommandArgs commandArgs) {
		Profile profile = plugin.getProfileManager().getProfileByUuid(commandArgs.getPlayer().getUniqueId());
		if (profile.getTeam().getType().getClaimMaterial() == null) {
			profile.sendMessage(ChatColor.RED + "You cannot claim for the Wilderness.");
			return;
		}
		
	}

}
