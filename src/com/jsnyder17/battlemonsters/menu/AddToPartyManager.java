package com.jsnyder17.battlemonsters.menu;

import java.util.ArrayList;

public class AddToPartyManager {
	public void execute(ArrayList<String> command, DataContainer dc) {
		if (command.size() == 3) {
			// Check if the specified monster exists in the data
			int monsterIndex = dc.findMonster(command.get(1));
			
			if (monsterIndex != -1) {
				try {
					int monsterLevel = Integer.parseInt(command.get(2));
					
					if (monsterLevel <= 100 && monsterLevel > 0) {
						dc.addPlayerMonster(monsterIndex, monsterLevel);
					}
					else {
						System.out.println("Invalid input. Level must be greater than 0 and less than 101. ");
					}
				}
				catch (NumberFormatException e) {
					System.out.println("Invalid input. '" + command.get(2) + "' is not an integer. ");
				}
			}
			else {
				System.out.println("The specified pokemon does not exist. ");
			}
		}
		else {
			System.out.println("Invalid input. Enter 'add <pokemon name> <level>' without the <>. ");
		}
	}
}
