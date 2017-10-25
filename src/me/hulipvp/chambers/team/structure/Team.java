package me.hulipvp.chambers.team.structure;

import java.util.HashSet;
import java.util.Set;
import java.util.UUID;

import org.bukkit.ChatColor;
import org.bukkit.Location;

import lombok.Getter;
import lombok.Setter;
import me.hulipvp.chambers.claim.structure.Claim;
import me.hulipvp.chambers.profile.structure.Profile;

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
	private boolean playerTeam;

	public Team(TeamType type) {
		this.type = type;
		this.name = type.getName();
		this.color = type.getColor();
		this.claim = null;
		this.members = new HashSet<>();
		this.offline = new HashSet<>();
		this.dtr = 1.0;
		this.home = null;
	}

	public String getFormattedName() {
		return this.type.getColor() + this.type.getName();
	}

	public void addMember(Profile profile) {
		profile.setTeam(this);
		this.members.add(profile.getId());
	}

	public int getSize() {
		return this.members.size();
	}

	public boolean isFull() {
		return this.members.size() >= 5;
	}

}
