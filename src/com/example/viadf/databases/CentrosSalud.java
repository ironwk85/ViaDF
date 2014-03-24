package com.example.viadf.databases;

public class CentrosSalud {
	int _id;
	String _institucion;
	String _tipo;
	String _subTipo;
	String _nombreCentro;
	String _nombreLocalidad;
	String _nombreMunicipio;
	String _nombreEstado;
	float _latitud;
	float _longitud;
	String _domicilio;
	String _horario;
	String _telefono;
	
	public CentrosSalud(){}
	
	public CentrosSalud(int id, String institucion, String tipo,
			String subTipo, String nombreCentro, String nombreLocalidad,
			String nombreMunicipio, String nombreEstado, float latitud,
			float longitud, String domicilio, String horario, String telefono){
		this._id = id;
		this._institucion = institucion;
		this._tipo = tipo;
		this._subTipo = subTipo;
		this._nombreCentro = nombreCentro;
		this._nombreLocalidad = nombreLocalidad;
		this._nombreMunicipio = nombreMunicipio;
		this._nombreEstado = nombreEstado;
		this._latitud = latitud;
		this._longitud = longitud;
		this._domicilio = domicilio;
		this._horario = horario;
		this._telefono = telefono;
	}

	public int get_id() {
		return _id;
	}

	public void set_id(int _id) {
		this._id = _id;
	}

	public String get_institucion() {
		return _institucion;
	}

	public void set_institucion(String _institucion) {
		this._institucion = _institucion;
	}

	public String get_tipo() {
		return _tipo;
	}

	public void set_tipo(String _tipo) {
		this._tipo = _tipo;
	}

	public String get_subTipo() {
		return _subTipo;
	}

	public void set_subTipo(String _subTipo) {
		this._subTipo = _subTipo;
	}

	public String get_nombreCentro() {
		return _nombreCentro;
	}

	public void set_nombreCentro(String _nombreCentro) {
		this._nombreCentro = _nombreCentro;
	}

	public String get_nombreLocalidad() {
		return _nombreLocalidad;
	}

	public void set_nombreLocalidad(String _nombreLocalidad) {
		this._nombreLocalidad = _nombreLocalidad;
	}

	public String get_nombreMunicipio() {
		return _nombreMunicipio;
	}

	public void set_nombreMunicipio(String _nombreMunicipio) {
		this._nombreMunicipio = _nombreMunicipio;
	}

	public String get_nombreEstado() {
		return _nombreEstado;
	}

	public void set_nombreEstado(String _nombreEstado) {
		this._nombreEstado = _nombreEstado;
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

	public String get_domicilio() {
		return _domicilio;
	}

	public void set_domicilio(String _domicilio) {
		this._domicilio = _domicilio;
	}

	public String get_horario() {
		return _horario;
	}

	public void set_horario(String _horario) {
		this._horario = _horario;
	}
	
	public String get_telefono() {
		return _telefono;
	}

	public void set_telefono(String _telefono) {
		this._telefono = _telefono;
	}
}
