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

public class CriteriosMayoresActivity extends AppCompatActivity {
    Context context;
    Patient p1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_criterios_mayores);
        context=getApplicationContext();
        Intent intent = getIntent();
        p1 = getIntent().getParcelableExtra("userTag");
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Boolean b1,b2;
                b1=((CheckBox) findViewById(R.id.cmcheck1)).isChecked();
                b2=((CheckBox) findViewById(R.id.cmcheck2)).isChecked();
                p1.setCriteriosMayores(b1,b2);
                if(b1||b2){
                    Intent intent = new Intent(context, RiesgoGermenGravActivity.class);
                    intent.putExtra("userTag", p1);
                    Log.d("Desarrollo", "Se abrio riesgo de germen grave");
                    intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                    context.startActivity(intent);
                    overridePendingTransition(R.anim.activity_in, R.anim.activity_out);
                }else{
                    Intent intent = new Intent(context, CriteriosMenoresActivity.class);
                    intent.putExtra("userTag", p1);
                    Log.d("Desarrollo", "Se abrio criterios menores");
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
