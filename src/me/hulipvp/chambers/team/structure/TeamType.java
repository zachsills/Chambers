package me.hulipvp.chambers.team.structure;

import org.bukkit.ChatColor;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public enum TeamType {

	KOTH(ChatColor.AQUA, "KoTH", false), 
	WILDERNESS(ChatColor.GRAY, "Wilderness", false), 
	RED(ChatColor.RED, "Red", true), 
	BLUE(ChatColor.BLUE, "Blue", true), 
	GREEN(ChatColor.GREEN, "Green", true), 
	YELLOW(ChatColor.YELLOW, "Yellow", true);

	private ChatColor color;
	private String name;
	private boolean playerTeam;

}