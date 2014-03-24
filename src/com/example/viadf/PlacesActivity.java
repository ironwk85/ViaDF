package com.example.viadf;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.view.Window;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.GridView;

import com.example.viadf.adapters.ImageAdapter;

public class PlacesActivity extends Activity {

	@SuppressLint("NewApi")
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		getWindow().requestFeature(Window.FEATURE_ACTION_BAR_OVERLAY);
		setContentView(R.layout.activity_places);
		getActionBar().setBackgroundDrawable(getResources().getDrawable(R.drawable.actionbar_bg));
		
		final Integer[] mThumbs = {
			R.drawable.transporte, R.drawable.ecobici,
	        R.drawable.hotel, R.drawable.mercdo,
	        R.drawable.embajada, R.drawable.sitiosinteres,
	        R.drawable.estaciomamientos, R.drawable.hospitales
	    };
        
        GridView mainGridView = (GridView) findViewById(R.id.placesGridView);
        mainGridView.setAdapter(new ImageAdapter(this, mThumbs));
        
        mainGridView.setOnItemClickListener(new OnItemClickListener() {
            public void onItemClick(AdapterView<?> parent, View v, int position, long id) {
                //Toast.makeText(MainActivity.this, "" + position, Toast.LENGTH_SHORT).show();
            	switch (position){
            	case 0:
            		Intent intentTransporte = new Intent(PlacesActivity.this, MapActivity.class);
            		intentTransporte.putExtra("transporte", "true");
            		intentTransporte.putExtra("ecobici", "false");
            		intentTransporte.putExtra("hoteles", "false");
            		intentTransporte.putExtra("mercados", "false");
            		intentTransporte.putExtra("embajadas", "false");
            		intentTransporte.putExtra("interes", "false");
            		intentTransporte.putExtra("estacionamientos", "false");
            		intentTransporte.putExtra("hospitales", "false");
            		startActivity(intentTransporte);
            		break;
            	case 1:
            		Intent intentEcobici = new Intent(PlacesActivity.this, MapActivity.class);
            		intentEcobici.putExtra("transporte", "false");
            		intentEcobici.putExtra("ecobici", "true");
            		intentEcobici.putExtra("hoteles", "false");
            		intentEcobici.putExtra("mercados", "false");
            		intentEcobici.putExtra("embajadas", "false");
            		intentEcobici.putExtra("interes", "false");
            		intentEcobici.putExtra("estacionamientos", "false");
            		intentEcobici.putExtra("hospitales", "false");
            		startActivity(intentEcobici);
            		break;
            	case 2:
            		Intent intentHoteles = new Intent(PlacesActivity.this, MapActivity.class);
            		intentHoteles.putExtra("transporte", "false");
            		intentHoteles.putExtra("ecobici", "false");
            		intentHoteles.putExtra("hoteles", "true");
            		intentHoteles.putExtra("mercados", "false");
            		intentHoteles.putExtra("embajadas", "false");
            		intentHoteles.putExtra("interes", "false");
            		intentHoteles.putExtra("estacionamientos", "false");
            		intentHoteles.putExtra("hospitales", "false");
            		startActivity(intentHoteles);
            		break;
            	case 3:
            		Intent intentMercados = new Intent(PlacesActivity.this, MapActivity.class);
            		intentMercados.putExtra("transporte", "false");
            		intentMercados.putExtra("ecobici", "false");
            		intentMercados.putExtra("hoteles", "false");
            		intentMercados.putExtra("mercados", "true");
            		intentMercados.putExtra("embajadas", "false");
            		intentMercados.putExtra("interes", "false");
            		intentMercados.putExtra("estacionamientos", "false");
            		intentMercados.putExtra("hospitales", "false");
            		startActivity(intentMercados);
            		break;
            	case 4:
            		Intent intentEmbajadas = new Intent(PlacesActivity.this, MapActivity.class);
            		intentEmbajadas.putExtra("transporte", "false");
            		intentEmbajadas.putExtra("ecobici", "false");
            		intentEmbajadas.putExtra("hoteles", "false");
            		intentEmbajadas.putExtra("mercados", "false");
            		intentEmbajadas.putExtra("embajadas", "true");
            		intentEmbajadas.putExtra("interes", "false");
            		intentEmbajadas.putExtra("estacionamientos", "false");
            		intentEmbajadas.putExtra("hospitales", "false");
            		startActivity(intentEmbajadas);
            		break;
            	case 5:
            		Intent intentInteres = new Intent(PlacesActivity.this, MapActivity.class);
            		intentInteres.putExtra("transporte", "false");
            		intentInteres.putExtra("ecobici", "false");
            		intentInteres.putExtra("hoteles", "false");
            		intentInteres.putExtra("mercados", "false");
            		intentInteres.putExtra("embajadas", "false");
            		intentInteres.putExtra("interes", "true");
            		intentInteres.putExtra("estacionamientos", "false");
            		intentInteres.putExtra("hospitales", "false");
            		startActivity(intentInteres);
            		break;
            	case 6:
            		Intent intentEstacionamientos = new Intent(PlacesActivity.this, MapActivity.class);
            		intentEstacionamientos.putExtra("transporte", "false");
            		intentEstacionamientos.putExtra("ecobici", "false");
            		intentEstacionamientos.putExtra("hoteles", "false");
            		intentEstacionamientos.putExtra("mercados", "false");
            		intentEstacionamientos.putExtra("embajadas", "false");
            		intentEstacionamientos.putExtra("interes", "false");
            		intentEstacionamientos.putExtra("estacionamientos", "true");
            		intentEstacionamientos.putExtra("hospitales", "false");
            		startActivity(intentEstacionamientos);
            		break;
            	case 7:
            		Intent intentHospitales = new Intent(PlacesActivity.this, MapActivity.class);
            		intentHospitales.putExtra("transporte", "false");
            		intentHospitales.putExtra("ecobici", "false");
            		intentHospitales.putExtra("hoteles", "false");
            		intentHospitales.putExtra("mercados", "false");
            		intentHospitales.putExtra("embajadas", "false");
            		intentHospitales.putExtra("interes", "false");
            		intentHospitales.putExtra("estacionamientos", "false");
            		intentHospitales.putExtra("hospitales", "true");
            		startActivity(intentHospitales);
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
		getMenuInflater().inflate(R.menu.places, menu);
		return true;
	}

}
