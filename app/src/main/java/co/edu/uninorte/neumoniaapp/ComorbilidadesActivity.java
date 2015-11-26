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

public class ComorbilidadesActivity extends AppCompatActivity {

    Context context;

    private ListView mLv;
    private CheckAdapter adapter;
    ArrayList values= new ArrayList<String>();
    public static ArrayList comValues=new ArrayList<Boolean>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_comorbilidades);

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
                comValues=adapter.solValues;

                Intent intent = new Intent(context, RiesgoSocialActivity.class);
                Log.d("Desarrollo", "Se abrio el RiesgoSocial");
                startActivity(intent);
            }
        });
    }


    @Override
    protected void onPause() {
        super.onPause();
        //overridePendingTransition(android.R.anim.fade_in, android.R.anim.fade_out);
        overridePendingTransition(R.xml.slide_in_right, R.xml.slide_out_right);
    }
}
