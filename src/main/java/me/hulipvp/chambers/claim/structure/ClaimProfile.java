package me.hulipvp.chambers.claim.structure;

import java.util.HashSet;
import java.util.Set;
import java.util.UUID;

import org.bukkit.Location;

import lombok.Getter;
import lombok.Setter;
import me.hulipvp.chambers.profile.structure.Profile;

@Getter
@Setter
public class ClaimProfile {
	
	private UUID uuid;
	private Profile profile;
	private Set<Pillar> pillars;
	private Location cornerOne, cornerTwo;
	
	public ClaimProfile(Profile profile) {
		this.uuid = profile.getId();
		this.profile = profile;
		this.pillars = new HashSet<>();
		this.cornerOne = null;
		this.cornerTwo = null;
	}

}
