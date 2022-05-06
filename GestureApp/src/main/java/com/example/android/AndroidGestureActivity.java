package com.example.android;
import java.util.ArrayList;

import android.app.Activity;
import android.content.Intent;
import android.gesture.Gesture;
import android.gesture.GestureLibraries;
import android.gesture.GestureLibrary;
import android.gesture.GestureOverlayView;
import android.gesture.GestureOverlayView.OnGesturePerformedListener;
import android.gesture.Prediction;
import android.net.Uri;
import android.os.Bundle;
import android.widget.Toast;

public class AndroidGestureActivity extends Activity implements OnGesturePerformedListener {
	GestureLibrary mLibrary;
	
	
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
        
        mLibrary = GestureLibraries.fromRawResource(this, R.raw.gestures);
       	   if (!mLibrary.load()) {
       	     finish();
           }
         
          GestureOverlayView gestures = (GestureOverlayView) findViewById(R.id.gestures);
          gestures.addOnGesturePerformedListener(this);
    }
    
    public void onGesturePerformed(GestureOverlayView overlay, Gesture gesture) {
    	   ArrayList<Prediction> predictions = mLibrary.recognize(gesture);
    		 
    	   if (predictions.size() > 0 && predictions.get(0).score > 3.5) {
    	     String result = predictions.get(0).name;
    	 
    	     if ("open".equalsIgnoreCase(result)) {
    	       Toast.makeText(this, "Opening the document: " + predictions.get(0).score, Toast.LENGTH_LONG).show();
    	     } else if ("save".equalsIgnoreCase(result)) {
    	       Toast.makeText(this, "Saving the document: " + predictions.get(0).score, Toast.LENGTH_LONG).show();
    	     }
    	       else if ("exit".equalsIgnoreCase(result) || "exit2".equalsIgnoreCase(result) ||  "exit3".equalsIgnoreCase(result) || "exit4".equalsIgnoreCase(result)){
				 Toast.makeText(this, "Goodbye" + predictions.get(0).score, Toast.LENGTH_LONG).show();
				 finish();
				 //System.exit(0);
			 }
    	       else if ("call".equalsIgnoreCase(result)) {
				 Toast.makeText(this, "Calling home" + predictions.get(0).score, Toast.LENGTH_LONG).show();
				 Intent browserIntent = new Intent(Intent.ACTION_CALL, Uri.parse("123456789"));
				 startActivity(browserIntent);
    	       }
    	       else if ("yahoo".equalsIgnoreCase(result)) {
				 Toast.makeText(this, "Going to yahoo!" + predictions.get(0).score, Toast.LENGTH_LONG).show();
				 Intent browserIntent = new Intent(Intent.ACTION_VIEW, Uri.parse("http://www.yahoo.ca"));
				 startActivity(browserIntent);
    	       }
    	}
    }
}