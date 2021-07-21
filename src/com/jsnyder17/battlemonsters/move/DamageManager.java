package com.jsnyder17.battlemonsters.move;

import java.util.Random;

import com.jsnyder17.battlemonsters.battle.BattleConditions;
import com.jsnyder17.battlemonsters.monster.Monster;
import com.jsnyder17.battlemonsters.util.IntegerConverter;

public class DamageManager {
	private Move move;
	private int critical;
	private double type;
	
	public DamageManager(Move move) {
		this.move = move;
		critical = 1;
		type = 0.0;
	}
	
	public int getCritical() {
		return critical;
	}
	public double getType() {
		return type;
	}
	
	public double calculateDamage(Monster sender, Monster receiver, double types[][], BattleConditions bc) {
		Random rand = new Random();
		
		calcCritical();
		calcType(move, receiver, types);
		double random = (1.01 - 0.85) + 0.85 * rand.nextDouble();
		int numTargets = 1;
		double stab = 1.0;
		
		if (sender.getType1() == move.getType()) {
			stab = 1.5;
		}
		
		double damage = ((((((2 * sender.getLevel())/5) + 2) * move.getPower() * (sender.getAttack().getFinalValue() / receiver.getDefense().getFinalValue())) / 50) + 2) * numTargets * 1 * critical * random * stab * type * 1;
		
		System.out.println("Dealt " + damage + " damage! ");
		
		return damage;
	}
	private void calcCritical() {
		Random rand = new Random();
		
		if ((rand.nextInt(16) + 1) == 1) {
			critical += 1;
		}
	}
	private void calcType(Move move, Monster receiver, double types[][]) {
		IntegerConverter ic = new IntegerConverter();
		
		type = types[move.getType()][receiver.getType1()];
		
		System.out.println("Effectiveness of " + ic.getTypeString(move.getType()) + " against " + ic.getTypeString(receiver.getType1()) + " is: " + type);
	}
	
	public void damage(Monster monster, double damage) {
		monster.getHp().setFinalValue((monster.getHp().getFinalValue() - damage));
	}
}
