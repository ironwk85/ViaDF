package com.example.viadf;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.widget.TextView;

public class DetailsActivity extends Activity {
	
	private TextView tvTitle;
	private TextView tvArtist;
	private TextView tvDuration;
	private TextView tvColonia;
	private TextView tvSecundaria;
	private TextView tvDelegacion;
	String tipo;
	
	@Override
    protected void onStart() {
        super.onStart();
        
        Intent intent = getIntent();
		tipo = intent.getStringExtra("tipo");
		
		if (tipo.compareTo("ecobici") == 0){
			String id = intent.getStringExtra("id");
		    String title = intent.getStringExtra("title");
		    String artist = intent.getStringExtra("artist");
		    String duration = intent.getStringExtra("duration");
		    String thumb_url = intent.getStringExtra("thumb_url");
		    String colonia = intent.getStringExtra("colonia");
		    String secundaria = intent.getStringExtra("secundaria");
		    String delegacion = intent.getStringExtra("delegacion");
		    
		    tvTitle = (TextView)findViewById(R.id.tv1);
		    tvTitle.setText(title);
		    
		    tvArtist = (TextView)findViewById(R.id.tv2);
		    tvArtist.setText("Calle y referencia: "+artist);
		    
		    tvSecundaria = (TextView)findViewById(R.id.tv3);
		    tvSecundaria.setText("Calle secundaria: "+secundaria);
		    
		    tvColonia = (TextView)findViewById(R.id.tv4);
		    tvColonia.setText("Colonia: "+colonia);
		    
		    tvDuration = (TextView)findViewById(R.id.tv5);
		    tvDuration.setText("Delegación: "+duration);
		}
		else if (tipo.compareTo("true") == 0){
			
		}
		else if (tipo.compareTo("true") == 0){
			
		}
		else if (tipo.compareTo("true") == 0){
			
		}
		else if (tipo.compareTo("true") == 0){
			
		}
		else if (tipo.compareTo("true") == 0){
			
		}
		else if (tipo.compareTo("true") == 0){
			
		}
		else if (tipo.compareTo("true") == 0){
			
		}
	}

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_details);
		
		
	    //Toast.makeText(DetailsActivity.this, "" + "---"+message, Toast.LENGTH_SHORT).show();
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.details, menu);
		return true;
	}

}
