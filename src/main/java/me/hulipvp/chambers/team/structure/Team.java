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
	
	public List<Player> getOnlinePlayers() {
		return this.members.stream().map(uuid -> Bukkit.getPlayer(uuid)).collect(Collectors.toList());
	}

	public String getFormattedName() {
		return this.type.getColor() + this.type.getName();
	}

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
	
	public void removeMember(Profile profile) {
		profile.setTeam(null);
		this.members.remove(profile.getId());
	}

	public int getSize() {
		return this.members.size();
	}

	public boolean isFull() {
		return this.members.size() >= 5;
	}
	
	public boolean isRaidable() {
		return this.dtr <= 0;
	}
	
	public boolean isPlayerTeam() {
		return this.type.isPlayerTeam();
	}
	
	public void sendMessage(String message) {
		this.getOnlinePlayers().stream().forEach(player -> player.sendMessage(message));
	}

}
