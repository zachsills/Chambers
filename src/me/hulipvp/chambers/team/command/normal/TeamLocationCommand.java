package me.hulipvp.chambers.team.command.normal;

import java.text.DecimalFormat;

import org.bukkit.ChatColor;
import org.bukkit.Location;

import me.hulipvp.chambers.profile.structure.Profile;
import me.hulipvp.chambers.team.command.TeamCommand;
import me.hulipvp.chambers.util.commandapi.Command;
import me.hulipvp.chambers.util.commandapi.CommandArgs;

public class TeamLocationCommand extends TeamCommand {
	
	@Command(name = "faction.location", description = "Send your coords to your teammates", aliases = { "f.location", "t.location", "s.location", "team.location", "squad.location", "faction.loc", "f.loc", "t.loc", "s.loc", "team.loc", "squad.loc", "tl", "teamlocation", "fl" }, usage = "/<command>", requiresTeam = true)
	public void onCommand(CommandArgs commandArgs) {
		Profile profile = plugin.getProfileManager().getProfileByUuid(commandArgs.getPlayer().getUniqueId());
		profile.getTeam().sendMessage(ChatColor.DARK_AQUA + "(Team) " + commandArgs.getPlayer().getName() + ": " + ChatColor.YELLOW + getCoords(commandArgs.getPlayer().getLocation()));
	}
	
	private String getCoords(Location loc) {
		DecimalFormat decimalFormat = new DecimalFormat("0.00");
		String x = decimalFormat.format(loc.getX());
		String y = decimalFormat.format(loc.getY());
		String z = decimalFormat.format(loc.getZ());
		return "[" + x + ", " + y + ", " + z + "]";
	}

}
