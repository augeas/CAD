package com.elusinian.cad;

import java.util.ArrayList;

import android.content.Context;
import android.opengl.GLSurfaceView;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.SurfaceHolder;

import com.elusinian.cad.CArenderer;

import com.elusinian.cad.CAres;

public class CAsurface extends GLSurfaceView {

	private ArrayList<CAres> resolutions;
	
	public CAsurface(Context context,AttributeSet attr) {
	        super(context,attr);
	        mRenderer = new CArenderer();
	        setRenderer(mRenderer);
		    
	        build_res();
	        
	    }
	
	public boolean onTouchEvent(final MotionEvent event) {
		queueEvent(new Runnable(){
	            public void run() {
	                mRenderer.setColor(event.getX() / getWidth(),
	                        event.getY() / getHeight(), 1.0f);
	            }});
	            return true;
	        }

	        CArenderer mRenderer;
	
	   public void do_ca() {
		   
		   mRenderer.setColor(0.0f, 0.0f, 0.0f);
	   }
		   
	   public void build_res()
	   {
		   int cWidth,cHeight,size,loop,w,h,xoff,yoff;
		   CAres newRes;
		   
		   resolutions = new ArrayList<CAres>();
		   
		   cWidth = getWidth(); cHeight = getHeight();
		   		   
		   size = 1;
		   
		   for (loop=0;loop<4;loop++) {
			   w = cWidth / size; h = cHeight / size;
			   xoff = (cWidth - size*w) / 2;
			   yoff = (cHeight - size*h) / 2;
		
			   newRes = new CAres(w, h, xoff, yoff, size);
			   
			   resolutions.add(newRes);
			   
			   size *= 2;
			   			   
		   }
		   

	   }
	   
	public ArrayList get_res_labels() {
		
		ArrayList<String> labels = new ArrayList<String>();
		
		for (CAres res: resolutions) {
			labels.add(res.get_label());			
		}
		return labels;
		
	}
	   
}
	        
	
