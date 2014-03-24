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

public class MainActivity extends Activity {

    @SuppressLint("NewApi")
	@Override
    protected void onCreate(Bundle savedInstanceState) {
    	
    	final Integer[] mThumbs = {
		R.drawable.lugares, R.drawable.medalla,
        R.drawable.rutas, R.drawable.busca,
        R.drawable.configuracion	
    	};
    	
        super.onCreate(savedInstanceState);
        getWindow().requestFeature(Window.FEATURE_ACTION_BAR_OVERLAY);
        setContentView(R.layout.activity_main);
        getActionBar().setBackgroundDrawable(getResources().getDrawable(R.drawable.actionbar_bg));
        
        GridView mainGridView = (GridView) findViewById(R.id.mainGridView);
        mainGridView.setAdapter(new ImageAdapter(this, mThumbs));
        
        mainGridView.setOnItemClickListener(new OnItemClickListener() {
            public void onItemClick(AdapterView<?> parent, View v, int position, long id) {
                //Toast.makeText(MainActivity.this, "" + position, Toast.LENGTH_SHORT).show();
            	switch (position){
            	case 0:
            		Intent intentPlaces = new Intent(MainActivity.this, PlacesActivity.class);
            		startActivity(intentPlaces);
            		break;
            	case 1:
            		Intent intentAwards = new Intent(MainActivity.this, AwardsActivity.class);
            		startActivity(intentAwards);
            		break;
            	case 2:
            		Intent intentRoutes = new Intent(MainActivity.this, RoutesActivity.class);
            		startActivity(intentRoutes);
            		break;
            	case 3:
            		Intent intentSearch = new Intent(MainActivity.this, SearchActivity.class);
            		startActivity(intentSearch);
            		break;
            	case 4:
            		Intent intentSettings = new Intent(MainActivity.this, SettingsActivity.class);
            		startActivity(intentSettings);
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
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }
    
}
