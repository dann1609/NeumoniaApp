package co.edu.uninorte.neumoniaapp;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.Arrays;

public class ConfiguracionActivity extends AppCompatActivity {

    Context context;

    private ListView mLv;
    private CheckAdapter adapter;
    ArrayList values= new ArrayList<String>();
    public static ArrayList confValues=new ArrayList<Boolean>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_configuracion);
        context=getApplicationContext();
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        SharedPreferences sp = PreferenceManager.getDefaultSharedPreferences(getApplicationContext());
        confValues=new ArrayList<Boolean>();
        for(int i=0;i<getResources().getStringArray(R.array.configuracion).length;i++){
            confValues.add(sp.getBoolean(getResources().getStringArray(R.array.configuracion)[i],false));
        }

        values.addAll(Arrays.asList(getResources().getStringArray(R.array.configuracion)));
        mLv=(ListView)findViewById(R.id.listView);
        adapter=new CheckAdapter(this, values,confValues);
        mLv.setAdapter(adapter);


        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                confValues = adapter.solValues;
                SharedPreferences sharedPref = PreferenceManager.getDefaultSharedPreferences(context);
                SharedPreferences.Editor editor = sharedPref.edit();
                for (int i = 0; i < confValues.size(); i++) {
                    editor.putBoolean((getResources().getStringArray(R.array.configuracion))[i], (Boolean) confValues.get(i));
                    Log.d("desarrollo", (getResources().getStringArray(R.array.configuracion))[i]);
                }
                editor.commit();
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });
    }

}
