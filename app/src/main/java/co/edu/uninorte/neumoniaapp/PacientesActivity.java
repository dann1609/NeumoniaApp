package co.edu.uninorte.neumoniaapp;

import android.content.Context;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.ListView;

import java.util.ArrayList;

public class PacientesActivity extends AppCompatActivity implements PatientsCardAdapter.RecyclerClickListner {

    Context context;

    private ListView mLv;
    private PatientsAdapter adapter;
    private PatientsCardAdapter cardadapter;
    private RecyclerView mRecyclerView;
    ArrayList values= new ArrayList<Patient>();
    public static ArrayList patValues=new ArrayList<Boolean>();
    MySQLiteHelper db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pacientes);
        context=getApplicationContext();
        mRecyclerView = (RecyclerView) findViewById(R.id.recycle);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        db = new MySQLiteHelper(context);
        values=db.getAllPatients();

        //mLv=(ListView)findViewById(R.id.listView);
        //adapter=new PatientsAdapter(this,values);
        //mLv.setAdapter(adapter);

        cardadapter= new PatientsCardAdapter(this,values);
        cardadapter.setRecyclerClickListner(this);
        mRecyclerView.setAdapter(cardadapter);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(this));

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                patValues=adapter.solValues;

                for(int i=0;i<patValues.size();i++) {
                    if((Boolean)patValues.get(i)){
                        db.deletePatient((Patient) values.get(i));
                        values=db.getAllPatients();
                        adapter=new PatientsAdapter(context,values);
                        mLv.setAdapter(adapter);

                    }
                }

                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });
    }

    @Override
    public void itemClick(View view, int position) {
        Log.d("Recyclerview", "Click position " + position);
    }
}
