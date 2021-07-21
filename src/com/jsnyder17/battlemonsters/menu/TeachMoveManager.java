package com.jsnyder17.battlemonsters.menu;

import java.util.ArrayList;

public class TeachMoveManager {
	public void execute(ArrayList<String> command, DataContainer dc) {
		if (command.size() == 3) {
			int monsterIndex = dc.findPlayerMonster(command.get(1));
			int moveIndex = dc.findMove(command.get(2));
			
			// Check if the pokemon specified exists in the player's party 
			if (monsterIndex != -1) {
				// Check if the move specified exists in the data
				if (moveIndex != -1) {
					dc.addPlayerMonsterMove(monsterIndex, moveIndex);
					
					System.out.println("Taught successfully! ");
				}
				else {
					System.out.println("The specified move does not exist. ");
				}
			}
			else {
				System.out.println("The specified monster is not in your party. ");
			}
		}
		else {
			System.out.println("Invalid input. Enter 'teach <pokemon name> <move name>' without the <>. ");
		}
	}
}
