package me.hulipvp.chambers.team.structure;

import org.bukkit.ChatColor;

import lombok.Getter;

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
	
	TeamType(ChatColor color, String name) {
		this.color = color;
		this.name = name;
	}

}
