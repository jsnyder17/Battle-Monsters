package com.jsnyder17.battlemonsters.menu;

import java.util.ArrayList;
import java.util.StringTokenizer;

public class CommandSplitter {
	public ArrayList<String> formatInput(String input) {
		ArrayList<String> command = new ArrayList<String>();
		StringTokenizer st = new StringTokenizer(input, " ");
		
		while (st.hasMoreTokens()) {
			command.add(st.nextToken());
		}
		
		return command;
	}
}
