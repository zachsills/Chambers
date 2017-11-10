package me.hulipvp.chambers.team.structure;

import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.UUID;
import java.util.stream.Collectors;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.entity.Player;

import lombok.Getter;
import lombok.Setter;
import me.hulipvp.chambers.Chambers;
import me.hulipvp.chambers.claim.structure.Claim;
import me.hulipvp.chambers.profile.structure.Profile;
import me.hulipvp.chambers.util.LocationUtil;

@Getter
@Setter
public class Team {

	private TeamType type;
	private String name;
	private ChatColor color;
	private Set<UUID> members;
	private Set<String> offline;
	private double dtr;
	private Location home;
	private Claim claim;

	public Team(TeamType type) {
		this.type = type;
		this.name = type.getName();
		this.color = type.getColor();
		this.claim = null;
		this.members = new HashSet<>();
		this.offline = new HashSet<>();
		this.dtr = 1.0;
		this.home = Chambers.getInstance().getDataFile().getString("TEAMS." + type.name() + ".HOME") != null ? LocationUtil.deserializeLocation(Chambers.getInstance().getDataFile().getString("TEAMS." + type.name() + ".HOME")) : null;
		if (Chambers.getInstance().getDataFile().getString("TEAMS." + type.name() + ".CORNERONE") != null && Chambers.getInstance().getDataFile().getString("TEAMS." + type.name() + ".CORNERTWO") != null) {
			this.claim = new Claim(LocationUtil.deserializeLocation(Chambers.getInstance().getDataFile().getString("TEAMS." + type.name() + ".CORNERONE")), LocationUtil.deserializeLocation(Chambers.getInstance().getDataFile().getString("TEAMS." + type.name() + ".CORNERTWO")), this);
			Chambers.getInstance().getClaimManager().addClaim(this.claim);
		}
	}
	
	/**
	 * Get all of the Players that are online in the Team
	 * 
	 * @return List - a list of Player objects
	 */
	public List<Player> getOnlinePlayers() {
		return this.members.stream().map(uuid -> Bukkit.getPlayer(uuid)).collect(Collectors.toList());
	}

	/**
	 * Get the formatted name of the Team, obviously
	 * 
	 * @return String - the formatted name of the Team 
	 */
	public String getFormattedName() {
		return this.type.name().contains("KOTH") ? Chambers.getInstance().getKothManager().getFormattedKothName() : this.type.getColor() + this.type.getName();
	}

	/**
	 * Add a member to the Team
	 * 
	 * @param profile - the Profile of the member you wish to add
	 */
	public void addMember(Profile profile) {
		if (profile.getTeam() != null) {
			if (profile.getTeam() == this) {
				profile.sendMessage(ChatColor.GRAY + "You are already on the " + this.getFormattedName() + ChatColor.GRAY + " team.");
				return;
			}
			profile.getTeam().removeMember(profile);
		}
		profile.setTeam(this);
		this.members.add(profile.getId());
	}
	
	/**
	 * Remove a member from the Team
	 * 
	 * @param profile - the Profile of the member you wish to remove
	 */
	public void removeMember(Profile profile) {
		profile.setTeam(null);
		this.members.remove(profile.getId());
	}

	/**
	 * Get the current amount of members in the Team
	 * 
	 * @return int - the amount of members in the Team
	 */
	public int getSize() {
		return this.members.size();
	}

	/**
	 * See if the Team is at the maximum amount of members
	 * 
	 * @return boolean - <tt>true</tt> if the team is full, otherwise <tt>false</tt>
	 */
	public boolean isFull() {
		return this.members.size() >= 5;
	}
	
	/**
	 * See whether the Team is raidable or not
	 * 
	 * @return boolean - <tt>true</tt> if the Team is raidable, otherwise <tt>false</tt>
	 */
	public boolean isRaidable() {
		return this.dtr <= 0.0;
	}
	
	/**
	 * See whether the Team is a player team or not
	 * 
	 * @return boolean - <tt>true</tt> if the Team is a player team, otherwise <tt>false</tt>
	 */
	public boolean isPlayerTeam() {
		return this.type.isPlayerTeam();
	}
	
	/**
	 * Send a message to all of the online Players of the Team
	 * 
	 * @param message - the message you wish to send to the Team
	 */
	public void sendMessage(String message) {
		this.getOnlinePlayers().stream().forEach(player -> player.sendMessage(message));
	}

}
