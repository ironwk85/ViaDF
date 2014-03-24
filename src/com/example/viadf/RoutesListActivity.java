package com.example.viadf;

import java.util.ArrayList;
import java.util.HashMap;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import com.example.viadf.network.RetrieveJson;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.GooglePlayServicesClient;

import android.os.Bundle;
import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.IntentSender;
import android.support.v4.app.FragmentActivity;
import android.view.Menu;
import android.view.Window;
import android.widget.ListAdapter;
import android.widget.ListView;

public class RoutesListActivity extends FragmentActivity implements
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
		setContentView(R.layout.activity_routes_list);
		getActionBar().setBackgroundDrawable(getResources().getDrawable(R.drawable.actionbar_bg));
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.routes_list, menu);
		return true;
	}
	
	@Override
    protected void onStart() {
        super.onStart();

        Intent intent = getIntent();
	    int id = Integer.parseInt(intent.getStringExtra("id"));
        RetrieveJson json = new RetrieveJson();
        JSONObject jo = new JSONObject();
        switch(id){
        case 2:
        	jo = json.makeHttpRequest("http://www.segitesm.com.mx/php/museos.php", "GET", null);
        	break;
    	default:
    		break;
        }
        ArrayList<HashMap<String, String>> elements = new ArrayList<HashMap<String, String>>();
        try{
        	if (jo.toString()!= null && jo.toString().length()>0){
            	JSONArray contentJ = jo.getJSONArray("museos");
            	for (int i=0; i<contentJ.length(); i++){
            		JSONObject item = contentJ.getJSONObject(i);
            		HashMap<String, String> map = new HashMap<String, String>();
                	map.put("id", item.getString("id"));
                	map.put("title",item.getString("nombre"));
                	map.put("thumb_url", R.drawable.rutas_+"");
                	map.put("artist", item.getString("descripcion"));
                	elements.add(map);
     	        }
            	list = (ListView)findViewById(R.id.list_routesList);
        		adapter = new com.example.viadf.adapters.ListAdapter(this, elements, this);
        		list.setAdapter(adapter);
        	}
        }
        catch (JSONException e){
        	e.printStackTrace();
        }
    }

}
