package com.example.viadf;

import java.io.IOException;
import java.util.List;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.app.SearchManager;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.IntentSender;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.location.Location;
import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.support.v4.app.FragmentActivity;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.Window;
import android.widget.SearchView;
import android.widget.Toast;

import com.example.viadf.databases.CentrosSalud;
import com.example.viadf.databases.DatabaseHandler;
import com.example.viadf.databases.Embajada;
import com.example.viadf.databases.EstacionEcoBici;
import com.example.viadf.databases.Mercado;
import com.example.viadf.network.RetrieveJson;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.GooglePlayServicesClient;
import com.google.android.gms.common.GooglePlayServicesUtil;
import com.google.android.gms.location.LocationClient;
import com.google.android.gms.location.LocationListener;
import com.google.android.gms.location.LocationRequest;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.GoogleMap.OnMarkerClickListener;
import com.google.android.gms.maps.GoogleMap.OnMyLocationChangeListener;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.CameraPosition;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;

public class MapActivity extends FragmentActivity implements
		GooglePlayServicesClient.ConnectionCallbacks,
		GooglePlayServicesClient.OnConnectionFailedListener,
		LocationListener,
		OnMyLocationChangeListener{
	
	private static final LatLng MEXICO = new LatLng(23.6345,-102.553);
	private final static int CONNECTION_FAILURE_RESOLUTION_REQUEST = 9000;
    private static final int MILLISECONDS_PER_SECOND = 10000;
    public static final int UPDATE_INTERVAL_IN_SECONDS = 60;
    private static final long UPDATE_INTERVAL = MILLISECONDS_PER_SECOND * UPDATE_INTERVAL_IN_SECONDS;
    private static final int FASTEST_INTERVAL_IN_SECONDS = 30;
    private static final long FASTEST_INTERVAL = MILLISECONDS_PER_SECOND * FASTEST_INTERVAL_IN_SECONDS;
	
	LocationClient mLocationClient;
	LocationRequest mLocationRequest;
	GoogleMap mMap;
	boolean mUpdatesRequested = false;
	SharedPreferences mPrefs;
	Editor mEditor;
	String transporte;
	String ecobici;
	String hoteles;
	String mercados;
	String embajadas;
	String interes;
	String estacionamientos;
	String hospitales;
	
	
	// Define the callback method that receives location updates
    @Override
    public void onLocationChanged(Location location) {
        // Report to the UI that the location was updated
        //String msg = "Updated Location: " +
          //      Double.toString(location.getLatitude()) + "," +
            //    Double.toString(location.getLongitude());
    	Log.i("XX", "00000");
    	if (transporte.compareTo("true") == 0){
    		Log.i("XX", "11111");
        	RetrieveJson json = new RetrieveJson();
        	String latitude = String.valueOf(MEXICO.latitude);
        	String longitude = String.valueOf(MEXICO.longitude);
        	Log.i("XX", "2222222");
        	if (mLocationClient.isConnected()){
        		latitude = String.format("%.4f",mLocationClient.getLastLocation().getLatitude());
        		longitude = String.format("%.4f",mLocationClient.getLastLocation().getLongitude());
        	}
        	Log.i("XX", "333333");
            JSONObject jo = json.makeHttpRequest("http://datos.labplc.mx/georeferencia.json?&latitud=" + latitude + "&longitud=" + longitude + "&radio=1000", "GET", null);
            try{
            	//Toast.makeText(this, jo.toString(), Toast.LENGTH_SHORT).show();
            	Log.i("XX", "4");
            	if (jo.toString()!= null && jo.toString().length()>0){
	            	JSONArray contentJ = jo.getJSONObject("consulta").getJSONArray("ubicaciones");
	            	for (int i=0; i<contentJ.length(); i++){
	            		JSONObject item = contentJ.getJSONObject(i);
	     	        	mMap.addMarker(new MarkerOptions()
	     	        	        .position(new LatLng(Double.parseDouble(item.getString("latitud")), Double.parseDouble(item.getString("longitud"))))
	     	        	        .title("I."+ item.getString("nombre")).flat(false).snippet(item.getString("direccion")));
	            		//Toast.makeText(this, item.getString("nombre"), Toast.LENGTH_SHORT).show();
	            	}
            	}
            }
            catch (JSONException e){
            	e.printStackTrace();
            }
        }
    }
    
    @Override
    public void onMyLocationChange(Location location) //inherited from the interface you implemented
    {
    	Log.i("XX", "00000");
    	if (transporte.compareTo("true") == 0){
    		Log.i("XX", "11111");
        	RetrieveJson json = new RetrieveJson();
        	String latitude = String.valueOf(MEXICO.latitude);
        	String longitude = String.valueOf(MEXICO.longitude);
        	Log.i("XX", "2222222");
        	if (mLocationClient.isConnected()){
        		latitude = String.format("%.4f",mLocationClient.getLastLocation().getLatitude());
        		longitude = String.format("%.4f",mLocationClient.getLastLocation().getLongitude());
        	}
        	Log.i("LAT", latitude);
        	Log.i("LONG", longitude);
            JSONObject jo = json.makeHttpRequest("http://datos.labplc.mx/georeferencia.json?&latitud=" + latitude + "&longitud=" + longitude + "&radio=1000", "GET", null);
            try{
            	//Toast.makeText(this, jo.toString(), Toast.LENGTH_SHORT).show();
            	Log.i("XX", "4");
            	if (jo.toString()!= null && jo.toString().length()>0){
	            	JSONArray contentJ = jo.getJSONObject("consulta").getJSONArray("ubicaciones");
	            	for (int i=0; i<contentJ.length(); i++){
	            		JSONObject item = contentJ.getJSONObject(i);
	     	        	mMap.addMarker(new MarkerOptions()
	     	        	        .position(new LatLng(Double.parseDouble(item.getString("latitud")), Double.parseDouble(item.getString("longitud"))))
	     	        	        .title("I."+ item.getString("nombre")).flat(false).snippet(item.getString("direccion")));
	            		//Toast.makeText(this, item.getString("nombre"), Toast.LENGTH_SHORT).show();
	            	}
            	}
            }
            catch (JSONException e){
            	e.printStackTrace();
            }
        }
    //do your thing, the variable "location" holds everything you need
    }

	/*
     * Called by Location Services when the request to connect the
     * client finishes successfully. At this point, you can
     * request the current location or start periodic updates
     */
    @Override
    public void onConnected(Bundle dataBundle) {
        // Display the connection status
        //Toast.makeText(this, "Connected", Toast.LENGTH_SHORT).show();
        
        
    	LatLng target = new LatLng(MEXICO.latitude, MEXICO.longitude);
    	
        
        if (mLocationClient.getLastLocation() != null){
    		//Toast.makeText(this, mLocationClient.getLastLocation().getLatitude()+"",Toast.LENGTH_SHORT).show();
    		target = new LatLng(mLocationClient.getLastLocation().getLatitude(), 
    				mLocationClient.getLastLocation().getLongitude());
        }
        if (mUpdatesRequested)
			mLocationClient.requestLocationUpdates(mLocationRequest, this);
        
        mMap.animateCamera(CameraUpdateFactory.zoomIn());
        mMap.animateCamera(CameraUpdateFactory.zoomTo(10), 2000, null);
        CameraPosition cameraPosition = new CameraPosition.Builder()
	    .target(target)      // Sets the center of the map to Mountain View
	    .zoom(17)                   // Sets the zoom
	    .bearing(90)                // Sets the orientation of the camera to east
	    .tilt(30)                   // Sets the tilt of the camera to 30 degrees
	    .build();                   // Creates a CameraPosition from the builder
        mMap.animateCamera(CameraUpdateFactory.newCameraPosition(cameraPosition));
	
    }
    
    /*LocationListener locationListener = new LocationListener() {
        private final String TAG = "xoxoxo.LocationListener";

        public void onLocationChanged(Location l) {
        	Log.i(TAG, "!!!!!!!!!!!!!!!!!!!!!!!!!");
        	Location loc = new Location("best");
	    	loc = (Location) mLocationClient.getLastLocation();
	    	LatLng target = new LatLng(loc.getLatitude(), loc.getLongitude());
	    	CameraPosition cameraPosition = new CameraPosition.Builder()
	    	    .target(target)      // Sets the center of the map to Mountain View
	    	    .zoom(17)                   // Sets the zoom
	    	    .bearing(90)                // Sets the orientation of the camera to east
	    	    .tilt(30)                   // Sets the tilt of the camera to 30 degrees
	    	    .build();                   // Creates a CameraPosition from the builder
	    	mMap.animateCamera(CameraUpdateFactory.newCameraPosition(cameraPosition));
        }

        public void onProviderEnabled(String p) {
            Log.i(TAG, "Provider enabled");
        }

        public void onProviderDisabled(String p) {
            Log.i(TAG, "Provider disabled");
        }

        public void onStatusChanged(String p, int status, Bundle extras) {
            Log.i(TAG, "Status changed");
        }
    };*/
    
    /*
     * Called by Location Services if the connection to the
     * location client drops because of an error.
     */
    @Override
    public void onDisconnected() {
        Toast.makeText(this, "Disconnected. Please re-connect.",Toast.LENGTH_SHORT).show();
    }
    
    /*
     * Called by Location Services if the attempt to
     * Location Services fails.
     */
    @Override
    public void onConnectionFailed(ConnectionResult connectionResult) {
        /*
         * Google Play services can resolve some errors it detects.
         * If the error has a resolution, try sending an Intent to
         * start a Google Play services activity that can resolve
         * error.
         */
        if (connectionResult.hasResolution()) {
            try {
                // Start an Activity that tries to resolve the error
                connectionResult.startResolutionForResult(this,
                        CONNECTION_FAILURE_RESOLUTION_REQUEST);
                /*
                 * Thrown if Google Play services canceled the original
                 * PendingIntent
                 */
            } catch (IntentSender.SendIntentException e) {
                // Log the error
                e.printStackTrace();
            }
        } else {
            /*
             * If no resolution is available, display a dialog to the
             * user with the error.
             */
            showErrorDialog(connectionResult.getErrorCode());
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
	
	// Define a DialogFragment that displays the error dialog
    public static class ErrorDialogFragment extends DialogFragment {
        // Global field to contain the error dialog
        private Dialog mDialog;
        // Default constructor. Sets the dialog field to null
        public ErrorDialogFragment() {
            super();
            mDialog = null;
        }
        // Set the dialog to display
        public void setDialog(Dialog dialog) {
            mDialog = dialog;
        }
        // Return a Dialog to the DialogFragment.
        @Override
        public Dialog onCreateDialog(Bundle savedInstanceState) {
            return mDialog;
        }
    }
    
    /*
     * Handle results returned to the FragmentActivity
     * by Google Play services
     */
    @Override
    protected void onActivityResult(
            int requestCode, int resultCode, Intent data) {
        // Decide what to do based on the original request code
        switch (requestCode) {
            //...
            case CONNECTION_FAILURE_RESOLUTION_REQUEST :
            /*
             * If the result code is Activity.RESULT_OK, try
             * to connect again
             */
                switch (resultCode) {
                    case Activity.RESULT_OK :
                    /*
                     * Try the request again
                     */
                   // ...
                    break;
                }
            //...
        }
     }
	
	private boolean servicesConnected() {
        // Check that Google Play services is available
        int resultCode = GooglePlayServicesUtil.isGooglePlayServicesAvailable(this);
        // If Google Play services is available
        if (ConnectionResult.SUCCESS == resultCode) {
            // In debug mode, log the status
            Log.d("Location Updates","Google Play services is available.");
            // Continue
            return true;
        // Google Play services was not available for some reason
        } else {
            // Get the error code
            // Get the error dialog from Google Play services
            Dialog errorDialog = GooglePlayServicesUtil.getErrorDialog(
            		resultCode,this,CONNECTION_FAILURE_RESOLUTION_REQUEST);

            // If Google Play services can provide an error dialog
            if (errorDialog != null) {
                // Create a new DialogFragment for the error dialog
                ErrorDialogFragment errorFragment = new ErrorDialogFragment();
                // Set the dialog in the DialogFragment
                errorFragment.setDialog(errorDialog);
                // Show the error dialog in the DialogFragment
                errorFragment.show(getSupportFragmentManager(),
                        "Location Updates");
            }
        }
        return false;
	}


	@Override
	public boolean onPrepareOptionsMenu(Menu menu) {
	    super.onPrepareOptionsMenu(menu);
	    if (transporte.compareTo("true") == 0)
	    	menu.findItem(R.id.action_transporte).setChecked(true);
	    if (ecobici.compareTo("true") == 0)
	    	menu.findItem(R.id.action_ecobici).setChecked(true);
	    if (hoteles.compareTo("true") == 0)
	    	menu.findItem(R.id.action_hoteles).setChecked(true);
	    if (mercados.compareTo("true") == 0)
	    	menu.findItem(R.id.action_mercados).setChecked(true);
	    if (embajadas.compareTo("true") == 0)
	    	menu.findItem(R.id.action_embajadas).setChecked(true);
	    if (interes.compareTo("true") == 0)
	    	menu.findItem(R.id.action_interes).setChecked(true);
	    if (estacionamientos.compareTo("true") == 0)
	    	menu.findItem(R.id.action_estacionamientos).setChecked(true);
	    if (hospitales.compareTo("true") == 0)
	    	menu.findItem(R.id.action_hospitales).setChecked(true);
	    return true;
	}
	
	@SuppressLint("NewApi")
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		//getWindow().requestFeature(Window.FEATURE_ACTION_BAR_OVERLAY);
		setContentView(R.layout.activity_map);
		//getActionBar().setBackgroundDrawable(getResources().getDrawable(R.drawable.actionbar_bg));
		Log.i("CREATE", "11111");
		Intent intent = getIntent();
		transporte = intent.getStringExtra("transporte");
		ecobici = intent.getStringExtra("ecobici");
		hoteles = intent.getStringExtra("hoteles");
		mercados = intent.getStringExtra("mercados");
		embajadas = intent.getStringExtra("embajadas");
		interes = intent.getStringExtra("interes");
		estacionamientos = intent.getStringExtra("estacionamientos");
		hospitales = intent.getStringExtra("hospitales");
	    
		Log.i("CREATE", "222222");
		mMap = ((SupportMapFragment) getSupportFragmentManager().findFragmentById(R.id.map))
                .getMap();
		
        if (mMap== null)
        {
            Toast.makeText(this,"Google Maps not Available",
                    Toast.LENGTH_LONG).show();
        }
        else{
        	mMap.setMapType(GoogleMap.MAP_TYPE_NORMAL);
        	mMap.setBuildingsEnabled(true);
        	mMap.setIndoorEnabled(true);
        	mMap.setMyLocationEnabled(true);
        	mMap.getUiSettings().setAllGesturesEnabled(true);
        	mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(MEXICO, 4));
        	mMap.setOnMyLocationChangeListener(this);
        	
        	mLocationClient = new LocationClient (this,this,this);
        	mLocationRequest = LocationRequest.create();
        	mLocationRequest.setPriority(LocationRequest.PRIORITY_HIGH_ACCURACY);
            mLocationRequest.setInterval(UPDATE_INTERVAL);
            mLocationRequest.setFastestInterval(FASTEST_INTERVAL);
        }
        Log.i("CREATE", "33333");
        mPrefs = getSharedPreferences("SharedPreferences",Context.MODE_PRIVATE);
        mEditor = mPrefs.edit();
	}
	
	/*
     * Called when the Activity becomes visible.
     */
    @Override
    protected void onStart() {
        super.onStart();
        // Connect the client.
        if (servicesConnected())
        	mLocationClient.connect();
        if (transporte.compareTo("true") == 0){
        	
        	RetrieveJson json = new RetrieveJson();
        	//Log.i("JSON", "ERRORRRRR2222..");
        	String latitude = String.valueOf(MEXICO.latitude);
        	String longitude = String.valueOf(MEXICO.longitude);

        	if (mLocationClient.isConnected()){
        		latitude = String.format("%.4f",mLocationClient.getLastLocation().getLatitude());
        		longitude = String.format("%.4f",mLocationClient.getLastLocation().getLongitude());
        	}
        	
            JSONObject jo = json.makeHttpRequest("http://datos.labplc.mx/georeferencia.json?&latitud=" + latitude + "&longitud=" + longitude + "&radio=1000", "GET", null);
            try{
            	//Toast.makeText(this, jo.toString(), Toast.LENGTH_SHORT).show();
            	if (jo.toString()!= null && jo.toString().length()>0){
	            	JSONArray contentJ = jo.getJSONObject("consulta").getJSONArray("ubicaciones");
	            	for (int i=0; i<contentJ.length(); i++){
	            		JSONObject item = contentJ.getJSONObject(i);
	     	        	mMap.addMarker(new MarkerOptions()
	     	        	        .position(new LatLng(Double.parseDouble(item.getString("latitud")), Double.parseDouble(item.getString("longitud"))))
	     	        	        .title("I."+ item.getString("nombre")).flat(false).snippet(item.getString("direccion")));
	            		//Toast.makeText(this, item.getString("nombre"), Toast.LENGTH_SHORT).show();
	            	}
            	}
            	
            	//JSONArray ja = jo.getJSONArray("consulta");
            	//marker.setSnippet("Bicicletas disponibles: "+jo.getJSONObject("disponibilidad").getString("bicicletas") + ".  \n" + "Lugares vacíos: "+jo.getJSONObject("disponibilidad").getString("lugares"));
            	//Toast.makeText(this, jo.getJSONObject("consulta").getString("reporte"), Toast.LENGTH_SHORT).show();
            }
            catch (JSONException e){
            	e.printStackTrace();
            }
        }
    }
    
    @Override
    protected void onResume() {
    	
    	
        /*
         * Get any previous setting for location updates
         * Gets "false" if an error occurs
         */
        if (mPrefs.contains("KEY_UPDATES_ON")) {
            mUpdatesRequested = mPrefs.getBoolean("KEY_UPDATES_ON", true);
        // Otherwise, turn off location updates
        } else {
            mEditor.putBoolean("KEY_UPDATES_ON", true);
            mEditor.commit();
        }
        
        DatabaseHandler myDbHelper = new DatabaseHandler(this);
 
        try {
        	myDbHelper.createDataBase();
        } catch (IOException ioe) {
        	throw new Error("Unable to create database");
        }
        
        if (ecobici.compareTo("true") == 0){
	        List<EstacionEcoBici> estaciones = myDbHelper.getAllEstaciones();       
	
	        for (EstacionEcoBici eb : estaciones) {
	        	mMap.addMarker(new MarkerOptions()
	        	        .position(new LatLng(eb.getLatitud(), eb.getLongitud()))
	        	        .title("E."+eb.getID() + ". " + eb.getNombre()).flat(false).icon(BitmapDescriptorFactory.fromResource(R.drawable.ecobici_marker)));
	        	//Toast.makeText(this, eb.getNombre(), Toast.LENGTH_SHORT).show();
	        }
        }
        
        if (hospitales.compareTo("true") == 0){
	        List<CentrosSalud> centrosSalud = myDbHelper.getAllCentrosSalud();     
	
	        for (CentrosSalud cs : centrosSalud) {
	        	mMap.addMarker(new MarkerOptions()
	        	        .position(new LatLng(cs.get_longitud(), cs.get_latitud()))
	        	        .snippet("Dirección: "+cs.get_domicilio()+".   "+"Teléfono: "+cs.get_telefono())
	        	        .title("H. "+cs.get_nombreCentro()).icon(BitmapDescriptorFactory.fromResource(R.drawable.hospitales_marker)));
	        	//Toast.makeText(this, eb.getNombre(), Toast.LENGTH_SHORT).show();
	        }
        }
        
        if (embajadas.compareTo("true") == 0){
	        List<Embajada> embajadas = myDbHelper.getAllEmbajadas();     
	
	        for (Embajada cs : embajadas) {
	        	mMap.addMarker(new MarkerOptions()
	        	        .position(new LatLng(cs.get_latitud(), cs.get_longitud()))
	        	        .snippet("Dirección: "+cs.get_calle()+", "+cs.get_colonia()+", "+cs.get_delegacion()+".   Teléfono: "+cs.get_tel1())
	        	        .title("EM. "+cs.get_pais()).icon(BitmapDescriptorFactory.fromResource(R.drawable.embajadas_marker)));
	        	//Toast.makeText(this, eb.getNombre(), Toast.LENGTH_SHORT).show();
	        }
        }
        
        if (mercados.compareTo("true") == 0){
	        List<Mercado> mercados = myDbHelper.getAllMercados();     
	
	        for (Mercado cs : mercados) {
	        	mMap.addMarker(new MarkerOptions()
	        	        .position(new LatLng(cs.get_latitud(), cs.get_longitud()))
	        	        .snippet(""+cs.get_tipoDesc()+".    Delegación: "+cs.get_delegacionNombre())
	        	        .title("M. "+cs.get_nombre()).icon(BitmapDescriptorFactory.fromResource(R.drawable.mercados_marker)));
	        	//Toast.makeText(this, eb.getNombre(), Toast.LENGTH_SHORT).show();
	        }
        }
        
        
        mMap.setOnMarkerClickListener(new OnMarkerClickListener(){
        	public boolean onMarkerClick(Marker marker){
        		String [] split = marker.getTitle().split("\\.");
        		
        		if (split[0].compareTo("E") == 0){
	        		RetrieveJson json = new RetrieveJson();
	        		
	                JSONObject jo = json.makeHttpRequest("http://datos.labplc.mx/movilidad/ecobici/" + split[1] + ".json", "GET", null);
	                try{
	                	//JSONArray ja = jo.getJSONArray("consulta");
	                	marker.setSnippet("Bicicletas disponibles: "+jo.getJSONObject("disponibilidad").getString("bicicletas") + ".  \n" + "Lugares vacíos: "+jo.getJSONObject("disponibilidad").getString("lugares"));
	                	//Toast.makeText(this, jo.getJSONObject("consulta").getString("reporte"), Toast.LENGTH_SHORT).show();
	                }
	                catch (JSONException e){
	                	e.printStackTrace();
	                }
        		}
        		return false;
        	}
        });  
        super.onResume();
    }
    
    protected void onPause() {
        // Save the current setting for updates
        mEditor.putBoolean("KEY_UPDATES_ON", mUpdatesRequested);
        mEditor.commit();
        super.onPause();
    }

    /*
     * Called when the Activity is no longer visible.
     */
    @Override
    protected void onStop() {
    	if (mLocationClient.isConnected()) {
    		mLocationClient.removeLocationUpdates(this);
        }
        // Disconnecting the client invalidates it.
        mLocationClient.disconnect();
        super.onStop();
    }
    
    @SuppressLint("NewApi")
	@Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.map, menu);
        
     // Associate searchable configuration with the SearchView
	    SearchManager searchManager =
	           (SearchManager) getSystemService(Context.SEARCH_SERVICE);
	    SearchView searchView =
	            (SearchView) menu.findItem(R.id.action_search).getActionView();
	    searchView.setSearchableInfo(
	            searchManager.getSearchableInfo(getComponentName()));
	    
		return super.onCreateOptionsMenu(menu);
    }
    
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle presses on the action bar items
        switch (item.getItemId()) {
            case R.id.action_list:
                openElementsList();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }
    
    protected void openElementsList(){
    	Intent intent = new Intent(MapActivity.this, ElementsListActivity.class);
    	if (transporte.compareTo("true") == 0)
    		intent.putExtra("transporte", "true");
    	else
    		intent.putExtra("transporte", "false");

    	if (ecobici.compareTo("true") == 0)
    		intent.putExtra("ecobici", "true");
    	else
    		intent.putExtra("ecobici", "false");
    	
    	if (hoteles.compareTo("true") == 0)
    		intent.putExtra("hoteles", "true");
    	else
    		intent.putExtra("hoteles", "false");
    	
    	if (mercados.compareTo("true") == 0)
    		intent.putExtra("mercados", "true");
    	else
    		intent.putExtra("mercados", "false");
    	
    	if (embajadas.compareTo("true") == 0)
    		intent.putExtra("embajadas", "true");
    	else
    		intent.putExtra("embajadas", "false");
    	
    	if (interes.compareTo("true") == 0)
    		intent.putExtra("interes", "true");
    	else
    		intent.putExtra("interes", "false");
    	
    	if (estacionamientos.compareTo("true") == 0)
    		intent.putExtra("estacionamientos", "true");
    	else
    		intent.putExtra("estacionamientos", "false");
    	
    	if (hospitales.compareTo("true") == 0)
    		intent.putExtra("hospitales", "true");
    	else
    		intent.putExtra("hospitales", "false");
    	
		startActivity(intent);
    }

}
