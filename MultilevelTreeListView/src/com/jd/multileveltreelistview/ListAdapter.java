package com.jd.multileveltreelistview;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.StringTokenizer;
import java.util.TimeZone;


import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.TextView;
import com.jd.multileveltreelistview.R;
public class ListAdapter extends BaseAdapter
{
	
	Context contx;
	ArrayList<Entity> objects;
	 
	 LayoutInflater layoutInflator;
	 View VVV=null;
	public ListAdapter(Context context, int textViewResourceId,
			ArrayList<Entity> objects)
	{
		//super(context, textViewResourceId, objects);
		this.objects=objects;
		contx=context;
		layoutInflator = LayoutInflater.from(contx);
		
		 VVV = layoutInflator.inflate(R.layout.row_cell_multilevel,null);
		
	}

//	@Override
	public View getView(int position, View convertView, ViewGroup parent) 
	{
		ViewHolder holder;
		try
		{
		Entity CC=getItem(position);//objects.get(position);
		if (convertView == null)
		{
			//LayoutInflater layoutInflator = LayoutInflater.from(getContext());//layoutInflator
			convertView = View.inflate(contx,R.layout.row_cell_multilevel,null);
			holder = new ViewHolder();
			
			holder.txtName=(TextView)convertView.findViewById(R.id.row_cell_text_multilevel);
			holder.tvDummy=(TextView)convertView.findViewById(R.id.row_cell_text_dummy_multilevel);
			holder.btn=(Button)convertView.findViewById(R.id.row_cell_btn_multilevel);
			
			convertView.setTag(holder);
			
		   
		}
		else
		{

			
		}
		holder = (ViewHolder) convertView.getTag();
		
		if((""+CC.HasChild).equalsIgnoreCase("1")){
        	holder.btn.setVisibility(View.VISIBLE);
        	if(!CC.isOpened){
        		holder.btn.setText("+");
        	}
        	else{
        		holder.btn.setText("-");
        	}
        	holder.btn.setEnabled(true);
        }
        else{
        	holder.btn.setVisibility(View.INVISIBLE);
        	holder.btn.setText("");
        	holder.btn.setEnabled(false);
        	
        }
		
		
        
		holder.btn.setTag(position);
		holder.txtName.setTag(position);
        	
		holder.txtName.setText(CC.Name);
        String str="";
        int level=CC.level;
        for(int i=0;i<level*3;i++){
        	str+="-";
        }
        holder.tvDummy.setText(""+str);
		
        holder.txtName.setOnClickListener(new OnClickListener() {
			
			public void onClick(View v) {
				// TODO Auto-generated method stub
				
			}
		});
        
		}
		catch (Exception e)
		{
			Log.d("Exception",""+e.getMessage());
		}
		return convertView;
	}
	 
	
	
	
	static class ViewHolder 
	{
		TextView txtName;
		Button btn;
		TextView tvDummy;
		
	}
	
	void cancelDialog()
	{

	}

	public String GetUTCdateAsString()
	{
		 final String DATEFORMAT = "yyyy-MM-dd";

	    final SimpleDateFormat sdf = new SimpleDateFormat(DATEFORMAT);
	    sdf.setTimeZone(TimeZone.getTimeZone("UTC"));
	   String utcTime = sdf.format(new Date());

	    return utcTime;
	}
	
	
	
	
	


	  
	  
	  
	  //@Override
		public int getCount() {
			// TODO Auto-generated method stub
			return this.objects==null?0:this.objects.size();
		}

		//@Override
		public Entity getItem(int position) {
			// TODO Auto-generated method stub
			return this.objects==null?null:this.objects.get(position);
		}

//		@Override
		public long getItemId(int position) {
			// TODO Auto-generated method stub
			return 0;
		}

}
