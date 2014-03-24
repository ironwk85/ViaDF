package com.example.viadf;


import java.util.ArrayList;
import java.util.HashMap;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.IntentSender;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.view.Menu;
import android.view.View;
import android.view.Window;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ListAdapter;
import android.widget.ListView;

import com.example.viadf.network.RetrieveJson;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.GooglePlayServicesClient;

public class RoutesActivity extends FragmentActivity implements
GooglePlayServicesClient.ConnectionCallbacks,
GooglePlayServicesClient.OnConnectionFailedListener {
	
	private final static int CONNECTION_FAILURE_RESOLUTION_REQUEST = 9000;
    public static final int UPDATE_INTERVAL_IN_SECONDS = 5;
   
	ListView list;
	ListAdapter adapter;
	
	@Override
    public void onConnected(Bundle dataBundle) {
	
    }
	
	@Override
	public void onDisconnected() {
		
	}
	
	@Override
    protected void onStart() {
        super.onStart();

        RetrieveJson json = new RetrieveJson();
        JSONObject jo = json.makeHttpRequest("http://www.segitesm.com.mx/php/rutas.php", "GET", null);
        ArrayList<HashMap<String, String>> elements = new ArrayList<HashMap<String, String>>();
        try{
        	if (jo.toString()!= null && jo.toString().length()>0){
            	JSONArray contentJ = jo.getJSONArray("rutas");
            	for (int i=0; i<contentJ.length(); i++){
            		JSONObject item = contentJ.getJSONObject(i);
            		HashMap<String, String> map = new HashMap<String, String>();
                	map.put("id", item.getString("id"));
                	map.put("title",item.getString("nombre"));
                	map.put("thumb_url", R.drawable.rutas_+"");
                	map.put("artist", item.getString("descripcion"));
                	elements.add(map);
     	        }
            	list = (ListView)findViewById(R.id.list_routes);
        		adapter = new com.example.viadf.adapters.ListAdapter(this, elements, this);
        		list.setAdapter(adapter);
        		list.setOnItemClickListener(new OnItemClickListener() {
                    @Override
                    public void onItemClick(AdapterView<?> parent, View view,
        					int position, long id) {
                    	HashMap<String, String> o = (HashMap<String, String>) list.getItemAtPosition(position);
                    	openRoutes(o);
                    }
                });
        	}
        }
        catch (JSONException e){
        	e.printStackTrace();
        }
    }
	
	protected void openRoutes(HashMap<String, String> item){
    	Intent intent = new Intent(RoutesActivity.this, RoutesListActivity.class);
    	intent.putExtra("id", item.get("id"));
		startActivity(intent);
    }
	
	@Override
    public void onConnectionFailed(ConnectionResult connectionResult) {
        if (connectionResult.hasResolution()) {
            try {
                connectionResult.startResolutionForResult(this,
                        CONNECTION_FAILURE_RESOLUTION_REQUEST);
            } catch (IntentSender.SendIntentException e) {
                e.printStackTrace();
            }
        } else {
            showErrorDialog(connectionResult.getErrorCode());
        }
    }
	
	@Override
    protected void onActivityResult(
            int requestCode, int resultCode, Intent data) {
        switch (requestCode) {
            case CONNECTION_FAILURE_RESOLUTION_REQUEST :
                switch (resultCode) {
                    case Activity.RESULT_OK :
                    break;
                }
        }
     }
	
	private void showErrorDialog(int errorCode) {
        new AlertDialog.Builder(this)
                .setMessage(String.valueOf(errorCode))
                .setPositiveButton(android.R.string.ok,
                        new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int id) {
                                dialog.dismiss();
                            }
                        }).show();
    }

	@SuppressLint("NewApi")
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		getWindow().requestFeature(Window.FEATURE_ACTION_BAR_OVERLAY);
		setContentView(R.layout.activity_routes);
		getActionBar().setBackgroundDrawable(getResources().getDrawable(R.drawable.actionbar_bg));
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		getMenuInflater().inflate(R.menu.routes, menu);
		return true;
	}
}
