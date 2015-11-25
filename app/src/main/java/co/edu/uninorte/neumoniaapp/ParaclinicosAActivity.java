package co.edu.uninorte.neumoniaapp;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
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
import android.widget.TextView;

import java.util.ArrayList;

public class ParaclinicosAActivity extends AppCompatActivity {
    Context context;
    public static ArrayList paraAValues;
    TextView patxt1,patxt2,patxt3,units1,units2;
    EditText num1,num2;
    CheckBox check3;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_paraclinicos_a);
        context=getApplicationContext();

        patxt1=(TextView)findViewById(R.id.patxt1);
        patxt2=(TextView)findViewById(R.id.patxt2);
        patxt3=(TextView)findViewById(R.id.patxt3);
        units1=(TextView)findViewById(R.id.units1);
        units2=(TextView)findViewById(R.id.units2);
        num1=(EditText) findViewById(R.id.num1);
        num2=(EditText) findViewById(R.id.num2);
        check3=(CheckBox) findViewById(R.id.check3);
        SharedPreferences sp = PreferenceManager.getDefaultSharedPreferences(getApplicationContext());
        Boolean UREA = sp.getBoolean(getResources().getStringArray(R.array.configuracion)[1], false);
        Boolean SO2 = sp.getBoolean(getResources().getStringArray(R.array.configuracion)[4],false);
        Boolean RX = sp.getBoolean(getResources().getStringArray(R.array.configuracion)[6],false);
        if(!UREA){
            patxt1.setTextColor(Color.LTGRAY);
            num1.setEnabled(false);
            num1.setTextColor(Color.LTGRAY);
            num1.setText("xxx");
            units1.setTextColor(Color.LTGRAY);
        }
        if(!SO2){
            patxt2.setTextColor(Color.LTGRAY);
            num2.setEnabled(false);
            num2.setTextColor(Color.LTGRAY);
            num2.setText("xxx");
            units2.setTextColor(Color.LTGRAY);
        }
        if(!RX){
            patxt3.setTextColor(Color.LTGRAY);
            check3.setEnabled(false);
            check3.setTextColor(Color.LTGRAY);
        }
        paraAValues=new ArrayList();
        if(!UREA&&!SO2&&!RX){
            paraAValues.add(0);
            paraAValues.add(100);
            paraAValues.add(((CheckBox) findViewById(R.id.check3)).isChecked());
            //Intent intent = new Intent(context, DefinirGravedadActivity.class);
            //intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            //Log.d("Desarrollo", "Se abrio la definicion de gravedad");
            //context.startActivity(intent);
        }




        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String in1=((EditText) findViewById(R.id.num1)).getText().toString();
                String in2=((EditText) findViewById(R.id.num2)).getText().toString();
                SharedPreferences sp = PreferenceManager.getDefaultSharedPreferences(getApplicationContext());
                Boolean UREA = sp.getBoolean(getResources().getStringArray(R.array.configuracion)[1], false);
                Boolean SO2 = sp.getBoolean(getResources().getStringArray(R.array.configuracion)[4],false);
                Boolean RX = sp.getBoolean(getResources().getStringArray(R.array.configuracion)[6],false);

                Patient p1=new Patient();
                if(in1.equals("")||in2.equals("")){
                    Snackbar.make(view, "Los campos no estan completos", Snackbar.LENGTH_LONG)
                            .setAction("Action", null).show();
                }
                else {
                    paraAValues=new ArrayList();
                    if(!UREA){
                        paraAValues.add(0);
                    }
                    else{
                        paraAValues.add(Integer.parseInt(in1));
                    }
                    if(!SO2){
                        paraAValues.add(100);
                    }
                    else{
                        paraAValues.add(Integer.parseInt(in2));
                    }
                    paraAValues.add(((CheckBox) findViewById(R.id.check3)).isChecked());
                    //Intent intent = new Intent(context, DefinirGravedadActivity.class);
                    //intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                    //Log.d("Desarrollo", "Se abrio la definicion de gravedad");
                    //context.startActivity(intent);
                    DefinirGravedad gravedad = new DefinirGravedad();
                    gravedad.calcular(getApplicationContext());

                }


            }
        });
    }

}
