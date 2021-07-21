package com.jsnyder17.battlemonsters.battle;

import java.util.ArrayList;

import com.jsnyder17.battlemonsters.monster.Monster;

public class Trainer {
	private String name;
	private ArrayList<Monster> monsters;
	
	public Trainer(String name, ArrayList<Monster> monsters) {
		this.name = name;
		this.monsters = monsters;
	}
	
	public String getName() {
		return name;
	}
	public ArrayList<Monster> getMonsters() {
		return monsters;
	}
	
	public void setName(String name) {
		this.name = name;
	}
	
	public void addMonster(Monster monster) {
		monsters.add(monster);
	}
	public void removeMonster(Monster monster) {
		monsters.remove(monster);
	}
}
