package com.example.viadf.adapters;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewGroup.LayoutParams;
import android.widget.BaseAdapter;
import android.widget.GridView;
import android.widget.ImageView;

public class ImageAdapter extends BaseAdapter{
	private Context mContext;
	
	private Integer[] mThumbIds;/* = {
			R.drawable.sample_2, R.drawable.sample_3,
            R.drawable.sample_4, R.drawable.sample_5,
            R.drawable.sample_6	
	};*/
	
	public ImageAdapter(Context c, Integer[] thumbs){
		this.mThumbIds = thumbs;
		mContext = c;
	}
	
	public int getCount(){
		return mThumbIds.length;
	}
	
	public Object getItem(int position){
		return null;
	}
	public long getItemId(int position){
		return 0;
	}
	
	public View getView(int position, View convertView, ViewGroup parent){
		ImageView imageView;
		
		if (convertView == null){
			imageView = new ImageView(mContext);
			imageView.setLayoutParams(new GridView.LayoutParams(LayoutParams.MATCH_PARENT,LayoutParams.MATCH_PARENT));// 85,85));
			imageView.setScaleType(ImageView.ScaleType.CENTER_INSIDE);
			imageView.setPadding(8, 8, 8, 8);
		}
		else
			imageView = (ImageView) convertView;
		
		imageView.setImageResource(mThumbIds[position]);
		return imageView;
	}
}
