package co.edu.uninorte.neumoniaapp;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.CheckBox;

import java.util.ArrayList;

public class RiesgoGermenMedActivity extends AppCompatActivity {

    Context context;
    Patient p1;
    public static ArrayList germenMedValues;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_riesgo_germen_med);
        context=getApplicationContext();
        Intent intent = getIntent();
        p1 = getIntent().getParcelableExtra("userTag");
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                germenMedValues=new ArrayList();
                germenMedValues.add(((CheckBox) findViewById(R.id.gmcheck1)).isChecked());
                germenMedValues.add(((CheckBox) findViewById(R.id.gmcheck2)).isChecked());
                germenMedValues.add(((CheckBox) findViewById(R.id.gmcheck3)).isChecked());
                germenMedValues.add(((CheckBox) findViewById(R.id.gmcheck4)).isChecked());
                p1.setGermenMed(germenMedValues);
                Intent intent = new Intent(context, RecomendacionesActivity.class);
                intent.putExtra("userTag", p1);
                Log.d("Desarrollo", "Se abrio la recomendacion");
                intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                context.startActivity(intent);
            }
        });
    }

}
