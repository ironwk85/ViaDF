package com.example.viadf;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.view.Window;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.TextView;
import android.widget.AdapterView.OnItemClickListener;

import com.example.viadf.adapters.ImageAdapter;

public class AwardsActivity extends Activity {

	@SuppressLint("NewApi")
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		getWindow().requestFeature(Window.FEATURE_ACTION_BAR_OVERLAY);
		setContentView(R.layout.activity_awards);
		getActionBar().setBackgroundDrawable(getResources().getDrawable(R.drawable.actionbar_bg));
		
		final Integer[] mThumbs = {
				R.drawable.medalla_cultura, R.drawable.medalla_diversion,
		        R.drawable.medalla_ecobici
		    };
	        
	        GridView mainGridView = (GridView) findViewById(R.id.awardsGridView);
	        mainGridView.setAdapter(new ImageAdapter(this, mThumbs));
	        
	        mainGridView.setOnItemClickListener(new OnItemClickListener() {
	            public void onItemClick(AdapterView<?> parent, View v, int position, long id) {
	                //Toast.makeText(MainActivity.this, "" + position, Toast.LENGTH_SHORT).show();
	            	//final TextView tv = (TextView) findViewById(R.id.leyenda);
	            	switch (position){
	            	case 0:
	            		MedalDialogActivity cdd=new MedalDialogActivity(AwardsActivity.this,1);
	            		//tv.setText("Felicidades, ¡has ganado la insignia historiador!");
	        	        cdd.show(); 
	            		break;
	            	case 1:
	            		MedalDialogActivity cdd2=new MedalDialogActivity(AwardsActivity.this,2);
	            		//tv.setText("!Ganaste la medalla: Divertido! Comparte esto con tus amigos.");
	            		cdd2.show(); 
	            		break;
	            	case 2:
	            		MedalDialogActivity cdd3=new MedalDialogActivity(AwardsActivity.this,3);
	            		//tv.setText("Eres todo un ciclista, has recorrido 5 km en un día en tu bici, ¡reta a tus amigos!");
	            		cdd3.show(); 
	            		break;
	        		default:
	        			break;
	            	}
	            }
	        });
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.awards, menu);
		return true;
	}

}
