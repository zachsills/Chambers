package me.hulipvp.chambers.command;

import java.util.Arrays;

import org.bukkit.ChatColor;

import me.hulipvp.chambers.shop.structure.VillagerType;
import me.hulipvp.chambers.util.commandapi.ChambersCommand;
import me.hulipvp.chambers.util.commandapi.Command;
import me.hulipvp.chambers.util.commandapi.CommandArgs;

public class VillagerCommand extends ChambersCommand {
	
	@Command(name = "villager", description = "Spawn in Shop Villagers", aliases = { "spawnvillager" }, usage = "/<command> <args>", playerOnly = true, requiresTeam = false, adminsOnly = true)
	public void onCommand(CommandArgs commandArgs) {
		if (commandArgs.length() != 1) {
			commandArgs.getPlayer().sendMessage(ChatColor.RED + "Usage: /" + commandArgs.getLabel() + " <all|type>");
			return;
		}
		String argument = commandArgs.getArgs(0);
		if (argument.equalsIgnoreCase("all")) {
			plugin.getVillagerManager().spawnAllVillagers();
			commandArgs.getPlayer().sendMessage(ChatColor.GREEN + "All Villagers have been spawned.");
		} else {
			VillagerType villagerType = VillagerType.getTypeFromString(commandArgs.getArgs(0));
			if (villagerType == null) {
				commandArgs.getPlayer().sendMessage(ChatColor.RED + "Invalid Villager type. Use one the following:");
				Arrays.stream(VillagerType.values()).forEach(type -> commandArgs.getPlayer().sendMessage(type.getFormattedName()));
				return;
			}
			plugin.getVillagerManager().spawnVillager(villagerType, commandArgs.getPlayer().getLocation());
		}
	}

}
