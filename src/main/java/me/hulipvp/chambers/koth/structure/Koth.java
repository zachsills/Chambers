package me.hulipvp.chambers.koth.structure;

import lombok.Getter;
import lombok.Setter;
import me.hulipvp.chambers.profile.structure.Profile;

@Getter
@Setter
public class Koth {
	
	private String name;
	private Profile capper;
	private int time, maxCapTime;
	
	public Koth(String name, int time) {
		this.name = name;
		this.time = time;
		this.maxCapTime = time;
		this.capper = null;
	}

}
