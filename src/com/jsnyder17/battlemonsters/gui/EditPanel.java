package com.jsnyder17.battlemonsters.gui;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.*;

import com.jsnyder17.battlemonsters.monster.Monster;
import com.jsnyder17.battlemonsters.move.Move;

public class EditPanel extends JPanel implements ActionListener {
	private JList list;
	private JPanel statsPanel;
	private JButton editButton;
	private JButton addButton;
	private JButton removeButton;
	private JButton backButton;
	private ArrayList<JComboBox> selectMoveBoxes;
	private ArrayList<Monster> playerMonsters;
	private ArrayList<Move> moves;
	
	public EditPanel(ArrayList<Monster> playerMonsters, ArrayList<Move> moves) {
		this.playerMonsters = playerMonsters;
		this.moves = moves;
		
		initializeComponents();
	}
	
	private void initializeComponents() {
		list = new JList(playerMonsters.toArray());
		
		// Selection area 
		editButton = new JButton("Edit Pokemon");
		addButton = new JButton("Add Pokemon");
		removeButton = new JButton("Remove Pokemon");
		backButton = new JButton("Back");
		
		// Stat editing area
		statsPanel = new JPanel();
		
		selectMoveBoxes = new ArrayList<JComboBox>();
		
		for (JComboBox jcb : selectMoveBoxes) {
			jcb = new JComboBox(moves.toArray());
		}
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		if (e.getSource() == list) {
			updateStatsPanel();
		}
	}
	
	private void updateStatsPanel() {
		
	}
}
