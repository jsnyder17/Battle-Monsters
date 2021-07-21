package com.jsnyder17.battlemonsters.util;

public class IntegerConverter {
	public String getTypeString(int type) {
		String typeString = "";
		
		switch (type) {
			case 0:
				typeString = "Normal";
				break;
			case 1:
				typeString = "Fight";
				break;
			case 2:
				typeString = "Flying";
				break;
			case 3:
				typeString = "Poison";
				break;
			case 4:
				typeString = "Ground";
				break;
			case 5:
				typeString = "Rock";
				break;
			case 6:
				typeString = "Bug";
				break;
			case 7:
				typeString = "Ghost";
				break;
			case 8:
				typeString = "Steel";
				break;
			case 9:
				typeString = "Fire";
				break;
			case 10:
				typeString = "Water";
				break;
			case 11:
				typeString = "Grass";
				break;
			case 12:
				typeString = "Electric";
				break;
			case 13:
				typeString = "Psychic";
				break;
			case 14:
				typeString = "Ice";
				break;
			case 15:
				typeString = "Dragon";
				break;
			case 16:
				typeString = "Dark";
				break;
			case 17:
				typeString = "Fairy";
				break;
		}
		
		return typeString;
	}
	
	public int getTypeInt(String type) {
		int typeInt = 0;
		
		switch (type) {
			case "Normal":
				typeInt = 0;
				break;
			case "Fighting":
				typeInt = 1;
				break;
			case "Flying":
				typeInt = 2;
				break;
			case "Poison":
				typeInt = 3;
				break;
			case "Ground":
				typeInt = 4;
				break;
			case "Rock":
				typeInt = 5;
				break;
			case "Bug":
				typeInt = 6;
				break;
			case "Ghost":
				typeInt = 7;
				break;
			case "Steel":
				typeInt = 8;
				break;
			case "Fire":
				typeInt = 9;
				break;
			case "Water":
				typeInt = 10;
				break;
			case "Grass":
				typeInt = 11;
				break;
			case "Electric":
				typeInt = 12;
				break;
			case "Psychic":
				typeInt = 13;
				break;
			case "Ice":
				typeInt = 14;
				break;
			case "Dragon":
				typeInt = 15;
				break;
			case "Dark":
				typeInt = 16;
				break;
			case "Fairy":
				typeInt = 17;
				break;
		}
		
		return typeInt;
	}
	
	public String getCategoryString(int category) {
		String categoryString = "";
		
		switch (category) {
			case 0:
				categoryString = "Physical";
				break;
			case 1:
				categoryString = "Status";
				break;
			case 2:
				categoryString = "Special";
				break;
		}
		
		return categoryString;
	}
	
	public int getCategoryInt(String category) {
		int categoryInt = 0;
		
		switch (category) {
			case "Special":
				categoryInt = 1;
				break;
			case "Status":
				categoryInt = 2;
				break;
		}
		
		return categoryInt;
	}
}
