package me.hulipvp.chambers.team.command.normal;

import org.bukkit.ChatColor;

import me.hulipvp.chambers.profile.structure.ChatStatus;
import me.hulipvp.chambers.profile.structure.Profile;
import me.hulipvp.chambers.team.command.TeamCommand;
import me.hulipvp.chambers.util.commandapi.Command;
import me.hulipvp.chambers.util.commandapi.CommandArgs;

public class TeamChatCommand extends TeamCommand {
	
	@Command(name = "faction.chat", description = "The command to switch chat status", aliases = { "f.chat", "t.chat", "s.chat", "team.chat", "squad.chat", "faction.c", "f.c", "t.c", "s.c", "team.c", "squad.c" }, usage = "/<command> <args>", requiresTeam = true)
	public void onCommand(CommandArgs commandArgs) {
		Profile profile = plugin.getProfileManager().getProfileByUuid(commandArgs.getPlayer().getUniqueId());
		if (commandArgs.length() != 1) {
			profile.setChatStatus(profile.getChatStatus() == ChatStatus.FACTION ? ChatStatus.PUBLIC : ChatStatus.FACTION);
		} else {
			ChatStatus chatStatus = ChatStatus.getStatusFromString(commandArgs.getArgs(0));
			if (chatStatus == null) {
				profile.sendMessage(ChatColor.RED + "Please enter a valid status type.");
				return;
			}
			profile.setChatStatus(chatStatus);
		}
		profile.sendMessage(profile.getChatStatus().getStatusMessage());
	}
	
	

}