package com.example.viadf;

import android.app.Activity;
import android.app.Dialog;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

public class MedalDialogActivity extends Dialog implements
android.view.View.OnClickListener {

	public Activity c;
	public Dialog d;
	public Button yes, no;
	public int t;
	
	public MedalDialogActivity(Activity a, int t) {
		super(a);
		this.c = a;
		this.t = t;
	}
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.activity_medal_dialog);
		//final TextView tv = (TextView) findViewById(R.id.leyenda);
		//tv.setText(t);
		final ImageView iv = (ImageView) findViewById(R.id.imagen_leyenda);
		switch(t){
		case 1:
			iv.setBackgroundResource(R.drawable.anuncio_medalla_historiador);
			break;
		case 2:
			iv.setBackgroundResource(R.drawable.anuncio_medalla_diversion);
			break;
		case 3:
			iv.setBackgroundResource(R.drawable.anuncio_medalla_ciclista);
			break;
		default:
			break;
		}
		
		//yes = (Button) findViewById(R.id.btn_yes);
		//no = (Button) findViewById(R.id.btn_no);
		//yes.setOnClickListener(this);
		//no.setOnClickListener(this);
	}
	
	@Override
	public void onClick(View v) {
	//	switch (v.getId()) {
	//		case R.id.btn_yes:
	//		  c.finish();
	//		  break;
	//		case R.id.btn_no:
	//		  dismiss();
	//		  break;
	//		default:
	//		  break;
	//		}
	//		dismiss();
	//	}
	}
}