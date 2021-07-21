package com.jsnyder17.battlemonsters.battle;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.*;

import com.jsnyder17.battlemonsters.monster.Monster;

public class BattleView extends JPanel implements ActionListener {
	private Timer updateTimer;
	private Monster m1;
	private Monster m2;
	private BufferedImage m1Image;
	private BufferedImage m2Image;
	private String output;
	private Font nameFont;
	private Font smallFont;
	
	public BattleView() {
		updateTimer = new Timer(5, this);
		m1 = new Monster();
		m2 = new Monster();
		
		this.setLayout(null);
		
		output = "";
		
		nameFont = new Font("Arial", 3, 40);
		smallFont = new Font("Arial", 1, 20);
	}
	
	public void setM1(Monster m1) {
		this.m1 = m1;
		
		try {
			m1Image = ImageIO.read(new File(m1.getImageBackPath()));
		} 
		catch (IOException e) {
			e.printStackTrace();
		}
	}
	public void setM2(Monster m2) {
		this.m2 = m2;
		
		try {
			m2Image = ImageIO.read(new File(m2.getImageFrontPath()));
		} 
		catch (IOException e) {
			e.printStackTrace();
		}
	}
	public void setOutput(String output) {
		this.output = output;
	}
	
	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		
		g.setColor(Color.BLACK);
		
		System.out.println("Drawing from " + m1.getImageBackPath() + " ... ");
		g.drawImage(m1Image, 100, 400, this);
		g.setFont(nameFont);
		g.drawString(m1.getName(), 400, 400);
		g.setFont(smallFont);
		g.drawString("Lv: " + m1.getLevel(), 400, 430);
		g.drawString(" HP: ", 410, 450);
		
		if (m1.getHp().getFinalValue() > 0) {
			g.setColor(Color.GREEN);
			g.fillRect(450, 435, Math.round(Math.round(m1.getHp().getFinalValue())), 20);
		}
		
		g.setColor(Color.BLACK);
		g.drawImage(m2Image, 400, 100, this);
		g.setFont(nameFont);
		g.drawString(m2.getName(), 100, 100);
		g.setFont(smallFont);
		g.drawString("Lv: " + m2.getLevel() + " HP: ", 100, 150);
		g.setColor(Color.GREEN);
		
		if (m2.getHp().getFinalValue() > 0) {
			g.setColor(Color.GREEN);
			g.fillRect(110, 170, Math.round(Math.round(m2.getHp().getFinalValue())), 20);
		}
		
		g.setColor(Color.BLACK);
		g.drawString(output, 200, 500);
		
		updateTimer.start();
	}
	
	public void refresh() {
		revalidate();
		repaint();
	}
	
	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == updateTimer) {
			refresh();
		}
	}
}
