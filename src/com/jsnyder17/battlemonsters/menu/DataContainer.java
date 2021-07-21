package com.jsnyder17.battlemonsters.menu;

import java.util.ArrayList;

import com.jsnyder17.battlemonsters.derby.DerbyDatabase;
import com.jsnyder17.battlemonsters.monster.Monster;
import com.jsnyder17.battlemonsters.move.Move;

public class DataContainer {
	DerbyDatabase db;
	private ArrayList<Monster> monsters;
	private ArrayList<Move> moves;
	private ArrayList<Monster> playerMonsters;
	private ArrayList<Monster> cpuMonsters;
	
	public DataContainer() {
		db = new DerbyDatabase();
		monsters = new ArrayList<Monster>();
		moves = new ArrayList<Move>();
		playerMonsters = new ArrayList<Monster>();
		cpuMonsters = new ArrayList<Monster>();
	}
	
	public void initializeData() {
		db.initializeData();
		
		monsters.clear();
		moves.clear();
		playerMonsters.clear();
		cpuMonsters.clear();
		
		monsters = db.getMonsters();
		moves = db.getMoves();
	}
	
	public void resetData() {
		db.dropTables();
		db.createTables();
		db.loadInitialData();
	}
	
	public ArrayList<Monster> getMonsters() {
		return monsters;
	}
	public ArrayList<Move> getMoves() {
		return moves;
	}
	public ArrayList<Monster> getPlayerMonsters() {
		return playerMonsters;
	}
	public ArrayList<Monster> getCpuMonsters() {
		return cpuMonsters;
	}
	
	public void addPlayerMonster(int monsterIndex, int level) {
		Monster monster = new Monster(monsters.get(monsterIndex));
		monster.setLevel(level);
		
		monster.calculateStats();
		
		playerMonsters.add(monster);
	}
	public void addCpuMonster(int monsterIndex, int level) {
		Monster monster = new Monster(monsters.get(monsterIndex));
		monster.setLevel(level);
		
		monster.calculateStats();
		
		cpuMonsters.add(monster);
	}
	
	public void addPlayerMonsterMove(int monsterIndex, int moveIndex) {
		playerMonsters.get(monsterIndex).addMove(moves.get(moveIndex));
	}
	public void addCpuMonsterMove(int monsterIndex, int moveIndex) {
		cpuMonsters.get(monsterIndex).addMove(moves.get(moveIndex));
	}
	
	public void removePlayerMonster(int index) {
		playerMonsters.remove(index);
	}
	
	public int findMonster(String monsterName) {
		boolean found = false;
		int index = 0;
		
		while (!found && index < monsters.size()) {
			if (monsters.get(index).getName().equals(monsterName)) {
				found = true;
			}
			else {
				index += 1;
			}
		}
		if (found) {
			return index;
		}
		else {
			return -1;
		}
	}
	
	public int findPlayerMonster(String monsterName) {
		boolean found = false;
		int index = 0;
		
		while (!found && index < playerMonsters.size()) {
			if (playerMonsters.get(index).getName().equals(monsterName)) {
				found = true;
			}
			else {
				index += 1;
			}
		}
		if (found) {
			return index;
		}
		else {
			return -1;
		}
	}
	
	public int findCpuMonster(String monsterName) {
		boolean found = false;
		int index = 0;
		
		while (!found && index < cpuMonsters.size()) {
			if (cpuMonsters.get(index).getName().equals(monsterName)) {
				found = true;
			}
			else {
				index += 1;
			}
		}
		if (found) {
			return index;
		}
		else {
			return -1;
		}
	}
	
	public int findMove(String moveName) {
		boolean found = false;
		int index = 0;
		
		while (!found && index < moves.size()) {
			if (moves.get(index).getName().equals(moveName)) {
				found = true;
			}
			else {
				index += 1;
			}
		}
		if (found) {
			return index;
		}
		else {
			return -1;
		}
	}
}