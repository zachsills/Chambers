package me.hulipvp.chambers.team;

import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Random;
import java.util.Set;
import java.util.UUID;
import java.util.stream.Collectors;

import org.bukkit.Bukkit;

import me.hulipvp.chambers.team.structure.Team;
import me.hulipvp.chambers.team.structure.TeamType;

public class TeamManager {

	private Set<Team> teams;

	public TeamManager() {

		teams = new HashSet<>();

		registerAllTeamTypes();
		
	}
	
	/**
	 * Get a Set of all the non-System teams that are loaded onto the server
	 * 
	 * @return Set - a Set of all the Player Teams on the server
	 */
	public Set<Team> getAllPlayerTeams() {
		return teams.stream().filter(team -> team.isPlayerTeam()).collect(Collectors.toSet());
	}

	/**
	 * Get a Set of all System teams that are loaded onto the server
	 * 
	 * @return Set - a Set of all the System teams on the server
	 */
	public Set<Team> getAllSystemTeams() {
		return teams.stream().filter(team -> !team.isPlayerTeam()).collect(Collectors.toSet());
	}

	/**
	 * Get a Team by it's name, ignoring it's case<br>
	 * Will return <tt>null</tt> if no Team with the same name is found
	 * 
	 * @param name - the name of the Team that you're trying to find
	 * @return Team - a Team with the matching name
	 */
	public Team getTeamByName(String name) {
		return teams.stream().filter(team -> team.getName().equalsIgnoreCase(name)).findFirst().orElse(null);
	}
	
	/**
	 * Get a Team by checking if it's Team type is equal to the one provided<br>
	 * Will return <tt>null</tt> if none if found, which shouldn't happen unless you input
	 * null 
	 * 
	 * @param teamType - the TeamType of the Team you wish to find
	 * @return Team - a Team with the matching type as the one provided
	 */
	public Team getTeamByType(TeamType teamType) {
		return teams.stream().filter(team -> team.getType() == teamType).findFirst().orElse(null);
	}
	
	/**
	 * Get a Team by checking if the Member set contains the provided UUID<br>
	 * Will return <tt>null</tt> if no Team with that member is found
	 * 
	 * @param uuid - the UUID of the member you wish to find
	 * @return Team - a Team with a member that has the same UUID as the one
	 *         provided
	 */
	public Team getTeamByMember(UUID uuid) {
		return getAllPlayerTeams().stream().filter(team -> team.getMembers().contains(uuid)).findFirst().orElse(null);
	}

	/**
	 * First attempts to get a Team by it's name, and if that fails, it will
	 * attempt to get a Team by a member's UUID<br>
	 * If all of that fails, then it will return <tt>null</tt>
	 * 
	 * @param string- the name Team or the name of the Member you're trying to find
	 * @return Team - a Team with the same name or with a matching member
	 */
	public Team getTeamByString(String string) {
		Team teamToReturn = null;
		if (getTeamByName(string) != null) {
			teamToReturn = getTeamByName(string);
		}
		if (Bukkit.getPlayer(string) != null && teamToReturn == null) {
			teamToReturn = getTeamByMember(Bukkit.getPlayer(string).getUniqueId());
		}
		return teamToReturn;
	}

	/**
	 * Get the smallest Team so that players can join the Teams in an ordered
	 * sequence if you want it to be an ordered sequence
	 * 
	 * @return Team - the Team with the smallest size
	 */
	public Team getSmallestTeam() {
		return getAllPlayerTeams().stream().min((teamOne, teamTwo) -> teamTwo.getSize() - teamOne.getSize()).orElseGet(null);
	}

	/**
	 * Get the size of the smallest team
	 * 
	 * @return int - the amount of members in the smallest team
	 */
	public int getSmallestTeamSize() {
		return getSmallestTeam().getSize();
	}
	
	/**
	 * Get a Random Player team for a Player to join
	 * 
	 * @return Team - a Team that was randomly chosen from the Set
	 */
	public Team getRandomTeam() {
		List<Team> playerTeams = getAllPlayerTeams().stream().filter(team -> !team.isFull()).collect(Collectors.toList());
		return playerTeams.get(new Random().nextInt(playerTeams.size()));
	}
	
	/**
	 * Register all of the Teams on the server
	 */
	public void registerAllTeamTypes() {
		teams.clear();
		Arrays.stream(TeamType.values()).forEach(teamType -> {
			teams.add(new Team(teamType));
		});
	}

}
