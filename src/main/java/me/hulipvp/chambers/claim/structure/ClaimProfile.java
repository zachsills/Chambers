package me.hulipvp.chambers.claim.structure;

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
	private Location cornerOne, cornerTwo;
	private Pillar pillarOne, pillarTwo;
	
	public ClaimProfile(Profile profile) {
		this.uuid = profile.getId();
		this.profile = profile;
		this.cornerOne = null;
		this.cornerTwo = null;
		this.pillarOne = null;
		this.pillarTwo = null;
	}
	
	public void updatePillars() {
		if (this.cornerOne != null && this.cornerTwo != null) {
			
		}
	}
	
	public void clearPillars() {
		if (this.pillarOne != null) {
			this.pillarOne.remove();
			this.setPillarOne(null);
		}
		if (this.pillarTwo != null) {
			this.pillarTwo.remove();
			this.setPillarTwo(null);
		}
	}
	
}
