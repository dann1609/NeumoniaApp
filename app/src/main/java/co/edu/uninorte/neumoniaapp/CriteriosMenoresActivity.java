package co.edu.uninorte.neumoniaapp;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.CheckBox;
import android.widget.EditText;

import java.util.ArrayList;

public class CriteriosMenoresActivity extends AppCompatActivity {
    Context context;
    Patient p1;
    public static ArrayList cMenValues;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_criterios_menores);
        context=getApplicationContext();
        Intent intent = getIntent();
        p1 = getIntent().getParcelableExtra("userTag");
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String in3=((EditText) findViewById(R.id.cmennum3)).getText().toString();
                String in4=((EditText) findViewById(R.id.cmennum4)).getText().toString();
                String in5=((EditText) findViewById(R.id.cmennum5)).getText().toString();

                if(in3.equals("")||in4.equals("")||in5.equals("")){
                    Snackbar.make(view, "Los campos no estan completos", Snackbar.LENGTH_LONG)
                            .setAction("Action", null).show();
                }
                else {
                    cMenValues = new ArrayList();
                    cMenValues.add(((CheckBox) findViewById(R.id.cmencheck1)).isChecked());
                    cMenValues.add(((CheckBox) findViewById(R.id.cmencheck2)).isChecked());
                    cMenValues.add(Integer.parseInt(((EditText) findViewById(R.id.cmennum3)).getText().toString()));
                    cMenValues.add(Integer.parseInt(((EditText) findViewById(R.id.cmennum4)).getText().toString()));
                    cMenValues.add(Integer.parseInt(((EditText) findViewById(R.id.cmennum5)).getText().toString()));
                    p1.setCriteriosMenores(cMenValues);
                    Intent intent = new Intent(context, RiesgoGermenGravActivity.class);
                    intent.putExtra("userTag", p1);
                    Log.d("Desarrollo", "Se abrio riesgo de germen grave");
                    intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                    context.startActivity(intent);
                    overridePendingTransition(R.anim.activity_in, R.anim.activity_out);
                }
            }
        });
    }
    public void onBackPressed() {
        // TODO Auto-generated method stub
        super.onBackPressed();
        overridePendingTransition(R.anim.activity_back_in, R.anim.activity_back_out);
    }

}
