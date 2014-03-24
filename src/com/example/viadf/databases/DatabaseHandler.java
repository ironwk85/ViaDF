package com.example.viadf.databases;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.List;

import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteException;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

public class DatabaseHandler extends SQLiteOpenHelper {

	private static String DB_PATH = "/data/data/com.example.viadf/databases/";
    private static String DB_NAME = "ecobici";
    private SQLiteDatabase myDataBase; 
    private final Context myContext;
	
	public DatabaseHandler(Context context){
		super(context, DB_NAME, null, 1);
		this.myContext = context;
	}
	
	// Creating Tables
    public void createDataBase() throws IOException{
    	boolean dbExist = checkDataBase();
    	if(dbExist){
    		Log.i("DATABASE", "Database existent");
    		//do nothing - database already exist
    	}else{
    		//By calling this method and empty database will be created into the default system path
               //of your application so we are gonna be able to overwrite that database with our database.
        	this.getReadableDatabase();
        	try {
    			copyDataBase();
    			Log.i("DATABASE", "Database copied");
    		} catch (IOException e) {
        		throw new Error("Error copying database");
 
        	}
    	}
    }
 
    // Upgrading database
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        // Drop older table if existed
        //db.execSQL("DROP TABLE IF EXISTS " + TABLE_ESTACIONES);
 
        // Create tables again
        onCreate(db);
    }
    
    @Override
	public synchronized void close() {
    	if(myDataBase != null)
    		myDataBase.close();
    	super.close();
	}
    
    @Override
	public void onCreate(SQLiteDatabase db) {}
    
    private boolean checkDataBase(){
    	SQLiteDatabase checkDB = null;
    	try{
    		String myPath = DB_PATH + DB_NAME;
    		checkDB = SQLiteDatabase.openDatabase(myPath, null, SQLiteDatabase.OPEN_READONLY);
    	}catch(SQLiteException e){
    		//database does't exist yet.
    	}
 
    	if(checkDB != null){
    		checkDB.close();
    	}
    	return checkDB != null ? true : false;
    }
    
    private void copyDataBase() throws IOException{
    	//Open your local db as the input stream
    	InputStream myInput = myContext.getAssets().open(DB_NAME);
    	// Path to the just created empty db
    	String outFileName = DB_PATH + DB_NAME;
    	//Open the empty db as the output stream
    	OutputStream myOutput = new FileOutputStream(outFileName);
    	//transfer bytes from the inputfile to the outputfile
    	byte[] buffer = new byte[1024];
    	int length;
    	while ((length = myInput.read(buffer))>0){
    		myOutput.write(buffer, 0, length);
    	}
    	Log.i("DATABASE", "Copiying db");
    	//Close the streams
    	myOutput.flush();
    	myOutput.close();
    	myInput.close();
 
    }
    
    public void openDataBase() throws SQLException{
        String myPath = DB_PATH + DB_NAME;
    	myDataBase = SQLiteDatabase.openDatabase(myPath, null, SQLiteDatabase.OPEN_READONLY);
    	Log.i("DATABASE", "Database openned");
    }
    
 // Getting All Translates
    public List<EstacionEcoBici> getAllEstaciones() {
        List<EstacionEcoBici> estaciones = new ArrayList<EstacionEcoBici>();
        // Select All Query
        String selectQuery = "SELECT  * FROM estaciones";
        
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);

        // looping through all rows and adding to list
        if (cursor.moveToFirst()) {
            do {
            	EstacionEcoBici estacion = new EstacionEcoBici();
            	estacion.setID(cursor.getInt(0));
            	estacion.setPrincipal(cursor.getString(1));
            	estacion.setSecundaria(cursor.getString(2));
            	estacion.setReferencia(cursor.getString(3));
            	estacion.setColonia(cursor.getString(4));
            	estacion.setDelegacion(cursor.getString(5));
            	estacion.setLongitud(cursor.getFloat(6));
            	estacion.setLatitud(cursor.getFloat(7));
            	estacion.setNombre(cursor.getString(8));
                // Adding Translate to list
            	estaciones.add(estacion);
            } while (cursor.moveToNext());
        }

        // return Translate list
        return estaciones;
    }
    
    public List<CentrosSalud> getAllCentrosSalud() {
        List<CentrosSalud> cs = new ArrayList<CentrosSalud>();
        // Select All Query
        String selectQuery = "SELECT  _id, TIPO, SUBTIPO, NOMBRECENTRO, " +
        		"LATITUD, LONGITUD, HORARIO, TELEFONO  FROM CenSal1";
        
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);

        // looping through all rows and adding to list
        if (cursor.moveToFirst()) {
            do {
            	CentrosSalud c = new CentrosSalud();
            	c.set_id(cursor.getInt(0));
            	c.set_tipo(cursor.getString(1));
            	c.set_subTipo(cursor.getString(2));
            	c.set_nombreCentro(cursor.getString(3));
            	c.set_latitud(cursor.getFloat(4));
            	c.set_longitud(cursor.getFloat(5));
            	c.set_horario(cursor.getString(6));
            	c.set_telefono(cursor.getString(7));
                // Adding Translate to list
            	cs.add(c);
            } while (cursor.moveToNext());
        }

        // return Translate list
        return cs;
    }
    
    public List<Embajada> getAllEmbajadas() {
        List<Embajada> cs = new ArrayList<Embajada>();
        // Select All Query
        String selectQuery = "SELECT  _id, Pais, Calle, Colonia, " +
        		"Delegacion, Tel1, Tel2, Latitud, Longitud  FROM Emb2";
        
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);

        // looping through all rows and adding to list
        if (cursor.moveToFirst()) {
            do {
            	Embajada c = new Embajada();
            	c.set_id(cursor.getInt(0));
            	c.set_pais(cursor.getString(1));
            	c.set_calle(cursor.getString(2));
            	c.set_colonia(cursor.getString(3));
            	c.set_delegacion(cursor.getString(4));
            	c.set_tel1(cursor.getString(5));
            	c.set_tel2(cursor.getString(6));
            	c.set_latitud(cursor.getFloat(7));
            	c.set_longitud(cursor.getFloat(8));
                // Adding Translate to list
            	cs.add(c);
            } while (cursor.moveToNext());
        }

        // return Translate list
        return cs;
    }
    
    public List<Mercado> getAllMercados() {
        List<Mercado> cs = new ArrayList<Mercado>();
        // Select All Query
        String selectQuery = "SELECT  _id, nombre, tipodesc, delegacionnombre, " +
        		"latitud, longitud  FROM MERC1";
        
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);

        // looping through all rows and adding to list
        if (cursor.moveToFirst()) {
            do {
            	Mercado c = new Mercado();
            	c.set_id(cursor.getInt(0));
            	c.set_nombre(cursor.getString(1));
            	c.set_tipoDesc(cursor.getString(2));
            	c.set_delegacionNombre(cursor.getString(3));
            	c.set_latitud(cursor.getFloat(4));
            	c.set_longitud(cursor.getFloat(5));
                // Adding Translate to list
            	cs.add(c);
            } while (cursor.moveToNext());
        }

        // return Translate list
        return cs;
    }
}
