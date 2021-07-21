package com.jsnyder17.battlemonsters.move;

import com.jsnyder17.battlemonsters.util.IntegerConverter;

public class Move {
	protected int id;
	protected String name;
	protected int type;
	protected int category;
	protected int power;
	protected int accuracy;
	protected int maxPP;
	protected int currentPP;
	private IntegerConverter ic;
	
	public Move() {
		id = 0;
		name = "";
		type = 0;
		category = 0;
		power = 0;
		accuracy = 0;
		maxPP = 0;
		currentPP = 0;
		ic = new IntegerConverter();
	}
	public Move(Move move) {
		this.name = move.name;
		this.type = move.type;
		this.category = move.category;
		this.power = move.power;
		this.accuracy = move.accuracy;
		this.maxPP = move.maxPP;
		this.currentPP = this.maxPP;
	}
	
	public int getId() {
		return id;
	}
	public String getName() {
		return name;
	}
	public int getType() {
		return type;
	}
	public int getCategory() {
		return category;
	}
	public int getPower() {
		return power;
	}
	public int getAccuracy() {
		return accuracy;
	}
	public int getMaxPP() {
		return maxPP;
	}
	public int getCurrentPP() {
		return currentPP;
	}
	
	
	public void setId(int id) {
		this.id = id;
	}
	public void setName(String name) {
		this.name = name;
	}
	public void setType(int type) {
		this.type = type;
	}
	public void setCategory(int category) {
		this.category = category;
	}
	public void setPower(int power) {
		this.power = power;
	}
	public void setAccuracy(int accuracy) {
		this.accuracy = accuracy;
	}
	public void setMaxPP(int maxPP) { 
		this.maxPP = maxPP;
	}
	public void setCurrentPP(int currentPP) {
		this.currentPP = currentPP;
	}
	
	public String toString() {
		return name + "\n"
			+ "Type: " + ic.getTypeString(type) + " | CAT: " + ic.getCategoryString(category) + " | POW: " + power + " | ACC: " + accuracy + " | PP: " + maxPP;
	}
}
