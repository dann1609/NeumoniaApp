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

    public PatientsCardAdapter(Context context,List<Patient> data){
        inflater = LayoutInflater.from(context);
        this.context = context;
        this.data = data;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = inflater.inflate(R.layout.patients_card_row, parent, false);
        MyViewHolder holder = new MyViewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {
        Patient p1 = data.get(position);
        holder.tv.setText(p1.getIdPaciente());
    }

    @Override
    public int getItemCount() {
        return data.size();
    }

    public void setRecyclerClickListner(RecyclerClickListner recyclerClickListner){
        mRecyclerClickListner = recyclerClickListner;
    }


    class MyViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        private TextView tv;

        public MyViewHolder(View itemView) {
            super(itemView);
            itemView.setOnClickListener(this);
            tv = (TextView) itemView.findViewById(R.id.listText);
        }

        @Override
        public void onClick(View v) {
            if (mRecyclerClickListner != null) {
                mRecyclerClickListner.itemClick(v, getPosition());
            }
        }
    }

    public interface RecyclerClickListner
    {
        public void itemClick(View view, int position);
    }
}