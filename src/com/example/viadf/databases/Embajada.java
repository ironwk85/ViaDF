package com.example.viadf.databases;

public class Embajada {
	int _id;
	String _pais;
	String _calle;
	String _colonia;
	String _delegacion;
	String _cp;
	String _tel1;
	String _tel2;
	String _zona;
	float _latitud;
	float _longitud;
	
	public Embajada(){}
	
	public Embajada(int id, String pais, String calle, String colonia,
			String delegacion, String cp, String tel1, String tel2,
			String zona, float latitud, float longitud){
		this._id = id;
		this._pais = pais;
		this._calle = calle;
		this._colonia = colonia;
		this._delegacion = delegacion;
		this._cp = cp;
		this._tel1 = tel1;
		this._tel2 = tel2;
		this._zona = zona;
		this._latitud = latitud;
		this._longitud = longitud;
	}

	public int get_id() {
		return _id;
	}

	public void set_id(int _id) {
		this._id = _id;
	}

	public String get_pais() {
		return _pais;
	}

	public void set_pais(String _pais) {
		this._pais = _pais;
	}

	public String get_calle() {
		return _calle;
	}

	public void set_calle(String _calle) {
		this._calle = _calle;
	}

	public String get_colonia() {
		return _colonia;
	}

	public void set_colonia(String _colonia) {
		this._colonia = _colonia;
	}

	public String get_delegacion() {
		return _delegacion;
	}

	public void set_delegacion(String _delegacion) {
		this._delegacion = _delegacion;
	}

	public String get_cp() {
		return _cp;
	}

	public void set_cp(String _cp) {
		this._cp = _cp;
	}

	public String get_tel1() {
		return _tel1;
	}

	public void set_tel1(String _tel1) {
		this._tel1 = _tel1;
	}

	public String get_tel2() {
		return _tel2;
	}

	public void set_tel2(String _tel2) {
		this._tel2 = _tel2;
	}

	public String get_zona() {
		return _zona;
	}

	public void set_zona(String _zona) {
		this._zona = _zona;
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
