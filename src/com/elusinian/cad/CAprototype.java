package com.elusinian.cad;

import java.util.ArrayList;

import android.widget.LinearLayout;

public class CAprototype {

	private String ca_name;
	private String ca_id; 
	private ArrayList<CApar> pars;
	
	public CAprototype(String id, String name) {
		ca_name = name;
		ca_id = id;
		
		setPars(new ArrayList<CApar>());
		
	}
	
	public String getCa_name() {
		return ca_name;
	}

	public String getCa_id() {
		return ca_id;
	}
	
	public void addPar (CApar par){
		pars.add(par);
	}

	public void setPars(ArrayList<CApar> pars) {
		this.pars = pars;
	}
	
	public ArrayList<CApar> getPars() {
		return pars;
	}
		
	
}
