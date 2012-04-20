package com.elusinian.cad;

public class CApar {
	public enum CAparType {INTEGER, LOGICAL}
	public CAparType type;
	public String name;
	public int max;
	public int min;
	public int defval;
	public int val;

	public CApar(String nm, int mi, int mx, int df) {
		type = CAparType.INTEGER;
		name = nm;
		min = mi;
		max = mx;
		defval = df;
		val = defval;
	}
	
	public String setValue(String trial) {
	
		int newval=val;
		
		switch (type) {
			case INTEGER:
				newval = Integer.parseInt(trial);
				if (newval < min) {newval = min;}
				if (newval > max) {newval = max;}
				val = newval;
				break;
		}
			return Integer.toString(newval);
		
	}
		
	
}