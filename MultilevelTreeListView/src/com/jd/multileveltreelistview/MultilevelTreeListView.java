package com.jd.multileveltreelistview;
import java.util.ArrayList;
import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;
public class MultilevelTreeListView extends Activity 
{
	ListAdapter adapter;
	ListView mainList;
	ArrayList<Entity>arrTrades;
	@Override
	protected void onCreate(Bundle savedInstanceState) 
	{
		super.onCreate(savedInstanceState);
		setContentView(R.layout.treelist);
		mainList=(ListView)findViewById(R.id.currentpending_list);
		mainList.setDividerHeight(1);
		arrTrades=new ArrayList<Entity>();
    }

	@Override
	protected void onResume() 
	{
		if(arrTrades.size()==0){
			populateList();
		}
		super.onResume();
	}

	public Entity getEntity(int level,int haschild){
		Entity E=new Entity();
		E.Name="Level "+level;
		E.isOpened=false;
		E.level=level;
		E.HasChild=haschild;
		return E;
	}
	public void populateList()
	{
		try
		{
			for(int i=0;i<5;i++){
					arrTrades.add(getEntity(0, 1));
			}
			adapter=new ListAdapter(MultilevelTreeListView.this, R.id.row_cell_text_multilevel, arrTrades);
			mainList.setAdapter(adapter);
		}
		catch (Exception e)
		{
			Log.d(" populateList Exception",""+e.getMessage());
		}
	}
	public void CellButtonClick(View v){
		try{
			Button b=(Button)v;
			int index;
			index=(Integer) b.getTag();
			if(b.getText().toString().equals("+")){
				b.setText("-");
				Entity temp[]=new Entity[5];
				int PLevel=arrTrades.get(index).level+1;
				for(int i=0;i<5;i++){
					temp[i]=getEntity(PLevel, 1);
				}
				arrTrades.get(index).isOpened=true;
				if(temp!=null){
					int addindex=index+1;
					for(int i=0;i<temp.length;i++){
						arrTrades.add(addindex, temp[i]);
						addindex++;
					}
				}
				temp=null;
			}
			else{
				b.setText("+");
				arrTrades.get(index).isOpened=false;
					int removeindex=index+1;
					for(int i=0;i<5;i++){
						if(arrTrades.get(removeindex).isOpened){
							removeChilds(removeindex);
						}
						arrTrades.remove(removeindex);
					}
				}
				
			
			adapter.notifyDataSetChanged();
		}
		catch(Exception e){
			adapter.notifyDataSetChanged();
			Log.d("Error=", ""+e.getMessage());
		}
	}
	public void removeChilds(int index){
		try {
				int removeindex=index+1;
				for(int i=0;i<5;i++){
					if(arrTrades.get(removeindex).isOpened){
						removeChilds(removeindex);
					}
					arrTrades.remove(removeindex);
				}
		} catch (Exception e) {
			// TODO: handle exception
			Log.d("Errro=", ""+e.getMessage());
		}
	}
}
