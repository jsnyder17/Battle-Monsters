package com.jsnyder17.battlemonsters.derby;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.StringTokenizer;

import com.jsnyder17.battlemonsters.monster.Monster;
import com.jsnyder17.battlemonsters.move.Move;
import com.jsnyder17.battlemonsters.util.IntegerConverter;

public class InitialData {
	public ArrayList<Monster> getMonsters() throws IOException {
		ArrayList<Monster> monsterList = new ArrayList<Monster>();
		ReadCSV readMonsters = new ReadCSV("testmonsters.csv");
		IntegerConverter ic = new IntegerConverter();
		String typeString = "";
		StringTokenizer st;
		
		try {
			Integer monsterId = 1;
			
			while (true) {
				List<String> tuple = readMonsters.next();
				if (tuple == null) {
					break;
				}
				Iterator<String> i = tuple.iterator();
				Monster monster = new Monster();
				
				monster.setId(monsterId++);
				monster.setName(i.next());
				
				// Get types
				typeString = i.next();
				
				// Handle double types 
				if (typeString.contains(" ")) {
					st = new StringTokenizer(typeString, " ");
						
					monster.setType1(ic.getTypeInt(st.nextToken()));
					if (st.hasMoreTokens()) {
						monster.setType2(ic.getTypeInt(st.nextToken()));
					}
				}
				else {
					monster.setType1(ic.getTypeInt(typeString));
				}
				
				monster.getHp().setBase(Integer.parseInt(i.next()));
				monster.getAttack().setBase(Integer.parseInt(i.next()));
				monster.getDefense().setBase(Integer.parseInt(i.next()));
				monster.getSpecialAttack().setBase(Integer.parseInt(i.next()));
				monster.getSpecialDefense().setBase(Integer.parseInt(i.next()));
				monster.getSpeed().setBase(Integer.parseInt(i.next()));
				monster.calculateImagePaths();
				
				monsterList.add(monster);
			}
			
			return monsterList;
		}
		finally {
			readMonsters.close();
		}
	}
	
	public ArrayList<Move> getMoves() throws IOException {
		ArrayList<Move> moveList = new ArrayList<Move>();
		ReadCSV readMoves = new ReadCSV("movedata.csv");
		String temp = "";
		IntegerConverter ic = new IntegerConverter();
		int index = 1;
		
		try {
			Integer moveId = 1;
			while (true) {
				List<String> tuple = readMoves.next();
				if (tuple == null) {
					break;
				}
				Iterator<String> i = tuple.iterator();
				Move move = new Move();
				
				move.setId(moveId++);
				move.setName(i.next());
				move.setType(ic.getTypeInt(i.next()));
				move.setCategory(ic.getCategoryInt(i.next()));
				move.setMaxPP(Integer.parseInt(i.next()));
				move.setCurrentPP(move.getMaxPP());
				move.setPower(Integer.parseInt(i.next()));
				move.setAccuracy(Integer.parseInt(i.next()));
				
				moveList.add(move);
			}
			
			return moveList;
		}
		finally {
			readMoves.close();
		}
	}
	
	public String formatString(String str) {
		String newStr = "";
		
		if (str.contains("*")) {
			newStr = str.substring(0, str.indexOf('*'));
		}
		
		return newStr;
	}
	public int formatInteger(String str) {
		int number = 0;
		
		// Return -1 if string is "-"
		if (str.contains("—")) {
			number = -1;
		}
		else {
			// Remove asterisk from string
			if (str.contains("*")) {
				str = str.substring(0, str.indexOf('*'));
			}
			
			// Remove percentage from string
			if (str.contains("%")) {
				str = str.substring(0, str.indexOf('%'));
			}
			
			number = Integer.parseInt(str);
		}
		
		return number;
 	}
}