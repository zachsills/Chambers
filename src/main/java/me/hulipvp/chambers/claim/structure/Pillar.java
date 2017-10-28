package me.hulipvp.chambers.claim.structure;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.entity.Player;

import lombok.Getter;

@Getter
public class Pillar {

	private UUID claimerId;
	private Player claimer;
	private Location location;
	private Material material;

	public Pillar(Player claimer, Location location, Material material) {
		this.claimerId = claimer.getUniqueId();
		this.claimer = claimer;
		this.location = location;
		this.material = material;
	}

	public void display() {
		List<Integer> values = new ArrayList<>();
		for (int i = 0; i <= 256; i++) {
			Location location = new Location(this.getLocation().getWorld(), (double) this.location.getBlockX(), (double) i, (double) this.location.getBlockZ());
			if (location.getBlock().getType() == Material.AIR && claimer != null) {
				if (values.contains(location.getBlockY())) {
					this.claimer.sendBlockChange(location, this.material, (byte) 0);
					this.claimer.sendBlockChange(location.add(0.0, 2.0, 0.0), Material.GLASS, (byte) 0);
				} else {
					this.claimer.sendBlockChange(location, Material.GLASS, (byte) 0);
					values.add(location.getBlockY() + 2);
				}
			}
		}
	}

	public void remove() {
		Location loc = null;
		for (int i = 0; i <= 256; i++) {
			loc = new Location(this.location.getWorld(), this.location.getBlockX(), i, this.location.getBlockZ());
			if (loc.getBlock().getType() == Material.AIR) {
				this.claimer.sendBlockChange(loc, Material.AIR, (byte) 0);
			}
		}
	}

}
