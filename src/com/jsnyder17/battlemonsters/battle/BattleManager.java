package com.jsnyder17.battlemonsters.battle;

import java.util.ArrayList;
import java.util.Random;

import javax.swing.JFrame;
import javax.swing.JPanel;

import com.jsnyder17.battlemonsters.monster.Monster;
import com.jsnyder17.battlemonsters.move.DamageManager;
import com.jsnyder17.battlemonsters.move.Move;

public class BattleManager {
	private ArrayList<Monster> playerMonsters;
	private ArrayList<Monster> cpuMonsters;
	private int currentPlayerMonster;
	private int currentCpuMonster;
	private int turnIndex;
	private Random rand;
	private BattleConditions battleConditions;
	private JFrame frame;
	private BattleView battleView;
	private double types[][];
	
	public BattleManager(ArrayList<Monster> playerMonsters, ArrayList<Monster> cpuMonsters) {
		this.playerMonsters = new ArrayList<Monster>();
		this.cpuMonsters = new ArrayList<Monster>();
			
		for (Monster m : playerMonsters) {
			this.playerMonsters.add(new Monster(m));
		}
		for (Monster m : cpuMonsters) {
			this.cpuMonsters.add(new Monster(m));
		}
		
		turnIndex = 0;
		rand = new Random();
		battleConditions = new BattleConditions();
		
		frame = new JFrame();
		frame.setSize(700, 650);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		battleView = new BattleView();
		
		types = new double[17][17];
		
		initializeArray();
		
		frame.add(battleView);
	}
	
	private void initializeArray() {
		for (int i = 0; i < 17; i++) {
			for (int j = 0; j < 17; j++) {
				switch(i) {
					case 0:
						if (j == 5 || j == 8) {
							types[i][j] = 0.5;
						}
						else if (j == 7) {
							types[i][j] = 0;
						}
						else {
							types[i][j] = 1;
						}
						break;
						
					case 1:
						if (j == 0 || j == 5 || j == 8 || j == 14 || j == 16) {
							types[i][j] = 2;
						}
						else if (j == 2 || j == 3 || j == 6 || j == 13) {
							types[i][j] = 0.5;
						}
						else if (j == 7) {
							types[i][j] = 0;
						}
						else {
							types[i][j] = 1;
						}
						break;
						
					case 2:
						if (j == 1 || j == 6 || j == 11) {
							types[i][j] = 2;
						}
						else if (j == 5 || j == 8 || j == 12) {
							types[i][j] = 0.5;
						}
						else {
							types[i][j] = 1;
						}
						break;
					
					case 3:
						if (j == 11) {
							types[i][j] = 2;
						}
						else if (j == 3 || j == 4 || j == 5 || j == 7) {
							types[i][j] = 0.5;
						}
						else if (j == 8) {
							types[i][j] = 0;
						}
						else {
							types[i][j] = 1;
						}
						break;
					
					case 4:
						if (j == 3 || j == 5 || j == 8 || j == 9 || j == 12) {
							types[i][j] = 2;
						}
						else if (j == 6 || j == 11) {
							types[i][j] = 0.5;
						}
						else if (j == 2) {
							types[i][j] = 0;
						}
						else {
							types[i][j] = 1;
						}
						break;
					
					case 5:
						if (j == 2 || j == 6 || j == 9 || j == 14) {
							types[i][j] = 2;
						}
						else if (j == 1 || j == 4 || j == 8) {
							types[i][j] = 0.5;
						}
						else {
							types[i][j] = 1;
						}
						break;
						
					case 6:
						if (j == 11 || j == 13 || j == 16) {
							types[i][j] = 2;
						}
						else if (j == 1 || j == 2 || j == 3 || j == 7 || j == 8 || j == 9) {
							types[i][j] = 0.5;
						}
						else {
							types[i][j] = 1;
						}
						break;
					
					case 7:
						if (j == 7 || j == 13) {
							types[i][j] = 2;
						}
						else if (j == 16) {
							types[i][j] = 0.5;
						}
						else if (j == 0) {
							types[i][j] = 0;
						}
						else {
							types[i][j] = 1;
						}
						break;
					
					case 8:
						if (j == 5 || j == 14) {
							types[i][j] = 2;
						}
						else if (j == 8 || j == 9 || j == 10 || j == 12) {
							types[i][j] = 0.5;
						}
						else {
							types[i][j] = 1;
						}
						break;
						
					case 9:
						if (j == 6 || j == 8 || j == 11 || j == 14) {
							types[i][j] = 2;
						}
						else if (j == 5 || j == 9 || j == 10 || j == 15) {
							types[i][j] = 0.5;
						}
						else {
							types[i][j] = 1;
						}
						break;
						
					case 10:
						if (j == 4 || j == 5 || j == 9) {
							types[i][j] = 2;
						}
						else if (j == 10 || j == 11 || j == 15) {
							types[i][j] = 0.5;
						}
						else {
							types[i][j] = 1;
						}
						break;
					
					case 11:
						if (j == 4 || j == 5 || j == 10) {
							types[i][j] = 2;
						}
						else if (j == 2 || j == 3 || j == 6 || j == 8 || j == 9 || j == 11 || j == 15) {
							types[i][j] = 0.5;
						}
						else {
							types[i][j] = 1;
						}
						break;
					
					case 12:
						if (j == 2 || j == 10) {
							types[i][j] = 2;
						}
						else if (j == 11 || j == 12 || j == 15) {
							types[i][j] = 0.5;
						}
						else if (j == 4) {
							types[i][j] = 0;
						}
						else {
							types[i][j] = 1;
						}
						break;
					
					case 13:
						if (j == 1 || j == 3) {
							types[i][j] = 2;
						}
						else if (j == 8 || j == 13) {
							types[i][j] = 0.5;
						}
						else if (j == 16) {
							types[i][j] = 0;
						}
						else {
							types[i][j] = 1;
						}
						break;
						
					case 14:
						if (j == 2 || j == 4 || j == 11 || j == 15) {
							types[i][j] = 2;
						}
						else if (j == 8 || j == 9 || j == 10 || j == 14) {
							types[i][j] = 0.5;
						}
						else {
							types[i][j] = 1;
						}
						break;
						
					case 15:
						if (j == 15) {
							types[i][j] = 2;
						}
						else if (j == 8) {
							types[i][j] = 0.5;
						}
						else {
							types[i][j] = 1;
						}
						break;
						
					case 16:
						if (j == 7 || j == 13) {
							types[i][j] = 2;
						}
						else if (j == 1 || j == 16) {
							types[i][j] = 0.5;
						}
						else {
							types[i][j] = 1;
						}
						break;
				}
			}
		}
	}
	
	public void start() {
		System.out.println("Battle start! ");
		
		frame.setVisible(true);
		
		battleLoop();
	}
	
	public void battleLoop() {
		boolean end = false;
		int turnCount = 0;
		Monster sender = null;
		Monster receiver = null;
		
		battleView.setM1(playerMonsters.get(currentPlayerMonster));
		battleView.setM2(cpuMonsters.get(currentCpuMonster));
		
		while (!end) {
			if (turnIndex == 0) {
				sender = playerMonsters.get(currentPlayerMonster);
				receiver = cpuMonsters.get(currentCpuMonster);
			}
			else {
				sender = cpuMonsters.get(currentCpuMonster);
				receiver = playerMonsters.get(currentPlayerMonster);
			}
			
			executeTurn(sender, receiver);
			
			System.out.println();
			
			boolean playerFaint = checkFaintedStatus(playerMonsters.get(currentPlayerMonster));
			boolean cpuFaint = checkFaintedStatus(cpuMonsters.get(currentCpuMonster));
			
			if (playerFaint || cpuFaint) {
				if (playerFaint) {
					playerMonsters.remove(playerMonsters.get(currentPlayerMonster));
				}
				else {
					cpuMonsters.remove(cpuMonsters.get(currentCpuMonster));
				}
				
				if (playerMonsters.size() == 0 || cpuMonsters.size() == 0) {
					if (playerFaint) {
						battleView.setOutput("You blacked out! ");
					}
					else {
						battleView.setOutput("You defeated CPU! ");
					}
					
					end = true;
				}
				else {
					if (playerFaint) {
						currentPlayerMonster = rand.nextInt(playerMonsters.size());
						
						battleView.setOutput("You sent out " + playerMonsters.get(currentPlayerMonster).getName());
						
						sleep();
						
						battleView.setM1(playerMonsters.get(currentPlayerMonster));
					}
					else {
						currentCpuMonster = rand.nextInt(cpuMonsters.size());
						
						battleView.setOutput("CPU sent out " + cpuMonsters.get(currentCpuMonster).getName());
						
						sleep();
						
						battleView.setM2(cpuMonsters.get(currentCpuMonster));
					}
				}
			}
			
			switchTurn();
			
			turnCount += 1;
		}
		
		System.out.println("Battle end after (" + turnCount + ") turns. ");
		
		frame.dispose();
	}
	
	private void executeTurn(Monster sender, Monster receiver) {
		Move move = sender.getMoves().get(rand.nextInt(sender.getMoves().size()));
		
		battleView.setOutput(sender.getName() + " used " + move.getName() + "! ");
		sleep();
		
		// Evaluate move type
		if (move.getCategory() == 0) {
			double damage = 0.0;
			
			DamageManager dm = new DamageManager(move);
			
			damage = dm.calculateDamage(sender, receiver, types, battleConditions);
			sleep();
			
			dm.damage(receiver, damage);
			
			if (dm.getCritical() == 2) {
				battleView.setOutput("Critical hit! ");
				sleep();
			}
			
			if (dm.getType() > 1) {
				battleView.setOutput("It's super effective! ");
				sleep();
			}
			else if (dm.getType() < 1) {
				battleView.setOutput("It's not very effective ... ");
				sleep();
			}
		}
	}
	
	private void switchTurn() {
		if (turnIndex == 0) { 
			turnIndex = 1;
		}
		else {
			turnIndex = 0;
		}
	}
	
	private boolean checkFaintedStatus(Monster monster) {
		boolean fainted = false;
		
		if (monster.getHp().getFinalValue() <= 0) {
			battleView.setOutput(monster.getName() + " fainted! ");

			fainted = true;
			
			sleep();
		}

		return fainted;
	}
	
	private void sleep() {
		try {
			Thread.sleep(1000);
		} 
		catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
}
