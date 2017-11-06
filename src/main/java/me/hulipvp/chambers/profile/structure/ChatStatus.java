package me.hulipvp.chambers.profile.structure;

import java.util.Arrays;
import java.util.List;

import org.bukkit.ChatColor;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public enum ChatStatus {

	FACTION(ChatColor.YELLOW + "You have set your chat status to " + ChatColor.GREEN + "faction" + ChatColor.YELLOW + " chat.", Arrays.asList("f", "a", "ally", "faction", "fac", "t", "team")), 
	PUBLIC(ChatColor.YELLOW + "You have set your chat status to " + ChatColor.RED + "global" + ChatColor.YELLOW + " chat.", Arrays.asList("p", "public", "g", "global"));
	
	private String statusMessage;
	private List<String> aliases;
	
	public static ChatStatus getStatusFromString(String string) {
		return Arrays.stream(ChatStatus.values()).filter(status -> status.getAliases().contains(string.toLowerCase())).findFirst().orElse(null);
	}

}
