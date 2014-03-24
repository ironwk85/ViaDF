package com.example.viadf;

import android.os.Bundle;
import android.app.Activity;
import android.view.Menu;

public class RoutesListDescriptionActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_routes_list_description);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.routes_list_description, menu);
		return true;
	}

}
