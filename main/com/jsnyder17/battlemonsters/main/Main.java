package com.jsnyder17.battlemonsters.main;

import com.jsnyder17.battlemonsters.menu.DataContainer;
import com.jsnyder17.battlemonsters.menu.GameManager;

public class Main {
	public static void main(String[] args) {
		DataContainer dc = new DataContainer();
		dc.initializeData();
		
		if (dc.getMonsters().size() < 0 || dc.getMoves().size() < 0) {
			if (dc.getMonsters().size() < 0) {
				System.out.println("No monsters found. ");
			}
			else {
				System.out.println("No moves found. ");
			}
		}
		else {
			//initializeArray();
			GameManager mm = new GameManager(dc);
			
			mm.start();
		}
		
		System.out.println("Exiting ... ");
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	/*
	public static void initializeArray() {
		double types[][] = new double[17][17];
		
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
		
		for (int i = 0; i < 16; i++) {
			for (int j = 0; j < 16; j++) {
				System.out.print(types[i][j] + " ");
			}
			System.out.println();
		}
	}
	*/
}
