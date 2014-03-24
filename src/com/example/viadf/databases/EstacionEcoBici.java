package com.example.viadf.databases;

public class EstacionEcoBici {
	int _id;
	String _principal;
	String _secundaria;
	String _referencia;
	String _colonia;
	String _delegacion;
	float _longitud;
	float _latitud;
	String _nombre;
	
	public EstacionEcoBici(){}
	
	public EstacionEcoBici(int id, String principal, String secundaria,
			String referencia, String colonia, String delegacion,
			float longitud, float latitud, String nombre){
		this._id = id;
		this._principal = principal;
		this._secundaria = secundaria;
		this._referencia = referencia;
		this._colonia = colonia;
		this._delegacion = delegacion;
		this._longitud = longitud;
		this._latitud = latitud;
		this._nombre = nombre; 
	}
	
	public int getID(){
		return this._id;
	}
	
	public String getPrincipal() {
		return this._principal;
	}
	
	public String getSecundaria(){
		return this._secundaria;
	}
	
	public String getReferencia(){
		return this._referencia;
	}
	
	public String getColonia() {
		return this._colonia;
	}
	
	public String getDelegacion(){
		return this._delegacion;
	}
	
	public float getLongitud(){
		return this._longitud;
	}
	
	public float getLatitud() {
		return this._latitud;
	}
	
	public String getNombre(){
		return this._nombre;
	}
	
	public void setID(int id){
		this._id = id;
	}
	
	public void setPrincipal(String principal){
		this._principal = principal;
	}
	
	public void setSecundaria(String secundaria){
		this._secundaria = secundaria;
	}
	
	public void setReferencia(String referencia){
		this._referencia = referencia;
	}
	
	public void setColonia(String colonia){
		this._colonia = colonia;
	}
	
	public void setDelegacion(String delegacion){
		this._delegacion = delegacion;
	}
	
	public void setLongitud(float longitud){
		this._longitud = longitud;
	}
	
	public void setLatitud (float latitud){
		this._latitud = latitud;
	}
	
	public void setNombre(String nombre){
		this._nombre = nombre;
	}
}
