package com.jsnyder17.battlemonsters.monster;

import java.util.ArrayList;
import java.util.Random;

import com.jsnyder17.battlemonsters.move.Move;
import com.jsnyder17.battlemonsters.util.IntegerConverter;

public class Monster {
	private int id;
	private String name;
	private int type1;
	private int type2;
	private int level;
	
	private Stat hp;
	private Stat attack;
	private Stat defense;
	private Stat specialAttack;
	private Stat specialDefense;
	private Stat speed;
	
	private ArrayList<Move> moves;
	private IntegerConverter ic;
	private String imageFrontPath;
	private String imageBackPath;
	
	public Monster() {
		id = 0;
		name = "";
		type1 = 0;
		type2 = 0;
		level = 0;
		hp = new Stat();
		attack = new Stat();
		specialAttack = new Stat();
		defense = new Stat();
		specialDefense = new Stat();
		speed = new Stat();
		moves = new ArrayList<Move>();
		ic = new IntegerConverter();
		imageFrontPath = "";
		imageBackPath = "";
	}
	
	public Monster(Monster monster) {
		this.name = monster.name;
		this.level = monster.level;
		this.type1 = monster.type1;
		this.type2 = monster.type2;
		this.hp = new Stat(monster.hp);
		this.attack = new Stat(monster.attack);
		this.defense = new Stat(monster.defense);
		this.specialAttack = new Stat(monster.specialAttack);
		this.specialDefense = new Stat(monster.specialDefense);
		this.speed = new Stat(monster.speed);
		
		moves = new ArrayList<Move>();
		
		for (Move m : monster.getMoves()) {
			moves.add(new Move(m));
		}
		
		ic = new IntegerConverter();
		this.imageFrontPath = monster.imageFrontPath;
		this.imageBackPath = monster.imageBackPath;
	}
	
	public int getId() {
		return id;
	}
	public String getName() {
		return name;
	}
	public Stat getHp() {
		return hp;
	}
	public int getLevel() {
		return level;
	}
	public int getType1() {
		return type1;
	}
	public int getType2() {
		return type2;
	}
	public Stat getAttack() {
		return attack;
	}
	public Stat getSpecialAttack() {
		return specialAttack;
	}
	public Stat getDefense() {
		return defense;
	}
	public Stat getSpecialDefense() {
		return specialDefense;
	}
	public Stat getSpeed() {
		return speed;
	}
	public ArrayList<Move> getMoves() {
		return moves;
	}
	public String getImageFrontPath() {
		return imageFrontPath;
	}
	public String getImageBackPath() {
		return imageBackPath;
	}
	
	public void setId(int id) {
		this.id = id;
	}
	public void setName(String name) {
		this.name = name;
	}
	public void setHp(Stat hp) {
		this.hp = hp;
	}
	public void setLevel(int level) {
		this.level = level;
	}
	public void setType1(int type1) {
		this.type1 = type1;
	}
	public void setType2(int type2) {
		this.type2 = type2;
	}
	public void setAttack(Stat attack) {
		this.attack = attack;
	}
	public void setSpecialAttack(Stat specialAttack) {
		this.specialAttack = specialAttack;
	}
	public void setDefense(Stat defense) {
		this.defense = defense;
	}
	public void setSpecialDefense(Stat specialDefense) {
		this.specialDefense = specialDefense;
	}
	public void setSpeed(Stat speed) {
		this.speed = speed;
	}
	public void setImageFrontPath(String imageFrontPath) {
		this.imageFrontPath = imageFrontPath;
	}
	public void setImageBackPath(String imageBackPath) {
		this.imageBackPath = imageBackPath;
	}
	
	public void addMove(Move move) {
		moves.add(move);
	}
	
	public void calculateImagePaths() {
		imageFrontPath = "resources/" + name + "_front.jpg";
		imageBackPath = "resources/" + name + "_back.jpg";
	}
	
	public void calculateStats() {
		double finalHpValue;
		double finalAttackValue;
		double finalDefenseValue;
		double finalSpecialAttackValue;
		double finalSpecialDefenseValue;
		double finalSpeedValue;
		
		Random rand = new Random();
		int iv = 1;
		
		hp.setIv(rand.nextInt(32));
		specialDefense.setIv(rand.nextInt(32));
		
		attack.setIv(iv);
		defense.setIv(iv);
		specialAttack.setIv(iv);
		speed.setIv(iv);
		
		hp.setEv(0);
		attack.setEv(0);
		defense.setEv(0);
		specialAttack.setEv(0);
		specialDefense.setEv(0);
		speed.setEv(0);
		
		finalHpValue = (((2 * hp.getBase() + hp.getIv() + (hp.getEv()/4)) * level) /100) + level + 10;
		finalAttackValue = (((2 * attack.getBase() + attack.getIv() + (attack.getEv()/4)) * level) /100) + 5;
		finalDefenseValue = (((2 * defense.getBase() + defense.getIv() + (defense.getEv()/4)) * level) /100) + 5;
		finalSpecialAttackValue = (((2 * specialAttack.getBase() + specialAttack.getIv() + (specialAttack.getEv()/4)) * level) /100) + 5;
		finalSpecialDefenseValue = (((2 * specialDefense.getBase() + specialDefense.getIv() + (specialDefense.getEv()/4)) * level) /100) + 5;
		finalSpeedValue = (((2 * speed.getBase() + speed.getIv() + (speed.getEv()/4)) * level) /100) + 5;
		
		hp.setFinalValue(finalHpValue); 
		attack.setFinalValue(finalAttackValue);
		defense.setFinalValue(finalDefenseValue);
		specialAttack.setFinalValue(finalSpecialAttackValue);
		specialDefense.setFinalValue(finalSpecialDefenseValue);
		speed.setFinalValue(finalSpeedValue);
	}
	
	public String toString() {
		String str = "";
		
		str = name + "\n"
			+ "Type: " + ic.getTypeString(type1) + ", " + ic.getTypeString(type2) + " | HP: " + hp.getFinalValue() + " | ATK: " + attack.getFinalValue() + " | DEF: " + defense.getFinalValue() + " | Sp. ATK: " + specialAttack.getFinalValue() + " | Sp. DEF: " + specialDefense.getFinalValue() + " | SPD: " + speed.getFinalValue(); 
		
		return str;
	}
	
	public String displayBaseStats() {
		String str = "";
		
		str = name + "\n"
			+ "Type: " + ic.getTypeString(type1) + ", " + ic.getTypeString(type2) + " | HP: " + hp.getBase() + " | ATK: " + attack.getBase() + " | DEF: " + defense.getBase() + " | Sp. ATK: " + specialAttack.getBase() + " | Sp. DEF: " + specialDefense.getBase() + " | SPD: " + speed.getBase(); 
		
		return str;
	}
}
