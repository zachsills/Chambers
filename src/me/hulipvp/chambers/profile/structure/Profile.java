package me.hulipvp.chambers.profile.structure;

import java.util.UUID;

import org.bukkit.entity.Player;

import lombok.Getter;
import lombok.Setter;
import me.hulipvp.chambers.team.structure.Team;

@Getter
@Setter
public class Profile {
	
	private UUID id;
	private ProfileStatus profileStatus;
	private ChatStatus chatStatus;
	private Team team;
	private int balance;
	
	public Profile(Player player, ProfileStatus profileStatus) {
		this.id = player.getUniqueId();
		this.profileStatus = profileStatus;
		this.chatStatus = ChatStatus.FACTION;
		this.team = null;
		this.balance = 100;
	}

}