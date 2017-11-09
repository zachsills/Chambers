package me.hulipvp.chambers.koth.structure;

import java.util.PriorityQueue;
import java.util.Queue;

import lombok.Getter;
import lombok.Setter;
import me.hulipvp.chambers.profile.structure.Profile;

@Getter
@Setter
public class Koth {
	
	private String name;
	private Profile capper;
	private int time, maxCapTime;
	private Queue<Profile> capQueue;
	
	/**
	 * Constructs a new Koth instance
	 * 
	 * @param name - the name of the Koth
	 * @param time - the initial capture time of the Koth
	 */
	public Koth(String name, int time) {
		this.name = name;
		this.time = time;
		this.maxCapTime = time;
		this.capper = null;
		this.capQueue = new PriorityQueue<>();
	}

}
