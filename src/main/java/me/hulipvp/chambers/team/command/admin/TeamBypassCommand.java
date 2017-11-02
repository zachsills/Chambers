package me.hulipvp.chambers.team.command.admin;

import org.bukkit.ChatColor;

import me.hulipvp.chambers.profile.structure.Profile;
import me.hulipvp.chambers.team.command.TeamCommand;
import me.hulipvp.chambers.util.commandapi.Command;
import me.hulipvp.chambers.util.commandapi.CommandArgs;

public class TeamBypassCommand extends TeamCommand {
	
	@Command(name = "faction.bypass", description = "Turn on bypass mode to build in other territories", aliases = { "f.bypass", "t.bypass", "s.bypass", "team.bypass", "squad.bypass" }, usage = "/<command>", playerOnly = true, requiresTeam = false, adminsOnly = true)
	public void onCommand(CommandArgs commandArgs) {
		Profile profile = plugin.getProfileManager().getProfileByUuid(commandArgs.getPlayer().getUniqueId());
		profile.setBypassMode(profile.isBypassMode() ? false : true);
		profile.sendMessage(ChatColor.YELLOW + "Bypass mode has been " + (profile.isBypassMode() ? ChatColor.RED + "disabled" : ChatColor.GREEN + "enabled") + ChatColor.YELLOW + ".");
	}

}
