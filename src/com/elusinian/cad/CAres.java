package com.elusinian.cad;

public class CAres {

	private int width;
	private int height;
	private int xoff;
	private int yoff;
	private int size;
	
	public CAres(int w, int h, int x, int y, int sz) {
		width = w; height = h; xoff = x; yoff = y; size = sz;		
	}
	
	public String get_label() {				
		return String.valueOf(width)+" x " + String.valueOf(height);			
	}
	
}
