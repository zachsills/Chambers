package me.hulipvp.chambers.shop;

import org.bukkit.Location;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Villager;
import org.bukkit.entity.Villager.Profession;
import org.bukkit.event.player.PlayerTeleportEvent.TeleportCause;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

import me.hulipvp.chambers.shop.structure.VillagerType;

public class VillagerManager {
	
	/**
	 * Spawns a Villager of the given VillagerType at the provided Location
	 * 
	 * @param type - the Type of the Villager you wish to Spawn
	 * @param location - the Location at which you want the Villager to be
	 * @return Villager - the Villager that you had set at the provided Location if you wish to use it
	 */
	public static Villager spawnVillager(VillagerType type, Location location) {
		if (!location.getChunk().isLoaded()) {
			location.getChunk().load();
		}
		Villager villager = (Villager) location.getWorld().spawnEntity(location, EntityType.VILLAGER);
		villager.setAdult();
		villager.setAgeLock(true);
		villager.setProfession(Profession.FARMER);
		villager.setRemoveWhenFarAway(false);
		villager.setCustomName(type.getColor() + type.getName());
		villager.setCustomNameVisible(true);
		villager.addPotionEffect(new PotionEffect(PotionEffectType.SPEED, Integer.MAX_VALUE, -6, true), true);
		villager.teleport(location, TeleportCause.PLUGIN);
		villager.setHealth(20.0D);
		return villager;
	}

}
