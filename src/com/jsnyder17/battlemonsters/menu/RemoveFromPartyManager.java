package com.jsnyder17.battlemonsters.menu;

import java.util.ArrayList;

public class RemoveFromPartyManager {
	public void execute(ArrayList<String> command, DataContainer dc) {
		if (command.size() == 2) {
			// Ensure the specified pokemon exists in the player's party
			int index = dc.findPlayerMonster(command.get(1));
			
			if (index != -1) {
				dc.removePlayerMonster(index);
			}
			else {
				System.out.println("The specified pokemon is not in your party. ");
			}
		}
		else {
			System.out.println("Invalid input. Enter 'remove <pokemon name>' without the <>. ");
		}
	}
}
