package com.example.viadf;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.SearchManager;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.SearchView;

import com.example.viadf.databases.CentrosSalud;
import com.example.viadf.databases.DatabaseHandler;
import com.example.viadf.databases.Embajada;
import com.example.viadf.databases.EstacionEcoBici;
import com.example.viadf.databases.Mercado;

public class ElementsListActivity extends Activity {
	
	static final String KEY_SONG = "song"; // parent node
	static final String KEY_ID = "id";
	static final String KEY_TITLE = "title";
	static final String KEY_ARTIST = "artist";
	static final String KEY_DURATION = "duration";
	static final String KEY_THUMB_URL = "thumb_url";
	static final String COLONIA = "colonia";
	static final String SECUNDARIA = "secundaria";
	static final String DELEGACION = "delegacion";
	String transporte;
	String ecobici;
	String hoteles;
	String mercados;
	String embajadas;
	String interes;
	String estacionamientos;
	String hospitales;
	
	ListView list;
	ListAdapter adapter;
	
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
	
	 @Override
	    protected void onStart() {
	        super.onStart();
	        
	        
	 }
	 
	 @Override
	    protected void onResume() {
		 super.onResume();
		 
		 
			
			ArrayList<HashMap<String, String>> elements = new ArrayList<HashMap<String, String>>();
			
			DatabaseHandler myDbHelper = new DatabaseHandler(this);
			 
	        try {
	        	myDbHelper.createDataBase();
	        } catch (IOException ioe) {
	        	throw new Error("Unable to create database");
	        }
			
	        if (ecobici.compareTo("true") == 0){
				List<EstacionEcoBici> estaciones = myDbHelper.getAllEstaciones();       
		
		        for (EstacionEcoBici eb : estaciones) {
		        	HashMap<String, String> map = new HashMap<String, String>();
		        	map.put(KEY_ID, eb.getID()+"");
		        	String nombre = eb.getNombre();
		        	map.put(KEY_TITLE,nombre);
		        	map.put(KEY_ARTIST, eb.getPrincipal() + ". - " + eb.getReferencia());
		        	map.put(KEY_DURATION, eb.getDelegacion());
		        	map.put(KEY_THUMB_URL, R.drawable.ecobici_thumb+"");
		        	map.put(COLONIA, eb.getColonia());
		        	map.put(SECUNDARIA, eb.getSecundaria());
		        	map.put(DELEGACION, eb.getDelegacion());
		        	map.put("tipo", "ecobici");
		        	elements.add(map);
		        }
	        }
	        if (hospitales.compareTo("true") == 0){
	        	List<CentrosSalud> cc = myDbHelper.getAllCentrosSalud();
	        	for (CentrosSalud c : cc){
	        		HashMap<String, String> map = new HashMap<String, String>();
	        		map.put(KEY_ID, c.get_id()+"");
	        		map.put(KEY_TITLE, c.get_nombreCentro());
	        		map.put(KEY_ARTIST, c.get_tipo()+c.get_subTipo());
	        		map.put(KEY_DURATION, c.get_telefono());
	        		map.put(KEY_THUMB_URL, R.drawable.hospitales_thumb+"");
	        		map.put("domicilio", c.get_domicilio());
	        		map.put("horario", c.get_horario());
	        		//map.put("telefono", c.get_telefono());
	        		elements.add(map);
	        	}
	        }
	        if (embajadas.compareTo("true") == 0){
	        	List<Embajada> embajadas = myDbHelper.getAllEmbajadas();
	        	for (Embajada embajada : embajadas){
	        		HashMap<String, String> map = new HashMap<String, String>();
	        		map.put(KEY_ID, embajada.get_id()+"");
	        		map.put(KEY_TITLE, embajada.get_pais());
	        		String direccion = embajada.get_calle() + ", " + 
	        				embajada.get_colonia() + ", " + embajada.get_delegacion()+".";
	        		map.put(KEY_ARTIST, direccion);
	        		String telefonos = embajada.get_tel1() + ", " + embajada.get_tel2();
	        		map.put(KEY_DURATION, telefonos);
	        		map.put(KEY_THUMB_URL, R.drawable.embajada_thumb+"");
	        		elements.add(map);
	        	}
	        }
	        if (mercados.compareTo("true") == 0){
	        	List<Mercado> mercados = myDbHelper.getAllMercados();
	        	for (Mercado mercado : mercados){
	        		HashMap<String, String> map = new HashMap<String, String>();
	        		map.put(KEY_ID, mercado.get_id()+"");
	        		map.put(KEY_TITLE, mercado.get_nombre());
	        		map.put(KEY_ARTIST, mercado.get_tipoDesc());
	        		map.put(KEY_DURATION, mercado.get_delegacionNombre());
	        		map.put(KEY_THUMB_URL, R.drawable.mercdo_thumb+"");
	        		elements.add(map);
	        	}
	        }
	        
			list = (ListView)findViewById(R.id.list);
			adapter = new com.example.viadf.adapters.ListAdapter(this, elements, this);
			list.setAdapter(adapter);
			
			list.setOnItemClickListener(new OnItemClickListener() {
				 
	            @Override
	            public void onItemClick(AdapterView<?> parent, View view,
						int position, long id) {
	            	HashMap<String, String> o = (HashMap<String, String>) list.getItemAtPosition(position);
	            	//HashMap<String, Object> item = adapter.get(position);
	            	//openDetails();
	            	//HashMap<String, String> item = (HashMap<String, String>)parent.getItemAtPosition(position);
	            	openDetails(o);
	            	//Toast.makeText(ElementsListActivity.this, "" + o.get(KEY_TITLE), Toast.LENGTH_SHORT).show();
	            	//Toast.makeText(ElementsListActivity.this, "" + parent.getItemAtPosition(position), Toast.LENGTH_SHORT).show();
	            }
	        });
	 }

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_elements_list);
		
		if (getIntent().getStringExtra("transporte") != null){
			Intent intent = getIntent();
			transporte = intent.getStringExtra("transporte");
			ecobici = intent.getStringExtra("ecobici");
			hoteles = intent.getStringExtra("hoteles");
			mercados = intent.getStringExtra("mercados");
			embajadas = intent.getStringExtra("embajadas");
			interes = intent.getStringExtra("interes");
			estacionamientos = intent.getStringExtra("estacionamientos");
			hospitales = intent.getStringExtra("hospitales");
		}
	}
	
	protected void openDetails(HashMap<String, String> item){
    	Intent intent = new Intent(ElementsListActivity.this, DetailsActivity.class);
    	intent.putExtra(KEY_ID, item.get(KEY_ID));
    	intent.putExtra(KEY_TITLE, item.get(KEY_TITLE));
    	intent.putExtra(KEY_ARTIST, item.get(KEY_ARTIST));
    	intent.putExtra(KEY_DURATION, item.get(KEY_DURATION));
    	intent.putExtra(KEY_THUMB_URL, item.get(KEY_THUMB_URL));
    	intent.putExtra(COLONIA, item.get(COLONIA));
    	intent.putExtra(SECUNDARIA, item.get(SECUNDARIA));
    	intent.putExtra(DELEGACION, item.get(DELEGACION));
    	intent.putExtra("tipo", item.get("tipo"));
    	
		startActivity(intent);
    }

	@SuppressLint("NewApi")
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.elements_list, menu);
		
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
            case R.id.action_map:
                openMap();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }
    
    protected void openMap(){
    	Intent intent = new Intent(ElementsListActivity.this, MapActivity.class);
    	
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
