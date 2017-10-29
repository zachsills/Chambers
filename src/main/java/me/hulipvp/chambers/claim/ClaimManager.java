package me.hulipvp.chambers.claim;

import java.util.HashSet;
import java.util.Set;

import org.bukkit.Location;

import me.hulipvp.chambers.claim.structure.Claim;
import me.hulipvp.chambers.claim.structure.ClaimProfile;
import me.hulipvp.chambers.profile.structure.Profile;
import me.hulipvp.chambers.team.structure.Team;

public class ClaimManager {

	private Set<Claim> claims;
	private Set<ClaimProfile> claimers;

	/**
	 * Just initializes all of the private fields so they're not null Nothing
	 * surprising Only one field so... heh
	 */
	public ClaimManager() {

		claims = new HashSet<>();
		claimers = new HashSet<>();

	}

	/**
	 * Will return <tt>null</tt> if no Claim is found at that certain Location
	 * provided
	 * 
	 * @param location - the Location you wish to find a claim at
	 * @return Claim - A claim that is found surrounding the provided location
	 * @see {@link Claim#isInsideClaim(Location)}
	 */
	public Claim getClaimAt(Location location) {
		return claims.stream().filter(claim -> claim.isInsideClaim(location)).findFirst().orElse(null);
	}

	/**
	 * Attempts to find a claim with the same owner Faction as the provided
	 * Faction<br>
	 * Will return <tt>null</tt> if no Claim with the same owner Faction is not
	 * found
	 * 
	 * @param owner - the Team you wish to see has a claim
	 * @return Claim - A claim with the same owner Faction as the one provided
	 * @see {@link Claim#getOwner()}
	 */
	public Claim getClaimByTeam(Team owner) {
		return claims.stream().filter(claim -> claim.getOwner() == owner).findFirst().orElse(null);
	}

	/**
	 * Adds a Claim to the <tt>Set</tt> of claims if the Claim is already
	 * created
	 * 
	 * @param claim
	 *            - the Claim you wish to store
	 */
	public void addClaim(Claim claim) {
		claims.add(claim);
	}

	/**
	 * Instantiates a new claim and then stores it to the <tt>Set</tt>
	 * 
	 * @param cornerOne - the first corner Location of the claim
	 * @param cornerTwo - the second corner Location of the claim
	 * @param owner - the owner Faction of the claim
	 */
	public void addClaim(Location cornerOne, Location cornerTwo, Team owner) {
		claims.add(new Claim(cornerOne, cornerTwo, owner));
	}

	/**
	 * Simply removes a claim stores in the Set
	 * 
	 * @param claim - the Claim you wish to remove
	 */
	public void removeClaim(Claim claim) {
		claims.remove(claim);
	}
	
	/**
	 * Get a ClaimProfile of a Profile to see whether has initiated a Claim sequence or not<br>
	 * Will return <tt>null</tt> if no ClaimProfile was found with the provided Profile
	 * 
	 * @param profile - the Profile of the ClaimProfile you wish to find
	 * @return ClaimProfile - a ClaimProfile with a matching Profile as the one provided
	 */
	public ClaimProfile getClaimProfile(Profile profile) {
		return claimers.stream().filter(claimProfile -> claimProfile.getProfile() == profile).findFirst().orElse(null);
	}
	
	/**
	 * Check to see whether a player is claiming or not
	 * 
	 * @param profile - the Profile of the player that you wish to see has initiated a claim sequence
	 * @return boolean - Returns <tt>true</tt> if the ClaimProfile is not null, however returns <tt>false</tt> if no ClaimProfile was found
	 */
	public boolean isClaiming(Profile profile) {
		return getClaimProfile(profile) != null;
	}
	
	/**
	 * Add a new ClaimProfile and then store it to manage it
	 * 
	 * @param profile - the Profile you wish to store in the ClaimProfile
	 */
	public void addClaimProfile(Profile profile) {
		claimers.add(new ClaimProfile(profile));
	}
	
	/**
	 * Removes a ClaimProfile from the Set of ClaimProfiles... obviously
	 * 
	 * @param profile - the Profile of the ClaimProfile you wish to remove
	 */
	public void removeClaimProfile(Profile profile) {
		ClaimProfile claimProfile = getClaimProfile(profile);
		claimers.remove(claimProfile);
	}
	
}
