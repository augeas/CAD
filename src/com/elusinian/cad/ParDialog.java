package com.elusinian.cad;

import java.util.ArrayList;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.view.KeyEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.View.OnKeyListener;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.elusinian.cad.CApar;

public class ParDialog {
	private Context appContext;
	private ArrayList<CApar> parameters;
	private int size;
	private LinearLayout[] parViews;
	private TextView[] parText;
	private EditText[] parFields;
	private LinearLayout layout;
	
	public ParDialog(Context cntxt, ArrayList<CApar> pars) {

		parameters = pars;
		appContext = cntxt;
		
		layout = new LinearLayout(appContext);

		
		size = parameters.size();
		
		parViews = new LinearLayout[5];
		parText = new TextView[5];
		parFields = new EditText[5];
		
		populate();
		
//        AlertDialog.Builder parBuilder = new AlertDialog.Builder(appContext); 
//        AlertDialog caParDialog = parBuilder.create();
		
	}
	
	public LinearLayout getLayout() {
		return layout;
	}
		
	private void populate() {
			
		int i=0;
		
		for (CApar par: parameters) {
			parViews[i] = new LinearLayout(appContext);
			parViews[i].setOrientation(LinearLayout.HORIZONTAL);
			parText[i] = new TextView(appContext);
			parText[i].setText(par.name+": ");
			parViews[i].addView(parText[i]);
			parFields[i] = new EditText(appContext);
			
			parFields[i].setOnKeyListener(new OnKeyListener () {

				public boolean onKey(View v, int keyCode, KeyEvent event) {
			        // If the event is a key-down event on the "enter" button
			        if (keyCode == KeyEvent.KEYCODE_ENTER && 
			        (event.getAction() == KeyEvent.ACTION_DOWN) || event.getAction() == KeyEvent.ACTION_MULTIPLE) {
			          // Perform action on key press 
			        	onSubmit();
			        	return true;
			        }
			        return false;
			    }
			});
			
			parFields[i].setText(Integer.toString(par.val));
			parViews[i].addView(parFields[i]);
			layout.addView(parViews[i]);
			i++;
		}
		}
		
	public void onSubmit()
	{
		String trial;
		int i=0;
		for (CApar par: parameters) {
		 trial = parFields[i].getText().toString();
		 parFields[i].setText(par.setValue(trial));
		 i++;
		}
		
	}
		
}
