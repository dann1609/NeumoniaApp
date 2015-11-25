package co.edu.uninorte.neumoniaapp;

import android.content.Context;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.Arrays;

public class ModificarDatosActivity extends AppCompatActivity {

    Context context;

    private ListView mLv;
    private CheckAdapter adapter;
    ArrayList values= new ArrayList<String>();
    public static ArrayList editValues=new ArrayList<Boolean>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_modificar_datos);
        context=getApplicationContext();
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        values.addAll(Arrays.asList(getResources().getStringArray(R.array.comorbilidades)));


        mLv=(ListView)findViewById(R.id.listView);
        adapter=new CheckAdapter(this,values);
        mLv.setAdapter(adapter);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own actions", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });
    }

}
