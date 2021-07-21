package com.jsnyder17.battlemonsters.menu;

import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;

import com.jsnyder17.battlemonsters.battle.BattleManager;
import com.jsnyder17.battlemonsters.monster.Monster;

public class GameManager {
	private DataContainer dc;
	
	public GameManager(DataContainer dc) {
		this.dc = dc;
	}
	
	public void start() {
		// Populate CPU party
		populateCpuParty();
		
		// Menu loop 
		menuLoop();
	}
	
	private void menuLoop() {
		boolean end = false;
		String input = "";
		
		CommandSplitter cs = new CommandSplitter();
		Scanner scan = new Scanner(System.in);
		ArrayList<String> command = null;
		
		while (!end) {
			System.out.print("> ");
			input = scan.nextLine();
			
			command = cs.formatInput(input);
			
			end = executeCommand(command);
		}
	}
	
	private boolean executeCommand(ArrayList<String> command) {
		boolean end = false;
		
		if (command.size() > 0) {
			if (command.get(0).equals("quit") || command.get(0).equals("exit")) {
				end = true;
			}
			else if (command.get(0).equals("add")) {
				// Add pokemon to party
				AddToPartyManager atpm = new AddToPartyManager();
				
				atpm.execute(command, dc);
			}
			else if (command.get(0).equals("remove")) {
				// Remove pokemon from party 
				RemoveFromPartyManager rfpm = new RemoveFromPartyManager();
				
				rfpm.execute(command, dc);
			}
			else if (command.get(0).equals("teach")) {
				// Teach move to pokemon in party 
				TeachMoveManager tmm = new TeachMoveManager();
				
				tmm.execute(command, dc);
			}
			else if (command.get(0).equals("view")) {
				// View pokemon of either party
				ViewPartyManager vpm = new ViewPartyManager();
				
				vpm.execute(command, dc);
			}
			else if (command.get(0).equals("help")) {
				displayHelp();
			}
			else if (command.get(0).equals("battle")) {
				BattleManager bm = new BattleManager(dc.getPlayerMonsters(), dc.getCpuMonsters());
				
				bm.start();
			}
			else if (command.get(0).equals("reset")) {
				reset();
			}
		}
		else {
			System.out.println("Invalid input. For help, type 'help' ");
		}
		
		return end;
	}
	
	private void displayHelp() {
		
	}
	
	private void reset() {
		System.out.println("Resetting ... ");
		
		dc.resetData();
		dc.getCpuMonsters().clear();
		
		System.out.println("Reset complete. ");
		
		populateCpuParty();
	}
	
	private void populateCpuParty() {
		Random rand = new Random();
		
		for (int i = 0; i < 6; i++) {
			dc.addCpuMonster(rand.nextInt(dc.getMonsters().size()), rand.nextInt(100) + 1);
		}
		
		// Add moves
		for (int i = 0; i < dc.getCpuMonsters().size(); i++) {
			if (dc.getCpuMonsters().get(i).getType1() == 10) {
				dc.addCpuMonsterMove(i, 3);
			}
			else {
				dc.addCpuMonsterMove(i, rand.nextInt(dc.getMoves().size()));
			}
		}
		
		System.out.println("CPU pokemon ready. ");
	}
}
