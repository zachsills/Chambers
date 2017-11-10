package me.hulipvp.chambers.command;

import me.hulipvp.chambers.koth.structure.Koth;
import me.hulipvp.chambers.util.commandapi.ChambersCommand;
import me.hulipvp.chambers.util.commandapi.Command;
import me.hulipvp.chambers.util.commandapi.CommandArgs;
import net.md_5.bungee.api.ChatColor;

public class KothCommand extends ChambersCommand {
	
	@Command(name = "koth", description = "See info about the Koth", aliases = { "checkkoth" }, usage = "/<command> <args>", playerOnly = true)
	public void onCommand(CommandArgs commandArgs) {
		Koth koth = plugin.getKothManager().getKoth();
		if (koth == null) {
			commandArgs.getPlayer().sendMessage(plugin.getKothManager().getKothFormat() + ChatColor.UNDERLINE + plugin.getGameManager().getGame().getKothName() + ChatColor.RESET.toString() + ChatColor.YELLOW + " is not running.");
		} else {
			commandArgs.getPlayer().sendMessage(plugin.getKothManager().getKothFormat() + ChatColor.UNDERLINE + plugin.getGameManager().getGame().getKothName() + ChatColor.RESET.toString() + ChatColor.YELLOW + " is able to be contested.");
		}
	}

}
