package com.elusinian.cad;

import java.util.ArrayList;

import com.elusinian.cad.CAprototype;
import com.elusinian.cad.CApar;
import com.elusinian.cad.CAsurface;
import com.elusinian.cad.CArenderer;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.opengl.GLSurfaceView;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.TabHost;
import android.widget.AdapterView.OnItemSelectedListener;
import android.widget.TabHost.TabSpec;

public class CADActivity extends Activity {
    private ArrayList<CAprototype> caList,seed1dList,seed2dList;
    private ArrayList<String> resList;
    private String[] caNames,seed1dNames;
    private int selectedCA,selectedSeed;
    private ParDialog parDialogContent,seedDialogContent;
    private AlertDialog caParDialog,seedDialog;
    private Context appContext; 
    private CAsurface CAcanvas;
    
    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {

        setContentView(R.layout.main);
        super.onCreate(savedInstanceState);
    
        TabHost tabHost=(TabHost)findViewById(R.id.tabHost);
        tabHost.setup();
        
        TabSpec spec1=tabHost.newTabSpec("Tab 1");
        spec1.setContent(R.id.AutomatonTab);
        spec1.setIndicator("Settings");

        TabSpec spec2=tabHost.newTabSpec("Tab 2");
        spec2.setContent(R.id.CanvasTab);
        spec2.setIndicator("Canvas");
        
        tabHost.addTab(spec1);
        tabHost.addTab(spec2);
                         
        appContext = getApplicationContext();

 //       final CAsurface CAcanvas = (CAsurface) findViewById(R.id.CAimage); 
        
        caList = new ArrayList<CAprototype>();
        seed1dList = new ArrayList<CAprototype>();
        seed2dList = new ArrayList<CAprototype>();
//        resList = CAcanvas.get_res_labels();
        
        selectedCA = selectedSeed = 0;
        
        addCA("sim_1d","Simple 1-D");
        	appendPar(new CApar("Rule",0,255,30));
        	appendPar(new CApar("History",0,6,0));
        addCA("pascal","Pascal's Triangle");
        	appendPar(new CApar("Modulo",1,4096,2));
        
        add1dSeed("sin_def","Single Defects");	
        	append1dSeedPar(new CApar("Number",1,100,1));
        	append1dSeedPar(new CApar("Separation",0,100,0));
        add1dSeed("random","Random");
        	append1dSeedPar(new CApar("% Occupancy",0,100,50));
        	
        String[] caNames = new String[caList.size()];
        String[] seed1dNames = new String[seed1dList.size()];
        
        int i = 0;
        for (CAprototype ca_proto: caList){
        	caNames[i] = ca_proto.getCa_name();
        	i++;
        }
        
        i = 0;
        for (CAprototype ca_proto: seed1dList){
        	seed1dNames[i] = ca_proto.getCa_name();
        	i++;
        }
       
        Spinner autospinner = (Spinner) findViewById(R.id.AutoSpinner);

        ArrayAdapter<String> autoadapter = new ArrayAdapter<String>(this,android.R.layout.simple_spinner_item, caNames);
        autoadapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        autospinner.setAdapter(autoadapter);
        autospinner.setOnItemSelectedListener(new CAOnItemSelectedListener());
               	
        Spinner seedspinner = (Spinner) findViewById(R.id.SeedSpinner);

        ArrayAdapter<String> seed1dAdapter = new ArrayAdapter<String>(this,android.R.layout.simple_spinner_item, seed1dNames);
        seed1dAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        seedspinner.setOnItemSelectedListener(new SeedOnItemSelectedListener());   
        seedspinner.setAdapter(seed1dAdapter);
        
//        Spinner resspinner = (Spinner) findViewById(R.id.ResSpinner);
//        ArrayAdapter<String> resAdapter = new ArrayAdapter<String>(this,android.R.layout.simple_spinner_item, resList);
//        resspinner.setAdapter(resAdapter);
        
        final Button parButton = (Button) findViewById(R.id.ParameterButton);
        parButton.setOnClickListener(new OnClickListener() {
            public void onClick(View v) {
                // Perform action on clicks
            	doParDialog();
            }
        });
  
        final Button SeedParButton = (Button) findViewById(R.id.SeedParameterButton);
        SeedParButton.setOnClickListener(new OnClickListener() {
            public void onClick(View v) {

            	doSeedDialog();
            }
        });
        
        final Button DrawButton = (Button) findViewById(R.id.DrawButton);
        DrawButton.setOnClickListener(new OnClickListener() {
            public void onClick(View v) { CAcanvas.do_ca();              
            }
        });
        
    }


    private void addCA(String id, String name) {
        caList.add(new CAprototype(id,name)); 
    }
    
    private void appendPar(CApar par) {
    	caList.get(caList.size()-1).addPar(par);	
    	
    }

	public void setSelectedCA(int selected) {
		selectedCA = selected;

	}

	public int getSelectedCA() {
		return selectedCA;
	}
   
	public void setSelectedSeed(int selected) {
		selectedSeed = selected;

	}

	public class CAOnItemSelectedListener implements OnItemSelectedListener {
		
		@Override
		public void onItemSelected(AdapterView<?> parent, View view, int pos,
				long id) {
			
			setSelectedCA(pos);			
	}
	    
		@Override
		public void onNothingSelected(AdapterView<?> arg0) {
			// TODO Auto-generated method stub
			
		}
	}
	
	public class SeedOnItemSelectedListener implements OnItemSelectedListener {
		
		@Override
		public void onItemSelected(AdapterView<?> parent, View view, int pos,
				long id) {
			
			setSelectedSeed(pos);			
	}
	    
		@Override
		public void onNothingSelected(AdapterView<?> arg0) {
			// TODO Auto-generated method stub
			
		}
	}
	
    
    private void add1dSeed(String id, String name) {
        seed1dList.add(new CAprototype(id,name)); 
    }
	
    private void append1dSeedPar(CApar par) {
    	seed1dList.get(seed1dList.size()-1).addPar(par);
    }
    
    private void add2dSeed(String id, String name) {
        seed2dList.add(new CAprototype(id,name)); 
    }
	
	public void doParDialog() {
		parDialogContent = new ParDialog(appContext,caList.get(selectedCA).getPars());
        AlertDialog.Builder parBuilder = new AlertDialog.Builder(this);
        parBuilder.setView(parDialogContent.getLayout());
        parBuilder.setTitle("Set Parameters");

        parBuilder.setPositiveButton("Apply", new DialogInterface.OnClickListener() { 
            public void onClick(DialogInterface dialog, int whichButton) { 
            	parDialogContent.onSubmit();}});
         caParDialog = parBuilder.create();
         caParDialog.show();
	}
	
	public void doSeedDialog() {
		seedDialogContent = new ParDialog(appContext,seed1dList.get(selectedSeed).getPars());
        AlertDialog.Builder seedBuilder = new AlertDialog.Builder(this);
        seedBuilder.setView(seedDialogContent.getLayout());
        seedBuilder.setTitle("Seed Parameters");

        seedBuilder.setPositiveButton("Apply", new DialogInterface.OnClickListener() { 
            public void onClick(DialogInterface dialog, int whichButton) { 
            	seedDialogContent.onSubmit();}});
         seedDialog = seedBuilder.create();
         seedDialog.show();
	}
	
	
	
 }    
