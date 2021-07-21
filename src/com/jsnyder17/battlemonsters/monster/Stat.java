package com.jsnyder17.battlemonsters.monster;

public class Stat {
	private int base;
	private double iv;
	private double ev;
	private double finalValue;
	
	public Stat() {
		base = 0;
		iv = 0.0;
		ev = 0.0;
		finalValue = 0.0;
	}
	public Stat(Stat stat) {
		this.base = stat.base;
		this.iv = stat.iv;
		this.ev = stat.ev;
		this.finalValue = stat.finalValue;
	}
	
	public int getBase() {
		return base;
	}
	public double getIv() {
		return iv;
	}
	public double getEv() {
		return ev;
	}
	public double getFinalValue() {
		return finalValue;
	}
	
	public void setBase(int base) {
		this.base = base;
	}
	public void setIv(double iv) {
		this.iv = iv;
	}
	public void setEv(double ev) {
		this.ev = ev;
	}
	public void setFinalValue(double finalValue) {
		this.finalValue = finalValue;
	}
	
	public String toString() {
		return base + " | " + iv + " | " + ev;
	}
}
