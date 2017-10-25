package me.hulipvp.chambers.team.structure;

import org.bukkit.ChatColor;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public enum TeamType {

	KOTH(ChatColor.AQUA, "KoTH"), 
	WILDERNESS(ChatColor.GRAY, "Wilderness"), 
	RED(ChatColor.RED, "Red"), 
	BLUE(ChatColor.BLUE, "Blue"), 
	GREEN(ChatColor.GREEN, "Green"), 
	YELLOW(ChatColor.YELLOW, "Yellow");

	private ChatColor color;
	private String name;

}
