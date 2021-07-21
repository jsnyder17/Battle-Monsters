package com.jsnyder17.battlemonsters.menu;

import java.util.ArrayList;

import com.jsnyder17.battlemonsters.monster.Monster;
import com.jsnyder17.battlemonsters.move.Move;

public class ViewPartyManager {
	public void execute(ArrayList<String> command, DataContainer dc) {
		if (command.size() > 1) {
			if (command.get(1).equals("all")) {
				if (command.size() > 2) {
					if (command.get(2).equals("moves") || command.get(2).equals("pokemon")) {
						boolean detailed = false;
						
						if (command.size() > 3) {
							if (command.get(3).equals("detailed")) {
								detailed = true;
							}
							else {
								System.out.println("Invalid input. ");
							}
						}
						
						if (command.get(2).equals("moves")) {
							viewMoves(dc, detailed);
						}
						else {
							viewMonsters(dc.getMonsters(), detailed);
						}
					}
					else {
						System.out.println("Invalid input. ");
					}
				}
				else {
					System.out.println("Invalid input. ");
				}
			}
			
			
			
			else if (command.get(1).equals("my")) {
				if (command.size() > 2) {
					if (command.get(2).equals("pokemon")) {
						boolean detailed = false;
						
						if (command.size() > 3) {
							if (command.get(3).equals("detailed")) {
								detailed = true;
							}
							else {
								System.out.println("Invalid input. ");
							}
						}
						viewMonsters(dc.getPlayerMonsters(), detailed);
					}
					else {
						System.out.println("Invalid input. ");
					}
				}
				else {
					System.out.println("Invalid input. ");
				}
			}
			
			
			
			else if (command.get(1).equals("cpu")) {
				if (command.size() > 2) {
					if (command.get(2).equals("pokemon")) {
						boolean detailed = false;
						
						if (command.size() > 3) {
							if (command.get(3).equals("detailed")) {
								detailed = true;
							}
							else {
								System.out.println("Invalid input. ");
							}
						}
						viewMonsters(dc.getCpuMonsters(), detailed);
					}
					else {
						System.out.println("Invalid input. ");
					}
				}
				else {
					System.out.println("Invalid input. ");
				}
			}
			else {
				System.out.println("Invalid input. ");
			}
		}
		else {
			System.out.println("Invalid input. ");
		}
	}
	
	public void viewMonsters(ArrayList<Monster> monsters, boolean detailed) {
		System.out.println("====== MONSTERS ======\n");
		
		if (detailed) {
			for (Monster m : monsters) {
				System.out.println("* " + m.displayBaseStats());
				System.out.println();
			}
		}
		else {
			for (Monster m : monsters) {
				System.out.println("* " + m.getName());
				System.out.println();
			}
		}
	}
	
	public void viewMoves(DataContainer dc, boolean detailed) {
		System.out.println("====== MOVES ======\n");
		
		if (detailed) {
			for (Move m : dc.getMoves()) {
				System.out.println(m.toString());
				System.out.println();
			}
		}
		else {
			for (Move m : dc.getMoves()) {
				System.out.println(m.getName());
				System.out.println();
			}
		}
	}
}
