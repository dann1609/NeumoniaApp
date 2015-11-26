package co.edu.uninorte.neumoniaapp;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.CheckBox;
import android.widget.EditText;

import java.util.ArrayList;

public class DatosGeneralesActivity extends AppCompatActivity {

    Context context;
    public static ArrayList datGenValues;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_datos_generales);
        context =getApplicationContext();
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                String in3 = ((EditText) findViewById(R.id.num3)).getText().toString();
                String in4 = ((EditText) findViewById(R.id.num4)).getText().toString();
                String in51 = ((EditText) findViewById(R.id.num5_1)).getText().toString();
                String in52 = ((EditText) findViewById(R.id.num5_2)).getText().toString();
                String in6 = ((EditText) findViewById(R.id.num6)).getText().toString();
                if (in3.equals("") || in4.equals("") || in51.equals("") || in52.equals("") || in6.equals("")) {
                    Snackbar.make(view, "Los campos no estan completos", Snackbar.LENGTH_LONG)
                            .setAction("Action", null).show();
                } else {
                    datGenValues = new ArrayList();
                    datGenValues.add(((CheckBox) findViewById(R.id.check1)).isChecked());
                    datGenValues.add(((CheckBox) findViewById(R.id.check2)).isChecked());
                    datGenValues.add(Integer.parseInt(in3));
                    datGenValues.add(Integer.parseInt(in4));
                    datGenValues.add(Integer.parseInt(in51));
                    datGenValues.add(Integer.parseInt(in52));
                    datGenValues.add(Integer.parseInt(in6));
                    SharedPreferences sp = PreferenceManager.getDefaultSharedPreferences(getApplicationContext());
                    Boolean UREA = sp.getBoolean(getResources().getStringArray(R.array.configuracion)[1], false);
                    Boolean SO2 = sp.getBoolean(getResources().getStringArray(R.array.configuracion)[4], false);
                    Boolean RX = sp.getBoolean(getResources().getStringArray(R.array.configuracion)[6], false);
                    ParaclinicosAActivity.paraAValues = new ArrayList();
                    if (!UREA && !SO2 && !RX) {
                        ParaclinicosAActivity.paraAValues.add(0);
                        ParaclinicosAActivity.paraAValues.add(100);
                        ParaclinicosAActivity.paraAValues.add(false);

                        Log.d("Desarrollo", "Se abrio la definicion de gravedad");
                        DefinirGravedad gravedad = new DefinirGravedad();
                        gravedad.calcular(context);
                        overridePendingTransition(R.anim.activity_in, R.anim.activity_out);
                    } else {
                        Intent intent = new Intent(context, ParaclinicosAActivity.class);
                        Log.d("Desarrollo", "Se abrio los Paraclinicos A");
                        Log.d("Desarrollo", Boolean.toString(((CheckBox) findViewById(R.id.check2)).isChecked()));
                        startActivity(intent);
                        overridePendingTransition(R.anim.activity_in, R.anim.activity_out);
                    }
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
    @Override
    protected void onPause() {
        super.onPause();
        //overridePendingTransition(R.anim.activity_in, R.anim.activity_out);
        //overridePendingTransition(android.R.anim.fade_in, android.R.anim.fade_out);

    }


}
