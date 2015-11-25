package co.edu.uninorte.neumoniaapp;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.Collections;
import java.util.List;

/**
 * Created by Lucia on 25/11/2015.
 */
public class PatientsCardAdapter extends RecyclerView.Adapter<PatientsCardAdapter.MyViewHolder>{

    private final Context context;
    private LayoutInflater inflater;
    private List<Patient> data = Collections.emptyList();
    private RecyclerClickListner mRecyclerClickListner;
    String[] riesgo;
    private Patient p1;

    public PatientsCardAdapter(Context context,List<Patient> data){
        inflater = LayoutInflater.from(context);
        this.context = context;
        this.data = data;
        riesgo=context.getResources().getStringArray(R.array.riesgo);
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = inflater.inflate(R.layout.patients_card_row, parent, false);
        MyViewHolder holder = new MyViewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {
        p1 = data.get(position);


        holder.id.setText(p1.getIdPaciente());
        holder.risk.setText("Riesgo: "+riesgo[p1.getRisk()]);
        holder.edad.setText(Integer.toString(p1.getEdad()));
        holder.press.setText("Presion: "+Integer.toString(p1.getTensArtSist())+"/"+Integer.toString(p1.getTensArtDiast()));
        holder.temp.setText(Integer.toString(p1.getTemp()));
        holder.frec.setText("Frec resp: "+Integer.toString(p1.getFrecRes()));
        holder.so2.setText(Integer.toString(p1.getSo2()));
    }

    @Override
    public int getItemCount() {
        return data.size();
    }

    public void setRecyclerClickListner(RecyclerClickListner recyclerClickListner){
        mRecyclerClickListner = recyclerClickListner;
    }


    class MyViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        private TextView id,risk,edad,press,temp,frec,so2;

        public MyViewHolder(View itemView) {
            super(itemView);
            itemView.setOnClickListener(this);
            id = (TextView) itemView.findViewById(R.id.idpaciente);
            risk = (TextView) itemView.findViewById(R.id.riesgo);
            edad = (TextView) itemView.findViewById(R.id.edad);
            press = (TextView) itemView.findViewById(R.id.presion);
            temp = (TextView) itemView.findViewById(R.id.temp);
            frec = (TextView) itemView.findViewById(R.id.frecresp);
            so2 = (TextView) itemView.findViewById(R.id.so2);
        }

        @Override
        public void onClick(View v) {
            if (mRecyclerClickListner != null) {
                mRecyclerClickListner.itemClick(v, getPosition(),p1);
            }
        }
    }

    public interface RecyclerClickListner
    {
        public void itemClick(View view, int position,Patient patient);
    }
}