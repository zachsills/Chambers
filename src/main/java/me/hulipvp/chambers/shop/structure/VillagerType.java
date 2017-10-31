package me.hulipvp.chambers.shop.structure;

import java.util.Arrays;

import org.bukkit.ChatColor;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public enum VillagerType {
	
	COMBAT(ChatColor.RED, "Combat Shops"),
	SELLER(ChatColor.RED, "Sell Shop"),
	TOOLS(ChatColor.RED, "Tools Shop"),
	ENCHANTER(ChatColor.GREEN, "Tim the Enchanter");
	
	private ChatColor color;
	private String name;
	
	public String getFormattedName() {
		return this.getColor() + this.getName();
	}
	
	public static VillagerType getTypeFromString(String string) {
		return Arrays.stream(VillagerType.values()).filter(villagerType -> villagerType.name().equalsIgnoreCase(string)).findFirst().orElse(null);
	}

}
