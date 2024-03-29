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

public class RiesgoSocialActivity extends AppCompatActivity {

    Context context;
    private ListView mLv;
    private CheckAdapter adapter;
    ArrayList values= new ArrayList<String>();
    public static ArrayList risSocValues=new ArrayList<Boolean>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_riesgo_social);
        context=getApplicationContext();
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);


        values.addAll(Arrays.asList(getResources().getStringArray(R.array.riesgosocial)));


        mLv=(ListView)findViewById(R.id.listView);
        adapter=new CheckAdapter(this,values);
        mLv.setAdapter(adapter);



        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        if(ModificarDatosActivity.active){
           fab.setImageResource(R.drawable.ic_menu_save);
        }
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                risSocValues=adapter.solValues;
                if(!ModificarDatosActivity.active) {
                    Intent intent = new Intent(context, DatosGeneralesActivity.class);
                    Log.d("Desarrollo", "Se abrio datos generales");
                    startActivity(intent);
                    overridePendingTransition(R.anim.activity_in, R.anim.activity_out);
                }else{
                    Intent intent = new Intent(context, PatientsListActivity.class);
                    Log.d("Desarrollo", "Se abrio el LA lista de pacientes");
                    ModificarDatosActivity.active=false;
                    ModificarDatosActivity.editar();
                    intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK|Intent.FLAG_ACTIVITY_NEW_TASK);
                    startActivity(intent);
                    overridePendingTransition(R.anim.activity_in, R.anim.activity_out);
                }
            }
        });
    }
    @Override
    public void onBackPressed() {
        // TODO Auto-generated method stub
        super.onBackPressed();
        overridePendingTransition(R.anim.activity_back_in, R.anim.activity_back_out);
    }

}
