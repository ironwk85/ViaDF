package com.example.viadf.databases;

public class Mercado {
	int _id;
	String _nombre;
	int _locales;
	String _tipoDesc;
	String _delegacionNombre;
	float _latitud;
	float _longitud;
	
	public Mercado(){}
	
	public Mercado (int id, String nombre, int locales, String tipoDesc,
			String delegacionNombre, float latitud, float longitud){
		this._id = id;
		this._nombre = nombre;
		this._locales = locales;
		this._tipoDesc = tipoDesc;
		this._delegacionNombre = delegacionNombre;
		this._latitud = latitud;
		this._longitud = longitud;
	}

	public int get_id() {
		return _id;
	}

	public void set_id(int _id) {
		this._id = _id;
	}

	public String get_nombre() {
		return _nombre;
	}

	public void set_nombre(String _nombre) {
		this._nombre = _nombre;
	}

	public int get_locales() {
		return _locales;
	}

	public void set_locales(int _locales) {
		this._locales = _locales;
	}

	public String get_tipoDesc() {
		return _tipoDesc;
	}

	public void set_tipoDesc(String _tipoDesc) {
		this._tipoDesc = _tipoDesc;
	}

	public String get_delegacionNombre() {
		return _delegacionNombre;
	}

	public void set_delegacionNombre(String _delegacionNombre) {
		this._delegacionNombre = _delegacionNombre;
	}

	public float get_latitud() {
		return _latitud;
	}

	public void set_latitud(float _latitud) {
		this._latitud = _latitud;
	}

	public float get_longitud() {
		return _longitud;
	}

	public void set_longitud(float _longitud) {
		this._longitud = _longitud;
	}
}
