package co.edu.uninorte.neumoniaapp;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.Arrays;

public class RiesgoGermenGravActivity extends AppCompatActivity {
    Context context;
    Patient p1;
    private ListView mLv;
    private CheckAdapter adapter;
    ArrayList values= new ArrayList<String>();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_riesgo_germen_grav);
        context=getApplicationContext();
        Intent intent = getIntent();
        p1 = getIntent().getParcelableExtra("userTag");
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        values.addAll(Arrays.asList(getResources().getStringArray(R.array.germengrave)));


        mLv=(ListView)findViewById(R.id.listView);
        adapter=new CheckAdapter(this,values);
        mLv.setAdapter(adapter);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                p1.setGermenGrav(adapter.solValues);
                Intent intent = new Intent(context, RecomendacionesActivity.class);
                intent.putExtra("userTag", p1);
                Log.d("Desarrollo", "Se abrio la recomendacion");
                intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                context.startActivity(intent);
                overridePendingTransition(R.anim.activity_in, R.anim.activity_out);
            }
        });
    }
    public void onBackPressed() {
        // TODO Auto-generated method stub
        super.onBackPressed();
        overridePendingTransition(R.anim.activity_back_in, R.anim.activity_back_out);
    }
}
