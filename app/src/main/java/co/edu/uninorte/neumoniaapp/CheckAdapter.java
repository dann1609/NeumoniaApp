package co.edu.uninorte.neumoniaapp;

import android.content.Context;
import android.graphics.Color;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.CheckBox;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * Created by Lucia on 17/11/2015.
 */
public class CheckAdapter extends BaseAdapter {

    private static final String TAG="AS_ListView";
    private Context context;
    private ArrayList values;
    public final Integer INPUT_STRING=1,INPUT_INTEGER=2,INPUT_BOOLEAN=3;
    public ArrayList solValues=new ArrayList<>();

    public CheckAdapter(Context context,ArrayList values){
        this.context= context;
        this.values=values;
        for (int i = 0; i < this.values.size(); i++){
            this.solValues.add(false);
        }
    }
    public CheckAdapter(Context context,ArrayList values,ArrayList sol){
        this.context= context;
        this.values=values;
        this.solValues=sol;
    }



    @Override
    public int getCount() {
        return values.size();
    }

    @Override
    public Object getItem(int position) {
        return values.get(position);
    }

    @Override
    public long getItemId(int i) {
        return 0;
    }

    @Override
    public View getView(final int position, View view, ViewGroup parent) {
        String s=values.get(position).toString();
        if(view==null){
            LayoutInflater inflater =(LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            view=inflater.inflate(R.layout.check_row,null);
        }
        CheckBox chk= (CheckBox) view.findViewById(R.id.check);
        TextView txt= (TextView) view.findViewById(R.id.checktxt);
        //chk.setText(Boolean.toString(chk.isChecked())+Integer.toString(position));
        txt.setText(values.get(position).toString());
        chk.setChecked((Boolean) solValues.get(position));
        chk.setTag(position);
        chk.setVisibility(View.VISIBLE);
        txt.setVisibility(View.VISIBLE);
        txt.setTextColor(Color.BLACK);
        if(position==0 ){
            chk.setVisibility(View.GONE);
            txt.setVisibility(View.GONE);
            txt.setText(values.get(position).toString().toUpperCase());
            txt.setTextColor(context.getResources().getColor(R.color.colorPrimary));
        }


        chk.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                CheckBox chk=(CheckBox) view;
                boolean isChecked = chk.isChecked();
                solValues.set(position, isChecked);
                Log.d("prueba", Boolean.toString(isChecked));
                Log.d("prueba", Boolean.toString((Boolean) solValues.get(position)));
                Log.d("prueba", "me cree");
                Log.d("prueba", Integer.toString((Integer) chk.getTag()));
            }
        });
        view.setTag(s);
        return view;
    }
}