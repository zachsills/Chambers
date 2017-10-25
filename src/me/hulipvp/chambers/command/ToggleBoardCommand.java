package me.hulipvp.chambers.command;

import org.bukkit.ChatColor;

import me.hulipvp.chambers.profile.structure.Profile;
import me.hulipvp.chambers.util.commandapi.ChambersCommand;
import me.hulipvp.chambers.util.commandapi.Command;
import me.hulipvp.chambers.util.commandapi.CommandArgs;

public class ToggleBoardCommand extends ChambersCommand {
	
	@Command(name = "toggleboard", description = "Toggle your scoreboard on and off", aliases = { "togglesb", "togglescoreboard", "hideboard", "showboard" }, usage = "/<command>", playerOnly = true, requiresTeam = false)
	public void onCommand(CommandArgs commandArgs) {
		Profile profile = plugin.getProfileManager().getProfileByUuid(commandArgs.getPlayer().getUniqueId());
		profile.setHidingScoreboard(profile.isHidingScoreboard() ? false : true);
		profile.sendMessage(ChatColor.YELLOW + "Scoreboard has been " + (profile.isHidingScoreboard() ? ChatColor.RED + "disabled": ChatColor.GREEN + "enabled") + ChatColor.YELLOW + ".");
	}

}
