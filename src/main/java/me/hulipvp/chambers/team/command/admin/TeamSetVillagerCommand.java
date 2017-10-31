package me.hulipvp.chambers.team.command.admin;

import java.util.Arrays;

import org.bukkit.ChatColor;

import me.hulipvp.chambers.shop.structure.VillagerType;
import me.hulipvp.chambers.team.command.TeamCommand;
import me.hulipvp.chambers.team.structure.Team;
import me.hulipvp.chambers.util.LocationUtil;
import me.hulipvp.chambers.util.commandapi.Command;
import me.hulipvp.chambers.util.commandapi.CommandArgs;

public class TeamSetVillagerCommand extends TeamCommand {
	
	@Command(name = "faction.setvillager", aliases = { "f.setvillager","f.setshop", "faction.setenchanter", "faction.settools", "factions.settim", "f.setcombat" }, adminsOnly = true, requiresTeam = false)
	public void onCommand(CommandArgs commandArgs) {
		if (commandArgs.length() != 2) {
			commandArgs.getPlayer().sendMessage(ChatColor.RED + "Usage: /" + commandArgs.getLabel() + " <team> <villagerType>");
			return;
		}
		Team team = plugin.getTeamManager().getTeamByName(commandArgs.getArgs(0));
		if (team == null) {
			commandArgs.getPlayer().sendMessage(ChatColor.RED + "Invalid team type.");
			return;
		}
		VillagerType villagerType = VillagerType.getTypeFromString(commandArgs.getArgs(1));
		if (villagerType == null) {
			commandArgs.getPlayer().sendMessage(ChatColor.RED + "Invalid Villager type. Use one the following:");
			Arrays.stream(VillagerType.values()).forEach(type -> commandArgs.getPlayer().sendMessage(type.getFormattedName()));
			return;
		}
		Team teamAtLocation = plugin.getClaimManager().getTeamAt(commandArgs.getPlayer().getLocation());
		if (team != teamAtLocation) {
			commandArgs.getPlayer().sendMessage(ChatColor.RED + "You are in the wrong territory.");
			return;
		}
		plugin.getDataFile().getConfiguration().set("TEAMS." + team.getType().name() + "." + villagerType.name(), LocationUtil.serializeLocation(commandArgs.getPlayer().getLocation()));
		plugin.getDataFile().save();
		plugin.getDataFile().load();
		commandArgs.getPlayer().sendMessage(ChatColor.GRAY + "Set villager location for Team: " + team.getFormattedName() + ChatColor.GRAY + " - " + villagerType.getFormattedName());
		return;
	}

}
