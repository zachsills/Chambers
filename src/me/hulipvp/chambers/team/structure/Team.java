package me.hulipvp.chambers.team.structure;

import java.util.HashSet;
import java.util.Set;
import java.util.UUID;

import org.bukkit.ChatColor;
import org.bukkit.Location;

import lombok.Getter;
import lombok.Setter;
import me.hulipvp.chambers.claim.structure.Claim;

@Getter
@Setter
public class Team {
	
	private TeamType type;
	private String name;
	private ChatColor color;
	private Set<UUID> members;
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
		this.dtr = 1.0;
		this.home = null;
	}
	
	public String getFormattedName() {
		return this.type.getColor() + this.type.getName();
	}
	
}
