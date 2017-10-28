package me.hulipvp.chambers.team.structure;

import org.bukkit.ChatColor;
import org.bukkit.Material;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public enum TeamType {

	KOTH(ChatColor.AQUA, "KoTH", false, Material.BEACON), 
	WILDERNESS(ChatColor.GRAY, "Wilderness", false, null), 
	RED(ChatColor.RED, "Red", true, Material.REDSTONE_BLOCK), 
	BLUE(ChatColor.BLUE, "Blue", true, Material.DIAMOND_BLOCK), 
	GREEN(ChatColor.GREEN, "Green", true, Material.EMERALD_BLOCK), 
	YELLOW(ChatColor.YELLOW, "Yellow", true, Material.GOLD_BLOCK);

	private ChatColor color;
	private String name;
	private boolean playerTeam;
	private Material claimMaterial;

}