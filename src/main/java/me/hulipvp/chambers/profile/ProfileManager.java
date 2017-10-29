package me.hulipvp.chambers.profile;

import java.util.HashSet;
import java.util.Set;
import java.util.UUID;
import java.util.stream.Collectors;

import me.hulipvp.chambers.profile.structure.Profile;

public class ProfileManager {

	private Set<Profile> profiles;

	public ProfileManager() {

		profiles = new HashSet<>();

	}
	
	/**
	 * Get all of the Profiles of the Players that are currently playing the Game
	 * 
	 * @return Set - a Set of Profiles of the Players that have a Team
	 */
	public Set<Profile> getAllPlayingProfiles() {
		return profiles.stream().filter(profile -> profile.getTeam() != null).collect(Collectors.toSet());
	}

	/**
	 * Get a Profile by checking if their UUID is the same as the one
	 * provided<br>
	 * Will return <tt>null</tt> if no Profile with that UUID is found
	 * 
	 * @param uuid - the UUID of the Profile that you wish to find
	 * @return Profile - a Profile with the matching UUID as the one provided
	 */
	public Profile getProfileByUuid(UUID uuid) {
		return profiles.stream().filter(profile -> profile.getId() == uuid).findFirst().orElse(null);
	}

	/**
	 * Simply stores a Profile to the Set of profiles
	 * 
	 * @param profile - the Profile you wish to add
	 */
	public void addProfile(Profile profile) {
		profiles.add(profile);
	}

	/**
	 * Simply removes a Profile from the Set of profiles
	 * 
	 * @param profile - the Profile you wish to remove
	 */
	public void removeProfile(Profile profile) {
		profiles.remove(profile);
	}
	
}
